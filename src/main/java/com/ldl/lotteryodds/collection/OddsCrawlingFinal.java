package com.ldl.lotteryodds.collection;

import com.ldl.lotteryodds.entity.OddInfo;
import com.ldl.lotteryodds.entity.OddInfoFinal;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spire.random.DistRng;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 欧赔初盘采集 完场比分
 */
public class OddsCrawlingFinal {

    private static Map<String,String> company;
    static {
        company = new HashMap<>();
        company.put("")
    }
    //2014-04-23  格式大变   2011-05-03开始时间
    public static void main(String[] args) {

        //采集开始时间2011-07-21
        LocalDate beginDate = LocalDate.of(2011, 6, 2);
        final LocalDate endDate = LocalDate.of(2011, 6, 1);

        /*LocalDate beginDate = LocalDate.of(2015, 10, 23);
        final LocalDate endDate = LocalDate.of(2015, 10, 12);*/

        /*LocalDate beginDate = LocalDate.of(2013, 1, 1);
        final LocalDate endDate = LocalDate.of(2012, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2012, 1, 1);
        final LocalDate endDate = LocalDate.of(2011, 12, 31);*/
        int count = 0;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/wanchang.php?e=";
        /** 欧赔地址 */
        final String oupeiUrl = "http://odds.500.com/fenxi/ouzhi-";
        String url;

        final List<OddsCrawlingFinal> oddInfos = new ArrayList<>();
        /**
         * 加载dao
         */
        /*final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");*/

        while (beginDate.isAfter(endDate)) {
            count++;
            System.out.println(count);
            /*
              采集列表页
             */
            url = baseurl + beginDate.toString();
            System.out.println("开始采集" + beginDate.toString() + "的数据");
            try {
                get = new HttpGet(url);
                get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                response = client.execute(get);
                final String bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                final Document document = Jsoup.parse(bodyAsString);
                //获取比赛表格
                final Element tableMatch = document.getElementById("table_match");

                //解析每个tr转换为实体
                final Elements trs = tableMatch.select("tbody>tr");
                for (Object tr : trs) {
                    final Element element = (Element) tr;
                    /* 比赛id */
                    final String fid = element.attr("id").replace("a", "");
                    final OddInfoFinal oddInfo = new OddInfoFinal(fid);
                    /* 比分 */
                    final Elements socre = element.select(".pk");
                    /* 避免出现无比分情况 */
                    if (!"".equals(socre.select(".clt1").text().trim())) {
                        oddInfo.setZscore(Integer.parseInt(socre.select(".clt1").text()));
                        oddInfo.setKscore(Integer.parseInt(socre.select(".clt3").text()));
                        oddInfo.setResult(oddInfo.getZscore(), oddInfo.getKscore());
                    }
                    System.out.println(oddInfo);

                    /* 采集欧赔地址 http://odds.500.com/fenxi/ouzhi-331954.shtml */
                    url = oupeiUrl + fid + ".shtml";
                    get = new HttpGet(url);
                    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                    response = client.execute(get);
                    final String oupeiBody = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                    final Document oupei = Jsoup.parse(oupeiBody);
                    //获取欧赔数据表格
                    final Element opDatatb = oupei.getElementById("datatb");
                    final Elements optrs = opDatatb.select("tbody>tr").select("[xls=row]");
                    for (Element optr : optrs) {
                        if ("5".equals(optr.attr("id"))) {
                            //澳门欧赔信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOdd(cop.get(0).text());
                            oddInfo.setCdOdd(cop.get(1).text());
                            oddInfo.setClOdd(cop.get(2).text());
                        } else if ("2".equals(optr.attr("id"))) {
                            //立博赔率信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOddLb(cop.get(0).text());
                            oddInfo.setCdOddLb(cop.get(1).text());
                            oddInfo.setClOddLb(cop.get(2).text());
                        } else if ("293".equals(optr.attr("id"))) {
                            //威廉希尔赔率信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOddWl(cop.get(0).text());
                            oddInfo.setCdOddWl(cop.get(1).text());
                            oddInfo.setClOddWl(cop.get(2).text());
                        }
                    }

                    oddInfos.add(oddInfo);
                }

                /*if (count == 5) {
                    final int size = lotteryOddsDao.batchInsertOddInfo(oddInfos);
                    oddInfos.clear();
                    count = 0;
                    System.out.println("保存5天的数据,共" + size + "条");
                }*/

                //前一天数据
                beginDate = beginDate.minusDays(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*if (oddInfos.size() > 0) {
            final int size = lotteryOddsDao.batchInsertOddInfo(oddInfos);
            System.out.println("保存剩余数据,共" + size + "条");
        }*/

    }
}
