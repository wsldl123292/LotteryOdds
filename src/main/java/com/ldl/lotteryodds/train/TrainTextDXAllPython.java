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
public class TrainTextDXAllPython {

    public static void main(String[] args) throws IOException {

        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        //1、计算出双方对应主客场的得失球平均值。
        // 2、计算两队的大概进球范围。主队进球数=主队平均进球数+客队平均失球数。客队进球数=客队平均进球数+主队平均失球数。
        // 3、二者相加即为总进球数的大概范围。
        // 4、根据双方以往交锋情况缩小范围
        /** 澳门 */
        final List<OddInfo> oddInfos = lotteryOddsDao.getOddInfos("select * from oalldx ");/*order BY result ASC LIMIT 0,31151*/
        final StringBuilder stringBuffer = new StringBuilder();
        for (OddInfo oddInfo : oddInfos) {
            int total = oddInfo.getZscore() + oddInfo.getKscore();
            stringBuffer
                    .append(oddInfo.getCwOddAm()).append("\t")
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
                    .append(oddInfo.getCpAm().replace("-", "")).append("\t")
                    .append(oddInfo.getCkWaterAm()).append("\t")
                    .append(oddInfo.getLzWaterAm()).append("\t")
                    .append(oddInfo.getLpAm().replace("-", "")).append("\t")
                    .append(oddInfo.getLkWaterAm()).append("\t")
                    .append(oddInfo.getCwOddLb()).append("\t")
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
                    .append(oddInfo.getCpLb().replace("-", "")).append("\t")
                    .append(oddInfo.getCkWaterLb()).append("\t")
                    .append(oddInfo.getLzWaterLb()).append("\t")
                    .append(oddInfo.getLpLb().replace("-", "")).append("\t")
                    .append(oddInfo.getLkWaterLb()).append("\t")
                    .append(oddInfo.getCwOddWl()).append("\t")
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







                    .append(new BigDecimal(oddInfo.getLwOddAm()).subtract(new BigDecimal(oddInfo.getCwOddAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdOddAm()).subtract(new BigDecimal(oddInfo.getCdOddAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlOddAm()).subtract(new BigDecimal(oddInfo.getClOddAm())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLwKlAm()).subtract(new BigDecimal(oddInfo.getCwKlAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdKlAm()).subtract(new BigDecimal(oddInfo.getCdKlAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlKlAm()).subtract(new BigDecimal(oddInfo.getClKlAm())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLzWaterAm()).subtract(new BigDecimal(oddInfo.getCzWaterAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLpAm()).subtract(new BigDecimal(oddInfo.getCpAm())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLkWaterAm()).subtract(new BigDecimal(oddInfo.getCkWaterAm())).toString().replace("-", "")).append("\t")



                    .append(new BigDecimal(oddInfo.getLwOddLb()).subtract(new BigDecimal(oddInfo.getCwOddLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdOddLb()).subtract(new BigDecimal(oddInfo.getCdOddLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlOddLb()).subtract(new BigDecimal(oddInfo.getClOddLb())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLwKlLb()).subtract(new BigDecimal(oddInfo.getCwKlLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdKlLb()).subtract(new BigDecimal(oddInfo.getCdKlLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlKlLb()).subtract(new BigDecimal(oddInfo.getClKlLb())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLzWaterLb()).subtract(new BigDecimal(oddInfo.getCzWaterLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLpLb()).subtract(new BigDecimal(oddInfo.getCpLb())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLkWaterLb()).subtract(new BigDecimal(oddInfo.getCkWaterLb())).toString().replace("-", "")).append("\t")




                    .append(new BigDecimal(oddInfo.getLwOddWl()).subtract(new BigDecimal(oddInfo.getCwOddWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdOddWl()).subtract(new BigDecimal(oddInfo.getCdOddWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlOddWl()).subtract(new BigDecimal(oddInfo.getClOddWl())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLwKlWl()).subtract(new BigDecimal(oddInfo.getCwKlWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLdKlWl()).subtract(new BigDecimal(oddInfo.getCdKlWl())).toString().replace("-", "")).append("\t")
                    .append(new BigDecimal(oddInfo.getLlKlWl()).subtract(new BigDecimal(oddInfo.getClKlWl())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getLose()).add(new BigDecimal(oddInfo.getDown())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getZlose()).add(new BigDecimal(oddInfo.getZdown())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getKlose()).add(new BigDecimal(oddInfo.getKdown())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getZzdown()).add(new BigDecimal(oddInfo.getZzlose())).toString().replace("-", "")).append("\t")

                    .append(new BigDecimal(oddInfo.getKkdown()).add(new BigDecimal(oddInfo.getKklose())).toString().replace("-", "")).append("\t")


                    .append(oddInfo.getZjscore()).append("\t")
                    .append(oddInfo.getZlscore()).append("\t")
                    .append(oddInfo.getKjscore()).append("\t")
                    .append(oddInfo.getKlscore()).append("\t")
                    .append(oddInfo.getZzjscore()).append("\t")
                    .append(oddInfo.getZzlscore()).append("\t")
                    .append(oddInfo.getKkjscore()).append("\t")
                    .append(oddInfo.getKklsocre()).append("\t")

                    .append(new BigDecimal(oddInfo.getlDWaterAm()).subtract(new BigDecimal(oddInfo.getcDWaterAm()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterAm()).subtract(new BigDecimal(oddInfo.getcXWaterAm()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXAm()).subtract(new BigDecimal(oddInfo.getcPDXAm()))).append("\t")

                    .append(oddInfo.getcDWaterAm()).append("\t")
                    .append(oddInfo.getcXWaterAm()).append("\t")
                    .append(oddInfo.getlDWaterAm()).append("\t")
                    .append(oddInfo.getlXWaterAm()).append("\t")
                    .append(oddInfo.getcPDXAm()).append("\t")
                    .append(oddInfo.getlPDXAm()).append("\t")
                    .append(new BigDecimal(oddInfo.getlDWaterLb()).subtract(new BigDecimal(oddInfo.getcDWaterLb()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterLb()).subtract(new BigDecimal(oddInfo.getcXWaterLb()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXLb()).subtract(new BigDecimal(oddInfo.getcPDXLb()))).append("\t")

                    .append(oddInfo.getcDWaterLb()).append("\t")
                    .append(oddInfo.getcXWaterLb()).append("\t")
                    .append(oddInfo.getlDWaterLb()).append("\t")
                    .append(oddInfo.getlXWaterLb()).append("\t")
                    .append(oddInfo.getcPDXLb()).append("\t")
                    .append(oddInfo.getlPDXLb()).append("\t")

                    .append(new BigDecimal(oddInfo.getlDWaterWl()).subtract(new BigDecimal(oddInfo.getcDWaterWl()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlXWaterWl()).subtract(new BigDecimal(oddInfo.getcXWaterWl()))).append("\t")
                    .append(new BigDecimal(oddInfo.getlPDXWl()).subtract(new BigDecimal(oddInfo.getcPDXWl()))).append("\t")
                    .append(oddInfo.getcDWaterWl()).append("\t")
                    .append(oddInfo.getcXWaterWl()).append("\t")
                    .append(oddInfo.getlDWaterWl()).append("\t")
                    .append(oddInfo.getlXWaterWl()).append("\t")
                    .append(oddInfo.getcPDXWl()).append("\t")
                    .append(oddInfo.getlPDXWl()).append("\t");

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
