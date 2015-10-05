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
public class TrainTextNew {

    public static void main(String[] args) throws IOException {
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        /** 澳门 */
        final List<OddInfo> oddInfosAm = lotteryOddsDao.getOddInfos("select * from amnew ");/*order BY result ASC LIMIT 0,31151*/
        final StringBuilder stringBufferAm = new StringBuilder();
        for (OddInfo oddInfo : oddInfosAm) {
            stringBufferAm.append(oddInfo.getCwOddAm()).append("\t")
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
                    .append(oddInfo.getLkWaterAm()).append("\t")
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
        Files.write(stringBufferAm.toString(), new File("F:\\data\\lotteryodds\\train_am_new.txt"), Charsets.UTF_8);



        /** 立博 */
        final List<OddInfo> oddInfosLb = lotteryOddsDao.getOddInfos("select * from lbnew ");/*order BY result ASC LIMIT 0,21611*/
        final StringBuilder stringBufferLb = new StringBuilder();
        for (OddInfo oddInfo : oddInfosLb) {
            stringBufferLb.append(oddInfo.getCwOddLb()).append("\t")
                    .append(oddInfo.getCdOddLb()).append("\t")
                    .append(oddInfo.getClOddLb()).append("\t")
                    .append(oddInfo.getLwOddLb()).append("\t")
                    .append(oddInfo.getLdOddLb()).append("\t")
                    .append(oddInfo.getLlOddLb()).append("\t")
                    .append(oddInfo.getCwKlLb()).append("\t")
                    .append(oddInfo.getCdKlLb()).append("\t")
                    .append(oddInfo.getClKlLb()).append("\t")
                    .append(oddInfo.getLwKlLb()).append("\t")
                    .append(oddInfo.getLdKlLb()).append("\t")
                    .append(oddInfo.getLlKlLb()).append("\t")
                    .append(oddInfo.getCzWaterLb()).append("\t")
                    .append(oddInfo.getCpLb()).append("\t")
                    .append(oddInfo.getCkWaterLb()).append("\t")
                    .append(oddInfo.getLzWaterLb()).append("\t")
                    .append(oddInfo.getLpLb()).append("\t")
                    .append(oddInfo.getLkWaterLb()).append("\t")
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
        Files.write(stringBufferLb.toString(), new File("F:\\data\\lotteryodds\\train_lb_new.txt"), Charsets.UTF_8);




        /** 威廉希尔 */
        final List<OddInfo> oddInfosWl = lotteryOddsDao.getOddInfos("select * from wlnew ");/*order BY result ASC LIMIT 0,31052*/
        final StringBuilder stringBufferWl = new StringBuilder();
        for (OddInfo oddInfo : oddInfosWl) {
            stringBufferWl.append(oddInfo.getCwOddWl()).append("\t")
                    .append(oddInfo.getCdOddWl()).append("\t")
                    .append(oddInfo.getClOddWl()).append("\t")
                    .append(oddInfo.getLwOddWl()).append("\t")
                    .append(oddInfo.getLdOddWl()).append("\t")
                    .append(oddInfo.getLlOddWl()).append("\t")
                    .append(oddInfo.getCwKlWl()).append("\t")
                    .append(oddInfo.getCdKlWl()).append("\t")
                    .append(oddInfo.getClKlWl()).append("\t")
                    .append(oddInfo.getLwKlWl()).append("\t")
                    .append(oddInfo.getLdKlWl()).append("\t")
                    .append(oddInfo.getLlKlWl()).append("\t")
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
        Files.write(stringBufferWl.toString(), new File("F:\\data\\lotteryodds\\train_wl_new.txt"), Charsets.UTF_8);

    }
}
