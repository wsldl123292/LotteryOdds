package com.ldl.lotteryodds.train.scala

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
        val trainRowDataAm = sc.textFile("F:\\data\\lotteryodds\\train_am.txt")
        val trainRecordsAm = trainRowDataAm.map(line=>line.split("\t"))
        val trainDataAm = trainRecordsAm.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d => if(d =="") 0.00 else d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }

        trainDataAm.cache()



        val trainRowDataLb = sc.textFile("F:\\data\\lotteryodds\\train_lb.txt")
        val trainRecordsLb = trainRowDataLb.map(line=>line.split("\t"))
        val trainDataLb = trainRecordsLb.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }
        trainDataLb.cache()


        val trainRowDataWl = sc.textFile("F:\\data\\lotteryodds\\train_wl.txt")
        val trainRecordsWl = trainRowDataWl.map(line=>line.split("\t"))
        val trainDataWl = trainRecordsWl.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }
        trainDataWl.cache()

        /** 测试数据 */
        /*val testRowData = sc.textFile("F:\\test_am.txt")
        val testRecords = testRowData.map(line=>line.split("\t"))
        val testData = testRecords.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }
        testData.cache()*/


        /** 分类 */
        val numClasses = 4
        val categoricalFeaturesInfo = Map[Int, Int]()
        val numTrees = 20 // Use more in practice.
        val featureSubsetStrategy = "auto" // Let the algorithm choose.
        val impurity = "gini"
        val maxDepth = 20
        val maxBins = 32


        val modelAm = RandomForest.trainClassifier(trainDataAm, numClasses, categoricalFeaturesInfo,
            numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

        val modelLb = RandomForest.trainClassifier(trainDataLb, numClasses, categoricalFeaturesInfo,
            numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

        val modelWl = RandomForest.trainClassifier(trainDataWl, numClasses, categoricalFeaturesInfo,
            numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)


        modelAm.save(sc,"F:\\data\\lotteryodds\\model\\RandomForest\\am")
        modelLb.save(sc,"F:\\data\\lotteryodds\\model\\RandomForest\\lb")
        modelWl.save(sc,"F:\\data\\lotteryodds\\model\\RandomForest\\wl")
        sc.stop()
    }
}
