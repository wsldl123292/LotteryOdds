package com.ldl.lotteryodds.train;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

/**
 * 作者: LDL
 * 说明: 生成待预测文本新版
 * 时间: 2015/10/1 20:51
 */
public class DXAll {
    public static void main(String[] args) throws IOException {
//采集开始时间2011-07-21
        LocalDate beginDate = LocalDate.of(2015, 10, 16);
        int size = 1;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/?e=";
        /** 数据地址 */
        final String shujuUrl = "http://odds.500.com/fenxi/shuju-";
        String url;

        final List<OddInfo> oddInfos = new ArrayList<>();

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
            for (int i = 0; i < size; i++) {
                //for (Object tr : trs) {
                final Element element = trs.get(i);
                if (!element.attr("parentid").trim().equals("")) {
                    continue;
                }
                /** 比赛id */
                final String fid = element.attr("fid");
                final OddInfo oddInfo = new OddInfo(fid);
                oddInfo.setDate(beginDate.toString());

                /** 比分 */
                final Elements socre = element.select(".pk");
                /** 避免出现无比分情况 */
                if (!socre.select(".clt1").text().trim().equals("")) {
                    oddInfo.setZscore(Integer.parseInt(socre.select(".clt1").text()));
                    oddInfo.setKscore(Integer.parseInt(socre.select(".clt3").text()));
                    oddInfo.setResult(oddInfo.getZscore(), oddInfo.getKscore());
                }
                /** 采集数据地址 http://odds.500.com/fenxi/shuju-331954.shtml */
                url = shujuUrl + fid + ".shtml";
                get = new HttpGet(url);
                get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                response = client.execute(get);
                final String shujunBody = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                final Document shuju = Jsoup.parse(shujunBody);
                //双方战绩
                final Elements zhanjis = shuju.select(".bottom_info");
                /** 主队近10场 */
                Elements z = zhanjis.get(0).select(".mar_left20");
                if (z != null && z.size() > 0) {

                    oddInfo.setZjscore(Integer.parseInt(z.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZlscore(Integer.parseInt(z.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 客队近10场 */
                Elements k = zhanjis.get(1).select(".mar_left20");
                if (k != null && k.size() > 0) {

                    oddInfo.setKjscore(Integer.parseInt(k.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKlscore(Integer.parseInt(k.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 主队近10主场 */
                Elements zz = zhanjis.get(2).select(".mar_left20");
                if (zz != null && zz.size() > 0) {

                    oddInfo.setZzjscore(Integer.parseInt(zz.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZzlscore(Integer.parseInt(zz.get(1).select(".shu").get(0).text().replace("球", "")));
                }

                /** 客队近10客场 */
                Elements kk = zhanjis.get(3).select(".mar_left20");
                if (kk != null && kk.size() > 0) {

                    oddInfo.setKkjscore(Integer.parseInt(kk.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKklsocre(Integer.parseInt(kk.get(1).select(".shu").get(0).text().replace("球", "")));
                }

                oddInfos.add(oddInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (oddInfos.size() > 0) {
            final StringBuilder stringBuffer = new StringBuilder();
            for (OddInfo oddInfo : oddInfos) {
                BigDecimal svgzjscore = new BigDecimal(oddInfo.getZjscore()).divide(new BigDecimal(10));
                BigDecimal svgzlscore = new BigDecimal(oddInfo.getZlscore()).divide(new BigDecimal(10));
                BigDecimal svgkjscore = new BigDecimal(oddInfo.getKjscore()).divide(new BigDecimal(10));
                BigDecimal svgklscore = new BigDecimal(oddInfo.getKlscore()).divide(new BigDecimal(10));
                List<Integer> zrange = getScoreRange((svgzjscore.add(svgklscore)).divide(new BigDecimal(2)));
                List<Integer> krange = getScoreRange((svgzlscore.add(svgkjscore)).divide(new BigDecimal(2)));
                stringBuffer.append(Math.min(zrange.get(0),krange.get(0))).append("~").append(Math.max(zrange.get(1),krange.get(1))).append("\n");
            }
            Files.write(stringBuffer.toString(), new File("F:\\data\\lotteryodds\\dx_all.txt"), Charsets.UTF_8);

        }
    }

    public static List<Integer> getScoreRange(BigDecimal bigDecimal) {
        List<Integer> list = new ArrayList<>();
        if (bigDecimal.compareTo(new BigDecimal(1)) < 0) {
            list.add(0);
            list.add(1);
        } else if (bigDecimal.compareTo(new BigDecimal(2)) < 0) {
            list.add(1);
            list.add(2);
        } else if (bigDecimal.compareTo(new BigDecimal(3)) < 0) {
            list.add(2);
            list.add(3);
        } else if (bigDecimal.compareTo(new BigDecimal(4)) < 0) {
            list.add(3);
            list.add(4);
        } else if (bigDecimal.compareTo(new BigDecimal(5)) < 0) {
            list.add(4);
            list.add(5);
        } else if (bigDecimal.compareTo(new BigDecimal(6)) < 0) {
            list.add(5);
            list.add(6);
        } else if (bigDecimal.compareTo(new BigDecimal(7)) < 0) {
            list.add(6);
            list.add(7);
        } else {
            list.add(7);
            list.add(8);
        }
        return list;
    }
}
