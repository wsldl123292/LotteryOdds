package com.ldl.lotteryodds.train.scala

import java.io.{File, PrintWriter}

import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.RandomForest

/**
 * 作者: LDL
 * 功能说明: 随机森林分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TrainRandomForestClassification {
    def main(args: Array[String]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext("local[4]", "am")
        /** 训练数据 */
        val trainRowData = sc.textFile("F:\\data\\lotteryodds\\train.txt")
        val trainRecords = trainRowData.map(line=>line.split("\t"))
        val trainData = trainRecords.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d => if(d =="") 0.00 else d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }

        /** 测试数据 */
        val testRowData = sc.textFile("F:\\data\\lotteryodds\\test.txt")
        val testRecords = testRowData.map(line=>line.split("\t"))
        val testData = testRecords.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }
        testData.cache()


        /** 分类 */
        val numClasses = 4
        val categoricalFeaturesInfo = Map[Int, Int]()
        val numTrees = 30 // Use more in practice.
        val featureSubsetStrategy = "auto" // Let the algorithm choose.
        val impurity = "gini"
        val maxDepth = 10
        val maxBins = 100


        val model = RandomForest.trainClassifier(trainData, numClasses, categoricalFeaturesInfo,
            numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

        val labelAndPreds = testData.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
        }
        print("label : ",labelAndPreds.collect().toList)
        val testErr = labelAndPreds.filter( r => r._1 != r._2 ).count().toDouble / testData.count()
        println("Test Error = " + testErr)

        val writer = new PrintWriter(new File("F:\\data\\lotteryodds\\result_train.txt" ))
        val predictions = testData.map { point => model.predict( point.features ) }
        predictions.collect().toList.foreach( p => {
            writer.write("预测结果 : " + p.toInt +"\n")
        } )
        writer.close()
        //model.save(sc,"F:\\data\\lotteryodds\\model\\RandomForest")
        sc.stop()
    }
}
