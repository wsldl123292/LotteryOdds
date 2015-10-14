package com.ldl.lotteryodds.train.scala

import java.io.{File, PrintWriter}

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * 作者: LDL
 * 功能说明: 随机森林分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TestLogisticRegressionWithLBFGSClassificationDXAll {
    def main(args: Array[ String ]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext( "local[4]", "am" )
        /** 测试数据 */
        val testRowData = sc.textFile( "F:\\data\\lotteryodds\\test_dx_all.txt" )
        val testRecords = testRowData.map( line => line.split( "\t" ) )
        val testData = testRecords.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == null) 0 else d.toDouble )
            LabeledPoint( label, Vectors.dense(features))
        }
        testData.cache( )

        val model = LogisticRegressionModel.load( sc, "F:\\data\\lotteryodds\\model\\LogisticRegressionWithLBFGSDXAll" )

        model.setThreshold(0.49)

        val labelAndPreds = testData.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
        }
        print("label : ",labelAndPreds.collect().toList)
        val testErr = labelAndPreds.filter( r => r._1 != r._2 ).count().toDouble / testData.count()
        println("Test Error = " + testErr)

        val writer = new PrintWriter(new File("F:\\data\\lotteryodds\\result_dx_all.txt" ))
        val predictions = testData.map { point => model.predict( point.features ) }
        predictions.collect().toList.foreach( p => {
            if(p.toInt == 0){
                writer.write("预测结果 : 小于3球\n")
            }else if(p.toInt == 1){
                writer.write("预测结果 : 3球或3球以上\n")
            }
        } )
        writer.close()

        sc.stop( )
    }
}
