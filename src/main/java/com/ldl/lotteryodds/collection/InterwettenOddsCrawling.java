package com.ldl.lotteryodds.collection;

import com.google.common.base.Splitter;
import com.ldl.lotteryodds.dao.LotteryOddsDao;
import com.ldl.lotteryodds.entity.InterwettenOdd;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: LDL
 * 说明: interwetten半全场 和 亚盘 比分
 * 时间: 2015/9/28 17:44
 */
public class InterwettenOddsCrawling {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //采集开始时间2011-07-21
        LocalDate beginDate = LocalDate.of(2012, 12, 31);
        final LocalDate endDate = LocalDate.of(2011, 7, 20);

        /*LocalDate beginDate = LocalDate.of(2014, 12, 6);
        final LocalDate endDate = LocalDate.of(2013, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2016, 12, 31);
        final LocalDate endDate = LocalDate.of(2015, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2015, 12, 31);
        final LocalDate endDate = LocalDate.of(2014, 12, 31);*/
        int count = 0;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /* 列表地址 */
        final String baseurl = "http://live.500.com/?e=";
        /* 半全场数据地址 */
        final String dxUrl = "http://odds.500.com/fenxi/bqc-";
        String url;

        final List<InterwettenOdd> oddInfos = new ArrayList<>();
        /*
          加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

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
                String odds = "";
                final Elements scriptElements = document.getElementsByTag("script");
                for (Object scriptElement : scriptElements) {
                    final Element element = (Element) scriptElement;
                    if (element.html().contains("liveOddsList")) {
                        odds = element.html();
                        break;
                    }
                }
                if (odds.equals("")) {
                    //前一天数据
                    beginDate = beginDate.minusDays(1);
                    continue;
                }

                //解析每个tr转换为实体
                final Elements trs = tableMatch.select("tbody>tr");
                for (Object tr : trs) {
                    final Element element = (Element) tr;
                    if (!element.attr("parentid").trim().equals("")) {
                        continue;
                    }
                    /* 比赛id */
                    final String fid = element.attr("fid");
                    final InterwettenOdd oddInfo = new InterwettenOdd(fid);
                    oddInfo.setDate(beginDate.toString());
                    oddInfo.setSid(element.attr("lid"));
                    final String names = element.attr("gy");
                    final List<String> strings = Splitter.on(",").splitToList(names);
                    if (strings.size() == 3) {
                        oddInfo.setzName(strings.get(1));
                        oddInfo.setkName(strings.get(2));
                    }
                    /* 比分 */
                    final Elements socre = element.select(".pk");
                    /* 避免出现无比分情况 */
                    if (!socre.select(".clt1").text().trim().equals("")) {
                        oddInfo.setZscore(Integer.parseInt(socre.select(".clt1").text()));
                        oddInfo.setKscore(Integer.parseInt(socre.select(".clt3").text()));
                        oddInfo.setCp(socre.select(".fhuise").text());
                        oddInfo.setResult(oddInfo.getZscore(), oddInfo.getKscore());
                    }


                    /* 采集数据地址 http://odds.500.com/fenxi/bqc-331954.shtml */
                    url = dxUrl + fid + ".shtml";
                    get = new HttpGet(url);
                    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                    response = client.execute(get);
                    final String body = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                    final Document bodyContent = Jsoup.parse(body);
                    if (!bodyContent.text().contains("Interwetten")) {
                        continue;
                    }
                    //获取数据表格
                    final Element datatb = bodyContent.select(".pub_table").first();
                    final Elements dxtrs = datatb.select("tbody>tr");
                    for (int i = 1; i < 10; i++) {
                        Element dxtr = dxtrs.get(i);
                        if (dxtr.text().contains("Interwetten")) {
                            Elements tds = dxtr.children();
                            oddInfo.setWw(tds.get(2).text());
                            oddInfo.setWd(tds.get(3).text());
                            oddInfo.setWl(tds.get(4).text());
                            oddInfo.setDw(tds.get(5).text());
                            oddInfo.setDd(tds.get(6).text());
                            oddInfo.setDl(tds.get(7).text());
                            oddInfo.setLw(tds.get(8).text());
                            oddInfo.setLd(tds.get(9).text());
                            oddInfo.setLl(tds.get(10).text());

                        }
                    }


                    oddInfos.add(oddInfo);
                }

                if (count == 5) {
                    final int size = lotteryOddsDao.batchInterwettenOdd(oddInfos);
                    oddInfos.clear();
                    count = 0;
                    System.out.println("保存5天的数据,共" + size + "条");
                }

                //前一天数据
                beginDate = beginDate.minusDays(1);
            } catch (IOException e) {
                System.out.println("采集到" + beginDate + "出错");
                e.printStackTrace();
            }
        }

        if (oddInfos.size() > 0) {
            try {
                final int size = lotteryOddsDao.batchInterwettenOdd(oddInfos);
                System.out.println("保存剩余数据,共" + size + "条");
            } catch (Exception e) {
                System.out.println("采集到" + beginDate + "出错");
                e.printStackTrace();
            }
        }

    }

}
