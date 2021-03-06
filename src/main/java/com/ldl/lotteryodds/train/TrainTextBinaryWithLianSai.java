package com.ldl.lotteryodds.train;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ldl.lotteryodds.dao.LotteryOddsDao;
import com.ldl.lotteryodds.entity.OddInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明: 生成数据训练集
 * 创建日期: 2015/9/30 14:08
 */
public class TrainTextBinaryWithLianSai {

    public static void main(String[] args) throws IOException {
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        /** 澳门 */
        final List<OddInfo> oddInfos = lotteryOddsDao.getOddInfos("select * from oalldx ");/*order BY result ASC LIMIT 0,31151*/
        final StringBuilder stringBuffer = new StringBuilder();
        for (OddInfo oddInfo : oddInfos) {
            int total = oddInfo.getZscore() + oddInfo.getKscore();
            stringBuffer
                    .append(oddInfo.getZjscore()).append("\t")
                    .append(oddInfo.getZlscore()).append("\t")
                    .append(oddInfo.getKjscore()).append("\t")
                    .append(oddInfo.getKlscore()).append("\t")
                    .append(oddInfo.getZzjscore()).append("\t")
                    .append(oddInfo.getZzlscore()).append("\t")
                    .append(oddInfo.getKkjscore()).append("\t")
                    .append(oddInfo.getKklsocre()).append("\t")

                    .append((new BigDecimal(oddInfo.getZjscore()).add(new BigDecimal(oddInfo.getKlscore()))).divide(new BigDecimal(10))).append("\t")
                    .append((new BigDecimal(oddInfo.getZlscore()).add(new BigDecimal(oddInfo.getKjscore()))).divide(new BigDecimal(10))).append("\t")
                    .append((new BigDecimal(oddInfo.getZzjscore()).add(new BigDecimal(oddInfo.getKklsocre()))).divide(new BigDecimal(10))).append("\t")
                    .append((new BigDecimal(oddInfo.getZzlscore()).add(new BigDecimal(oddInfo.getKkjscore()))).divide(new BigDecimal(10))).append("\t")



                    .append(new BigDecimal(oddInfo.getlDWaterAm()).subtract(new BigDecimal(oddInfo.getcDWaterAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterAm()).subtract(new BigDecimal(oddInfo.getcXWaterAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXAm()).subtract(new BigDecimal(oddInfo.getcPDXAm())).toString().replace("-", "")).append("\t")

                    .append(oddInfo.getcDWaterAm()).append("\t")
                    .append(oddInfo.getcXWaterAm()).append("\t")
                    .append(oddInfo.getlDWaterAm()).append("\t")
                    .append(oddInfo.getlXWaterAm()).append("\t")
                    .append(oddInfo.getcPDXAm().replace("-", "")).append("\t")
                    .append(oddInfo.getlPDXAm().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlDWaterLb()).subtract(new BigDecimal(oddInfo.getcDWaterLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterLb()).subtract(new BigDecimal(oddInfo.getcXWaterLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXLb()).subtract(new BigDecimal(oddInfo.getcPDXLb())).toString().replace("-", "")).append("\t")

                    .append(oddInfo.getcDWaterLb()).append("\t")
                    .append(oddInfo.getcXWaterLb()).append("\t")
                    .append(oddInfo.getlDWaterLb()).append("\t")
                    .append(oddInfo.getlXWaterLb()).append("\t")
                    .append(oddInfo.getcPDXLb().replace("-", "")).append("\t")
                    .append(oddInfo.getlPDXLb().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getlDWaterWl()).subtract(new BigDecimal(oddInfo.getcDWaterWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterWl()).subtract(new BigDecimal(oddInfo.getcXWaterWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXWl()).subtract(new BigDecimal(oddInfo.getcPDXWl())).toString().replace("-", "")).append("\t")
                    .append(oddInfo.getcDWaterWl()).append("\t")
                    .append(oddInfo.getcXWaterWl()).append("\t")
                    .append(oddInfo.getlDWaterWl()).append("\t")
                    .append(oddInfo.getlXWaterWl()).append("\t")
                    .append(oddInfo.getcPDXWl().replace("-", "")).append("\t")
                    .append(oddInfo.getlPDXWl().replace("-", "")).append("\t");

            if (total < 3) {
                stringBuffer.append(0).append("\n");
            } else {
                stringBuffer.append(1).append("\n");
            }
            /*if (total < 2) {
                stringBuffer.append(0).append("\n");
            } else if (total == 2) {
                stringBuffer.append(1).append("\n");
            } else if (total == 3) {
                stringBuffer.append(2).append("\n");
            } else if (total > 3) {
                stringBuffer.append(3).append("\n");
            }*/
        }
        Files.write(stringBuffer.toString(), new File("F:\\data\\lotteryodds\\train_dx_all.txt"), Charsets.UTF_8);

    }
}
