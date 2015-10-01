package com.ldl.lotteryodds.train;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ldl.lotteryodds.dao.LotteryOddsDao;
import com.ldl.lotteryodds.entity.OddInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明: 生成澳门数据训练集
 * 创建日期: 2015/9/30 14:08
 */
public class TrainAMText {

    public static void main(String[] args) throws IOException {
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        final List<OddInfo> oddInfos = lotteryOddsDao.getOddInfos("select * from am");
        final StringBuilder stringBuffer = new StringBuilder();
        for (OddInfo oddInfo : oddInfos) {
            stringBuffer.append(oddInfo.getCwOddAm()).append("\t")
                    .append(oddInfo.getCdOddAm()).append("\t")
                    .append(oddInfo.getClOddAm()).append("\t")
                    .append(oddInfo.getLwOddAm()).append("\t")
                    .append(oddInfo.getLdOddAm()).append("\t")
                    .append(oddInfo.getLlOddAm()).append("\t")
                    .append(oddInfo.getCwKlAm()).append("\t")
                    .append(oddInfo.getCdKlAm()).append("\t")
                    .append(oddInfo.getClKlAm()).append("\t")
                    .append(oddInfo.getLwKlAm()).append("\t")
                    .append(oddInfo.getLdKlAm()).append("\t")
                    .append(oddInfo.getLlKlAm()).append("\t")
                    .append(oddInfo.getCzWaterAm()).append("\t")
                    .append(oddInfo.getCpAm()).append("\t")
                    .append(oddInfo.getCkWaterAm()).append("\t")
                    .append(oddInfo.getLzWaterAm()).append("\t")
                    .append(oddInfo.getLpAm()).append("\t")
                    .append(oddInfo.getLkWaterAm()).append("\t").append(oddInfo.getResult()).append("\n");
        }
        Files.write(stringBuffer.toString(), new File("F:\\lotteryodds\\train_am.txt"), Charsets.UTF_8);

    }
}
