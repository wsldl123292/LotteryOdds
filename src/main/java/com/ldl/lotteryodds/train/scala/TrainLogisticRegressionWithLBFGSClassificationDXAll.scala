package com.ldl.lotteryodds.train.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * 作者: LDL
 * 功能说明: 随机森林分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TrainLogisticRegressionWithLBFGSClassificationDXAll {
    def main(args: Array[String]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext("local[5]", "am")
        /** 训练数据 */
        val trainRowDataAll = sc.textFile("F:\\data\\lotteryodds\\train_dx_all.txt")
        val trainRecordsAll = trainRowDataAll.map(line=>line.split("\t"))
        val trainingData = trainRecordsAll.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d => if(d =="") 0.00 else d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }

        /*val splits = trainDataAll.randomSplit(Array(0.9, 0.1),seed = 1L)
        val (trainingData, testData) = (splits(0), splits(1))
        trainingData.cache()*/
        /*val testRowData = sc.textFile( "F:\\data\\lotteryodds\\test_dx_all.txt" )
        val testRecords = testRowData.map( line => line.split( "\t" ) )
        val testData = testRecords.map { r =>
            val trimmed = r.map( _.replaceAll( "\"", "" ) )
            val label = trimmed( r.size - 1 ).toInt
            val features = trimmed.slice( 0, r.size - 1 ).map( d => if(d == null) 0 else d.toDouble )
            LabeledPoint( label, Vectors.dense(features))
        }
        testData.cache( )*/

        val model = new LogisticRegressionWithLBFGS()
                .setNumClasses(2)
                .run(trainingData)

        /*val predictionAndLabels = testData.map { case LabeledPoint(label, features) =>
            val prediction = model.predict(features)
            (label,prediction)
        }
        print("label : ",predictionAndLabels.collect().toList)

        // Get evaluation metrics.
        val metrics = new MulticlassMetrics(predictionAndLabels)
        val auROC = metrics.precision

        println("Area under ROC = " + auROC)*/
        model.save(sc,"F:\\data\\lotteryodds\\model\\LogisticRegressionWithLBFGSDXAll")
        sc.stop()
    }
}
