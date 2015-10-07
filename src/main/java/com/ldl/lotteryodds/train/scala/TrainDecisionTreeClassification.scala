package com.ldl.lotteryodds.train.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree

/**
 * 作者: LDL
 * 功能说明: 决策树分类数据
 * 创建日期: 2015/9/30 14:37
 */
object TrainDecisionTreeClassification {
    def main(args: Array[String]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext("local[4]", "am")
        /** 训练数据 */
        val trainRowData = sc.textFile("F:\\train_am.txt")
        val trainRecords = trainRowData.map(line=>line.split("\t"))
        val trainData = trainRecords.map{ r=>
            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-1).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }
        trainData.cache()


        /** 测试数据 */
        val testRowData = sc.textFile("F:\\test_am.txt")
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
        val impurity = "gini"
        val maxDepth = 25
        val maxBins = 32

        val model = DecisionTree.trainClassifier(trainData, numClasses, categoricalFeaturesInfo,
            impurity, maxDepth, maxBins)

        // Evaluate model on test instances and compute test error
        val labelAndPreds = testData.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
        }
        print("label : ",labelAndPreds.collect().toList)
        val testErr = labelAndPreds.filter( r => r._1 != r._2 ).count().toDouble / testData.count()
        println("Test Error = " + testErr)
        sc.stop()
    }
}