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
 * 功能说明: 生成数据训练集
 * 创建日期: 2015/9/30 14:08
 */
public class TrainTextBinaryLibSVM {

    public static void main(String[] args) throws IOException {
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        /** 澳门 */
        final List<OddInfo> oddInfos = lotteryOddsDao.getOddInfos("select * from amnew");/*order BY result ASC LIMIT 0,31151*/
        final StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < oddInfos.size(); i++) {
            OddInfo oddInfo = oddInfos.get(i);
            stringBuffer.append(
                    oddInfo.getType()).append("\t")
                    .append("1:"+oddInfo.getCwOddAm()).append("\t")
                    .append("2:"+oddInfo.getCdOddAm()).append("\t")
                    .append("3:"+oddInfo.getClOddAm()).append("\t")
                    .append("4:"+oddInfo.getLwOddAm()).append("\t")
                    .append("5:"+oddInfo.getLdOddAm()).append("\t")
                    .append("6:"+oddInfo.getLlOddAm()).append("\t")
                    .append("7:"+oddInfo.getCwKlAm()).append("\t")
                    .append("8:"+oddInfo.getCdKlAm()).append("\t")
                    .append("9:"+oddInfo.getClKlAm()).append("\t")
                    .append("10:"+oddInfo.getLwKlAm()).append("\t")
                    .append("11:"+oddInfo.getLdKlAm()).append("\t")
                    .append("12:"+oddInfo.getLlKlAm()).append("\t")
                    .append("13:"+oddInfo.getCzWaterAm()).append("\t")
                    .append("14:"+oddInfo.getCpAm()).append("\t")
                    .append("15:"+oddInfo.getCkWaterAm()).append("\t")
                    .append("16:"+oddInfo.getLzWaterAm()).append("\t")
                    .append("17:"+oddInfo.getLpAm()).append("\t")
                    .append("18:"+oddInfo.getLkWaterAm()).append("\t")
                    .append("19:" + oddInfo.getWin()).append("\t")
                    .append("20:"+oddInfo.getDown()).append("\t")
                    .append("21:"+oddInfo.getLose()).append("\t")
                    .append("22:"+oddInfo.getZwin()).append("\t")
                    .append("23:"+oddInfo.getZdown()).append("\t")
                    .append("24:"+oddInfo.getZlose()).append("\t")
                    .append("25:"+oddInfo.getKwin()).append("\t")
                    .append("26:"+oddInfo.getKdown()).append("\t")
                    .append("27:"+oddInfo.getKlose()).append("\t")
                    .append("28:"+oddInfo.getZzwin()).append("\t")
                    .append("29:"+oddInfo.getZzdown()).append("\t")
                    .append("30:"+oddInfo.getZzlose()).append("\t")
                    .append("31:"+oddInfo.getKkwin()).append("\t")
                    .append("32:"+oddInfo.getKkdown()).append("\t")
                    .append("33:"+oddInfo.getKklose()).append("\n");



                /*oddInfo.getType()).append("\t")
                        .append("1:"+oddInfo.getCwOddAm()).append("\t")
                        .append("2:"+oddInfo.getCdOddAm()).append("\t")
                        .append("3:"+oddInfo.getClOddAm()).append("\t")
                        .append("4:"+oddInfo.getLwOddAm()).append("\t")
                        .append("5:"+oddInfo.getLdOddAm()).append("\t")
                        .append("6:"+oddInfo.getLlOddAm()).append("\t")
                        .append("7:"+oddInfo.getCwKlAm()).append("\t")
                        .append("8:"+oddInfo.getCdKlAm()).append("\t")
                        .append("9:"+oddInfo.getClKlAm()).append("\t")
                        .append("10:"+oddInfo.getLwKlAm()).append("\t")
                        .append("11:"+oddInfo.getLdKlAm()).append("\t")
                        .append("12:"+oddInfo.getLlKlAm()).append("\t")
                        .append("13:"+oddInfo.getCzWaterAm()).append("\t")
                        .append("14:"+oddInfo.getCpAm()).append("\t")
                        .append("15:"+oddInfo.getCkWaterAm()).append("\t")
                        .append("16:"+oddInfo.getLzWaterAm()).append("\t")
                        .append("17:"+oddInfo.getLpAm()).append("\t")
                        .append("18:"+oddInfo.getLkWaterAm()).append("\t")
                        .append("19:"+oddInfo.getCwOddLb()).append("\t")
                        .append("20:"+oddInfo.getCdOddLb()).append("\t")
                        .append("21:"+oddInfo.getClOddLb()).append("\t")
                        .append("22:"+oddInfo.getLwOddLb()).append("\t")
                        .append("23:"+oddInfo.getLdOddLb()).append("\t")
                        .append("24:"+oddInfo.getLlOddLb()).append("\t")
                        .append("25:"+oddInfo.getCwKlLb()).append("\t")
                        .append("26:"+oddInfo.getCdKlLb()).append("\t")
                        .append("27:"+oddInfo.getClKlLb()).append("\t")
                        .append("28:"+oddInfo.getLwKlLb()).append("\t")
                        .append("29:"+oddInfo.getLdKlLb()).append("\t")
                        .append("30:"+oddInfo.getLlKlLb()).append("\t")
                        .append("31:"+oddInfo.getCzWaterLb()).append("\t")
                        .append("32:"+oddInfo.getCpLb()).append("\t")
                        .append("33:"+oddInfo.getCkWaterLb()).append("\t")
                        .append("34:"+oddInfo.getLzWaterLb()).append("\t")
                        .append("35:"+oddInfo.getLpLb()).append("\t")
                        .append("36:"+oddInfo.getLkWaterLb()).append("\t")
                        .append("37:"+oddInfo.getCwOddWl()).append("\t")
                        .append("38:"+oddInfo.getCdOddWl()).append("\t")
                        .append("39:"+oddInfo.getClOddWl()).append("\t")
                        .append("40:"+oddInfo.getLwOddWl()).append("\t")
                        .append("41:"+oddInfo.getLdOddWl()).append("\t")
                        .append("42:"+oddInfo.getLlOddWl()).append("\t")
                        .append("43:"+oddInfo.getCwKlWl()).append("\t")
                        .append("44:"+oddInfo.getCdKlWl()).append("\t")
                        .append("45:"+oddInfo.getClKlWl()).append("\t")
                        .append("46:"+oddInfo.getLwKlWl()).append("\t")
                        .append("47:"+oddInfo.getLdKlWl()).append("\t")
                        .append("48:"+oddInfo.getLlKlWl()).append("\t")
                        .append("49:"+oddInfo.getWin()).append("\t")
                        .append("50:"+oddInfo.getDown()).append("\t")
                        .append("51:"+oddInfo.getLose()).append("\t")
                        .append("52:"+oddInfo.getZwin()).append("\t")
                        .append("53:"+oddInfo.getZdown()).append("\t")
                        .append("54:"+oddInfo.getZlose()).append("\t")
                        .append("55:"+oddInfo.getKwin()).append("\t")
                        .append("56:"+oddInfo.getKdown()).append("\t")
                        .append("57:"+oddInfo.getKlose()).append("\t")
                        .append("58:"+oddInfo.getZzwin()).append("\t")
                        .append("59:"+oddInfo.getZzdown()).append("\t")
                        .append("60:"+oddInfo.getZzlose()).append("\t")
                        .append("61:"+oddInfo.getKkwin()).append("\t")
                        .append("62:"+oddInfo.getKkdown()).append("\t")
                        .append("63:"+oddInfo.getKklose()).append("\n");*/
        }
        Files.write(stringBuffer.toString(), new File("F:\\data\\train_all_binary_libsvm.txt"), Charsets.UTF_8);

    }
}
