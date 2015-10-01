package com.ldl.lotteryodds.train.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.model.RandomForestModel

/**
 * 作者: LDL
 * 功能说明: 随机森林分类澳门数据
 * 创建日期: 2015/9/30 14:37
 */
object TestAmRandomForestClassification {
    def main(args: Array[ String ]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext( "local[4]", "am" )


        /** 测试数据 */
        val testRowData = sc.textFile( "F:\\data\\lotteryodds\\test_am.txt" )
        val testRecords = testRowData.map( line => line.split( "\t" ) )
        val testData = testRecords.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == "") 0.00 else d.toDouble )
            LabeledPoint( label, Vectors.dense( features ) )
        }
        testData.cache( )


        /** 分类 */
        val model = RandomForestModel.load( sc, "F:\\data\\lotteryodds\\model\\RandomForest" )

        // Evaluate model on test instances and compute test error
        /*val labelAndPreds = testData.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
        }
        print("label : ",labelAndPreds.collect().toList)
        val testErr = labelAndPreds.filter( r => r._1 != r._2 ).count().toDouble / testData.count()
        println("Test Error = " + testErr)*/
        /*val predictions = testData.map { point => model.predict( point.features ) }
        predictions.collect( ).toList.foreach( p => {
            println( "预测结果 : " + p.toInt )
        } )*/

        val labelAndPreds = testData.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
        }
        print("label : ",labelAndPreds.collect().toList)
        sc.stop( )
    }
}
