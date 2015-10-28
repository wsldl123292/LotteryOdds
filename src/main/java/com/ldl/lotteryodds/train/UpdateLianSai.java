package com.ldl.lotteryodds.train;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.ldl.lotteryodds.dao.LotteryOddsDao;
import com.ldl.lotteryodds.entity.OddInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: LDL
 * 说明: 补充大小联赛信息
 * 时间: 2015/9/28 17:44
 */
public class UpdateLianSai {

    public static void main(String[] args) {

        //采集开始时间2011-07-21

        /*LocalDate beginDate = LocalDate.of(2012, 12, 31);
        final LocalDate endDate = LocalDate.of(2011, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2013, 12, 31);
        final LocalDate endDate = LocalDate.of(2012, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2015, 10, 23);
        final LocalDate endDate = LocalDate.of(2014, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2014, 12, 31);
        final LocalDate endDate = LocalDate.of(2013, 12, 31);*/

        LocalDate beginDate = LocalDate.of(2011, 12, 31);
        final LocalDate endDate = LocalDate.of(2011, 7, 20);
        int count = 0;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/?e=";

        String url;

        final List<OddInfo> oddInfos = new ArrayList<>();
        /**
         * 加载dao
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        final LotteryOddsDao lotteryOddsDao = (LotteryOddsDao) context.getBean("lotteryOddsDao");

        while (beginDate.isAfter(endDate)) {
            count++;
            System.out.println(count);
            /**
             * 采集列表页
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
                //解析赔率数据转换成map
                /**
                 * var liveOddsList={"424899":{"0":[1.35,4.71,7.7],"3":[1.36,4.75,8.0],"5":[1.4,4.3,5.85],"rqsp":[1.89,3.55,3.15],"sp":[1.25,4.90,8.50],
                 * "280":[1.45,4.7,6.6],"293":[1.36,4.8,8.5]},"424900":{"0":[1.88,3.4,3.89],"3":[1.75,3.7,4.5],
                 * "5":[1.7,3.6,4.0],"rqsp":[3.45,3.50,1.81],"sp":[1.77,3.30,3.90],"280":[1.76,3.65,4.8],
                 * "293":[1.8,3.6,4.33]},"424901":{"0":[2.52,3.21,2.65],"3":[2.75,3.3,2.5],
                 * "5":[2.45,3.25,2.5],"rqsp":[5.35,4.40,1.40],"sp":[2.50,3.10,2.50],
                 * "280":[2.7,3.35,2.56],"293":[2.6,3.4,2.62]}};
                 */
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
                final Map<String, Object> liveOddsMap = (Map<String, Object>) JSON.parse(odds.substring("var liveOddsList=".length(),
                        odds.length() - 1));
                final Map<String, Map<String, String>> oddMaps = new HashMap<>();
                for (String key : liveOddsMap.keySet()) {
                    final String value = liveOddsMap.get(key).toString().replace("\"", "").replace("[", "")
                            .replace("],", "-").replace("{", "").replace("}", "").replace("]", "");
                    final Map<String, String> join = Splitter.on("-").withKeyValueSeparator(":").split(value);
                    oddMaps.put(key, join);
                }

                //解析每个tr转换为实体
                final Elements trs = tableMatch.select("tbody>tr");
                for (Object tr : trs) {
                    final Element element = (Element) tr;
                    if (!element.attr("parentid").trim().equals("")) {
                        continue;
                    }
                    /** 比赛id */
                    final String fid = element.attr("fid");
                    final OddInfo oddInfo = new OddInfo(fid);
                    oddInfo.setDate(beginDate.toString());
                    oddInfo.setSid(element.attr("lid"));

                    oddInfos.add(oddInfo);
                }


                if (count == 5) {
                    final int size = lotteryOddsDao.batchUpdateSid(oddInfos);
                    oddInfos.clear();
                    count = 0;
                    System.out.println("更新5天的数据,共" + size + "条");
                }

                //前一天数据
                beginDate = beginDate.minusDays(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (oddInfos.size() > 0) {
            final int size = lotteryOddsDao.batchUpdateSid(oddInfos);
            System.out.println("更新剩余数据,共" + size + "条");
        }

    }

}
