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
import java.text.DecimalFormat;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明: 生成数据训练集
 * 创建日期: 2015/9/30 14:08
 */
public class TrainText {

    public static void main(String[] args) throws IOException {

        BigDecimal a = new BigDecimal(0.02);
        BigDecimal b = new BigDecimal(0.05);
        System.out.println(b.subtract(a).floatValue());
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        /** 澳门 */
        final List<OddInfo> oddInfosAm = lotteryOddsDao.getOddInfos("select * from oall");/*order BY result ASC LIMIT 0,31151*/
        final StringBuilder stringBufferAm = new StringBuilder();
        for (OddInfo oddInfo : oddInfosAm) {
            stringBufferAm
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwOddAm()) - (Double.parseDouble(oddInfo.getCwOddAm())))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdOddAm()) - (Double.parseDouble(oddInfo.getCdOddAm())))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlOddAm()) - Double.parseDouble(oddInfo.getClOddAm()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwKlAm()) - Double.parseDouble(oddInfo.getCwKlAm()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdKlAm()) - Double.parseDouble(oddInfo.getCdKlAm()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlKlAm()) - Double.parseDouble(oddInfo.getClKlAm()))).append("\t")
                    .append(oddInfo.getCzWaterAm()).append("\t")
                    .append(oddInfo.getCpAm()).append("\t")
                    .append(oddInfo.getCkWaterAm()).append("\t")
                    .append(oddInfo.getLzWaterAm()).append("\t")
                    .append(oddInfo.getLpAm()).append("\t")
                    .append(oddInfo.getLkWaterAm()).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwOddLb()) - Double.parseDouble(oddInfo.getCwOddLb()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdOddLb()) - Double.parseDouble(oddInfo.getCdOddLb()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlOddLb()) - Double.parseDouble(oddInfo.getClOddLb()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwKlLb()) - Double.parseDouble(oddInfo.getCwKlLb()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdKlLb()) - Double.parseDouble(oddInfo.getCdKlLb()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlKlLb()) - Double.parseDouble(oddInfo.getClKlLb()))).append("\t")
                    .append(oddInfo.getCzWaterLb()).append("\t")
                    .append(oddInfo.getCpLb()).append("\t")
                    .append(oddInfo.getCkWaterLb()).append("\t")
                    .append(oddInfo.getLzWaterLb()).append("\t")
                    .append(oddInfo.getLpLb()).append("\t")
                    .append(oddInfo.getLkWaterLb()).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwOddWl()) - Double.parseDouble(oddInfo.getCwOddWl()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdOddWl()) - Double.parseDouble(oddInfo.getCdOddWl()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlOddWl()) - Double.parseDouble(oddInfo.getClOddWl()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLwKlWl()) - Double.parseDouble(oddInfo.getCwKlWl()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLdKlWl()) - Double.parseDouble(oddInfo.getCdKlWl()))).append("\t")
                    .append(new DecimalFormat("#.000").format(Double.parseDouble(oddInfo.getLlKlWl()) - Double.parseDouble(oddInfo.getClKlWl()))).append("\t")
                    .append(oddInfo.getWin()).append("\t")
                    .append(oddInfo.getDown()).append("\t")
                    .append(oddInfo.getLose()).append("\t")
                    .append(oddInfo.getZwin()).append("\t")
                    .append(oddInfo.getZdown()).append("\t")
                    .append(oddInfo.getZlose()).append("\t")
                    .append(oddInfo.getKwin()).append("\t")
                    .append(oddInfo.getKdown()).append("\t")
                    .append(oddInfo.getKlose()).append("\t")
                    .append(oddInfo.getZzwin()).append("\t")
                    .append(oddInfo.getZzdown()).append("\t")
                    .append(oddInfo.getZzlose()).append("\t")
                    .append(oddInfo.getKkwin()).append("\t")
                    .append(oddInfo.getKkdown()).append("\t")
                    .append(oddInfo.getKklose()).append("\t")
                    .append(oddInfo.getResult()).append("\n");
        }
        Files.write(stringBufferAm.toString(), new File("F:\\data\\lotteryodds\\train_all.txt"), Charsets.UTF_8);

    }
}
