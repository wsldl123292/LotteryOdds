package com.ldl.lotteryodds.train.scala

import java.io.{PrintWriter, File}

import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.model.RandomForestModel

/**
 * 作者: LDL
 * 功能说明: 随机森林分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TestRandomForestClassification {
    def main(args: Array[ String ]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext( "local[4]", "am" )

        /** 测试数据 */
        val testRowDataAm = sc.textFile( "F:\\data\\lotteryodds\\test_am.txt" )
        val testRecordsAm = testRowDataAm.map( line => line.split( "\t" ) )
        val testDataAm = testRecordsAm.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d =="") 0.00 else d.toDouble )
            LabeledPoint( label, Vectors.dense(features))
        }
        testDataAm.cache( )

        val modelAm = RandomForestModel.load( sc, "F:\\data\\lotteryodds\\model\\RandomForest\\am" )

        /*val labelAndPredsAm = testDataAm.map { point =>
            val prediction = modelAm.predict(point.features)
            (point.label, prediction)
        }
        print("labelam : ",labelAndPredsAm.collect().toList)
        val testErrAm = labelAndPredsAm.filter( r => r._1 != r._2 ).count().toDouble / testDataAm.count()
        println("Test Error Am = " + testErrAm)*/

        val writerAm = new PrintWriter(new File("F:\\data\\lotteryodds\\result_am.txt" ))
        val predictionsAm = testDataAm.map { point => modelAm.predict( point.features ) }
        predictionsAm.collect().toList.foreach( p => {
            writerAm.write("预测结果 : " + p.toInt +"\n")
        } )
        writerAm.close()



        val testRowDataLb = sc.textFile( "F:\\data\\lotteryodds\\test_lb.txt" )
        val testRecordsLb = testRowDataLb.map( line => line.split( "\t" ) )
        val testDataLb = testRecordsLb.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == "") 0.00 else d.toDouble )
            LabeledPoint( label, Vectors.dense( features ) )
        }
        testDataLb.cache( )

        val modelLb = RandomForestModel.load( sc, "F:\\data\\lotteryodds\\model\\RandomForest\\lb" )

        /*val labelAndPredsLb = testDataLb.map { point =>
            val prediction = modelLb.predict(point.features)
            (point.label, prediction)
        }
        print("labellb : ",labelAndPredsLb.collect().toList)
        val testErrLb = labelAndPredsLb.filter( r => r._1 != r._2 ).count().toDouble / testDataLb.count()
        println("Test Error Lb = " + testErrLb)*/

        val writerLb = new PrintWriter(new File("F:\\data\\lotteryodds\\result_lb.txt" ))
        val predictionsLb = testDataLb.map { point => modelLb.predict( point.features ) }
        predictionsLb.collect().toList.foreach( p => {
            writerLb.write("预测结果 : " + p.toInt +"\n")
        } )
        writerLb.close()



        val testRowDataWl = sc.textFile( "F:\\data\\lotteryodds\\test_wl.txt" )
        val testRecordsWl = testRowDataWl.map( line => line.split( "\t" ) )
        val testDataWl = testRecordsWl.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == "") 0.00 else d.toDouble )
            LabeledPoint( label, Vectors.dense( features ) )
        }
        testDataWl.cache( )

        val modelWl = RandomForestModel.load( sc, "F:\\data\\lotteryodds\\model\\RandomForest\\wl" )

        /*val labelAndPredsWl = testDataWl.map { point =>
            val prediction = modelWl.predict(point.features)
            (point.label, prediction)
        }
        print("labelwl : ",labelAndPredsWl.collect().toList)
        val testErrWl = labelAndPredsWl.filter( r => r._1 != r._2 ).count().toDouble / testDataWl.count()
        println("Test Error Wl = " + testErrWl)*/

        val writerWl = new PrintWriter(new File("F:\\data\\lotteryodds\\result_wl.txt" ))
        val predictionsWl = testDataWl.map { point => modelWl.predict( point.features ) }
        predictionsWl.collect().toList.foreach( p => {
            writerWl.write("预测结果 : " + p.toInt +"\n")
        } )
        writerWl.close()



        val writerAll = new PrintWriter(new File("F:\\data\\lotteryodds\\result_all.txt" ))
        val count = predictionsLb.collect().toList.size
        for(i <- 0 to count-1){
            if((predictionsAm.collect().toList(i)==predictionsLb.collect().toList(i))&&(predictionsLb.collect().toList(i)==predictionsWl.collect().toList(i))){
                writerAll.write("第"+(i+1)+"场比赛 ： "+predictionsAm.collect().toList(i).toInt+"\n")
            }
        }
        writerAll.close()

        /*for ((elem, count) <- predictions.collect().toList.zipWithIndex){
            println(s"element $count is $elem")
        }*/
        sc.stop( )
    }
}
