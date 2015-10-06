package com.ldl.lotteryodds.train.scala

import java.io.{File, PrintWriter}

import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.model.RandomForestModel

/**
 * 作者: LDL
 * 功能说明: 随机森林分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TestRandomForestClassificationAll {
    def main(args: Array[ String ]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext( "local[4]", "am" )

        /** 测试数据 */
        val testRowData = sc.textFile( "F:\\data\\lotteryodds\\test_all.txt" )
        val testRecords = testRowData.map( line => line.split( "\t" ) )
        val testData = testRecords.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == null) 0 else d.toDouble )
            LabeledPoint( label, Vectors.dense(features))
        }
        testData.cache( )

        val model = RandomForestModel.load( sc, "F:\\data\\lotteryodds\\model\\RandomForestAll" )

        /*val labelAndPredsAm = testDataAm.map { point =>
            val prediction = modelAm.predict(point.features)
            (point.label, prediction)
        }
        print("labelam : ",labelAndPredsAm.collect().toList)
        val testErrAm = labelAndPredsAm.filter( r => r._1 != r._2 ).count().toDouble / testDataAm.count()
        println("Test Error Am = " + testErrAm)*/

        val writer = new PrintWriter(new File("F:\\data\\lotteryodds\\result_all.txt" ))
        val predictions = testData.map { point => model.predict( point.features ) }
        predictions.collect().toList.foreach( p => {
            writer.write("预测结果 : " + p.toInt +"\n")
        } )
        writer.close()

        sc.stop( )
    }
}
