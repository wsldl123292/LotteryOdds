package com.ldl.lotteryodds.train.scala

import java.io.{File, PrintWriter}

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.optimization.{LBFGS, LogisticGradient, SquaredL2Updater}
import org.apache.spark.mllib.util.MLUtils

/**
  * 作者: LDL
  * 说明:
  * 时间: 2015/10/6 21:11
  */
object TrainBinary {
    def main(args: Array[String]) {
        //System.setProperty("hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master")
        val sc = new SparkContext("local[3]", "am")
        /** 训练数据 */
        val trainRowDataAll = sc.textFile("train_all_binary.txt")
        val trainRecordsAll = trainRowDataAll.map(line => line.split("\t"))
        val trainingData = trainRecordsAll.map { r =>
            val trimmed = r.map(_.replaceAll("\"", ""))
            val label = trimmed(r.size - 1).toInt
            val features = trimmed.slice(0, r.size - 1).map(d => if (d == "") 0.00 else d.toDouble)
            LabeledPointCus("", label, Vectors.dense(features))
        }

        /*val splits = trainDataAll.randomSplit(Array(0.9, 0.1))
        val (trainingData, testData) = (splits(0), splits(1))
        trainingData.cache()*/


        val testRowData = sc.textFile("test_all_binary.txt")
        val testRecords = testRowData.map(line => line.split("\t"))
        val testData = testRecords.map { r =>
            val trimmed = r.map(_.replaceAll("\"", ""))
            val num = trimmed(r.size - 1)
            val label = trimmed(r.size - 2).toInt
            val features = trimmed.slice(0, r.size - 2).map(d => if (d == null) 0 else d.toDouble)
            LabeledPointCus(num, label, Vectors.dense(features))
        }
        testData.cache()


        /** 分类 */
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

        println("Area under ROC = " + auROC)

        model.save(sc,"F:\\data\\lotteryodds\\model\\LogisticRegressionBinary")*/

        val training = trainingData.map(x => (x.label, MLUtils.appendBias(x.features))).cache()
        val numFeatures = trainingData.take(1)(0).features.size
        val numCorrections = 10
        val convergenceTol = 1e-4
        val maxNumIterations = 30
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

        model.setThreshold(0.56)

        val writer = new PrintWriter(new File("result_all_binary30.txt"))

        val scoreAndLabels = testData.map { case LabeledPointCus(num, label, features) =>
            val score = model.predict(features)

            (num, label, score)
        }
        println(scoreAndLabels.collect().toList)
        scoreAndLabels.collect().toList.foreach(p => {
            if (p._3.toInt == 0) {
                writer.write(p._1 + " : 3 \n")
            } else {
                writer.write(p._1 + " : 01 \n")
            }
        })
        writer.close()

        // Get evaluation metrics.
        val testErr = scoreAndLabels.filter(r => r._2 != r._3).count().toDouble / testData.count()
        println("Test Error = " + testErr)

        //model.save(sc,"F:\\data\\lotteryodds\\model\\LogisticRegressionBinary")
        sc.stop()
    }
}
