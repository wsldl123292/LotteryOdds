package com.ldl.lotteryodds.train.scala

import org.apache.spark.mllib.linalg.{Vectors, Vector}

/**
 * 作者: LDL
 * 功能说明: 自定义lablepoint 添加比赛序号
 * 创建日期: 2015/10/29 10:56
 */
case class LabeledPointCus(
                               num: String,
                               label: Double,
                               features: Vector) {
    override def toString: String = {
        s"($label,$features)"
    }
}

object LabeledPointCus {
    /**
     * Parses a string resulted from `LabeledPoint#toString` into
     * an [[org.apache.spark.mllib.regression.LabeledPoint]].
     *
     */
    def parse(s: String): LabeledPointCus = {
        val parts = s.split(',')
        val num = parts(0)
        val label = java.lang.Double.parseDouble(parts(1))
        val features = Vectors.dense(parts(2).trim().split(' ').map(java.lang.Double.parseDouble))
        LabeledPointCus(num, label, features)
    }
}