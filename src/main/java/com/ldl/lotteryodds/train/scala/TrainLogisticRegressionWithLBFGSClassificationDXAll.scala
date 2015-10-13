package com.ldl.lotteryodds.train.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.optimization.{LBFGS, LogisticGradient, SquaredL2Updater}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils

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

        /*val splits = trainDataAll.randomSplit(Array(0.9, 0.1),seed = 11L)
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

        /*val model = new LogisticRegressionWithLBFGS()
                .setNumClasses(2)
                .run(trainingData)

        val predictionAndLabels = testData.map { case LabeledPoint(label, features) =>
            val prediction = model.predict(features)
            (label,prediction)
        }
        print("label : ",predictionAndLabels.collect().toList)

        val metrics = new MulticlassMetrics(predictionAndLabels)
        val auROC = metrics.precision

        println("Area under ROC = " + auROC)*/
        val numFeatures = trainingData.take(1)(0).features.size
        val training = trainingData.map(x => (x.label, MLUtils.appendBias(x.features))).cache()
        val numCorrections = 10
        val convergenceTol = 1e-4
        val maxNumIterations = 20
        val regParam = 0.001
        val initialWeightsWithIntercept = Vectors.dense(new Array[Double](numFeatures + 1))

        val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
            training,
            new LogisticGradient(),
            new SquaredL2Updater(),
            numCorrections,
            convergenceTol,
            maxNumIterations,
            regParam,
            initialWeightsWithIntercept)

        val model = new LogisticRegressionModel(
            Vectors.dense(weightsWithIntercept.toArray.slice(0, weightsWithIntercept.size - 1)),
            weightsWithIntercept(weightsWithIntercept.size - 1))

        //model.clearThreshold()
        /*model.setThreshold(0.5)
        val scoreAndLabels = testData.map { point =>
            val score = model.predict(point.features)
            (score, point.label)
        }
        print("label : ",scoreAndLabels.collect().toList)
        val metrics = new BinaryClassificationMetrics(scoreAndLabels)
        val auROC = metrics.areaUnderROC()
        println("Area under ROC = " + auROC)*/
        model.save(sc,"F:\\data\\lotteryodds\\model\\LogisticRegressionWithLBFGSDXAll")
        sc.stop()
    }
}
