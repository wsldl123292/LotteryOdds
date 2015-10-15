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
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: LDL
 * 说明: 生成待预测文本新版
 * 时间: 2015/10/1 20:51
 */
public class TestTextDXAll {
    public static void main(String[] args) throws IOException {
//采集开始时间2011-07-21
        LocalDate beginDate = LocalDate.of(2015, 10, 13);
        int size = 14;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/?e=";
        /** 大小指数数据地址 */
        final String daxiaoUrl = "http://odds.500.com/fenxi/daxiao-";
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
            for (int i = 0; i < trs.size(); i++) {
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
                //获取双方交战记录
                /*final Elements hits = shuju.select(".his_info");
                Element hit = hits.first();
                Elements f16 = hit.select(".f16");
                if (f16 != null && f16.size() > 0) {
                    oddInfo.setWin(f16.select(".red").get(0).text().replace("胜", ""));
                    oddInfo.setDown(f16.select(".green").get(0).text().replace("平", ""));
                    oddInfo.setLose(f16.select(".blue").get(0).text().replace("负", ""));
                }*/
                //双方战绩
                final Elements zhanjis = shuju.select(".bottom_info");
                /** 主队近10场 */
                Elements z = zhanjis.get(0).select(".mar_left20");
                if (z != null && z.size() > 0) {
                    /*oddInfo.setZwin(z.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setZdown(z.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setZlose(z.get(0).select(".shu").get(0).text().replace("负", ""));*/

                    oddInfo.setZjscore(Integer.parseInt(z.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZlscore(Integer.parseInt(z.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 客队近10场 */
                Elements k = zhanjis.get(1).select(".mar_left20");
                if (k != null && k.size() > 0) {
                    /*oddInfo.setKwin(k.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setKdown(k.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setKlose(k.get(0).select(".shu").get(0).text().replace("负", ""));*/

                    oddInfo.setKjscore(Integer.parseInt(k.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKlscore(Integer.parseInt(k.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 主队近10主场 */
                Elements zz = zhanjis.get(2).select(".mar_left20");
                if (zz != null && zz.size() > 0) {
                    /*oddInfo.setZzwin(zz.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setZzdown(zz.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setZzlose(zz.get(0).select(".shu").get(0).text().replace("负", ""));*/

                    oddInfo.setZzjscore(Integer.parseInt(zz.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZzlscore(Integer.parseInt(zz.get(1).select(".shu").get(0).text().replace("球", "")));
                }

                /** 客队近10客场 */
                Elements kk = zhanjis.get(3).select(".mar_left20");
                if (kk != null && kk.size() > 0) {
                    /*oddInfo.setKkwin(kk.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setKkdown(kk.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setKklose(kk.get(0).select(".shu").get(0).text().replace("负", ""));*/

                    oddInfo.setKkjscore(Integer.parseInt(kk.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKklsocre(Integer.parseInt(kk.get(1).select(".shu").get(0).text().replace("球", "")));
                }

                /** 采集数据地址 http://odds.500.com/fenxi/daxiao-331954.shtml */
                url = daxiaoUrl + fid + ".shtml";
                get = new HttpGet(url);
                get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                response = client.execute(get);
                final String body = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                final Document bodyContent = Jsoup.parse(body);
                //获取数据表格
                final Element datatb = bodyContent.getElementById("datatb");
                final Elements dxtrs = datatb.select("tbody>tr").select("[xls=row]");
                for (Element dxtr : dxtrs) {
                    if (dxtr.attr("id").equals("5")) {
                        //澳门盘口信息
                        final Elements pks = dxtr.select(".pl_table_data");
                        //最终盘口
                        final Elements lpktds = pks.get(0).select("tbody>tr>td");
                        oddInfo.setlDWaterAm(lpktds.get(0).text().replace("↑", "").replace("↓", ""));
                        oddInfo.setlPDXAm(lpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setlXWaterAm(lpktds.get(2).text().replace("↑", "").replace("↓", ""));

                        //初始盘口
                        final Elements cpktds = pks.get(1).select("tbody>tr>td");
                        oddInfo.setcDWaterAm(cpktds.get(0).text());
                        oddInfo.setcPDXAm(cpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setcXWaterAm(cpktds.get(2).text());
                    } else if (dxtr.attr("id").equals("2")) {
                        //立博盘口信息
                        final Elements pks = dxtr.select(".pl_table_data");
                        //最终盘口
                        final Elements lpktds = pks.get(0).select("tbody>tr>td");
                        oddInfo.setlDWaterLb(lpktds.get(0).text().replace("↑", "").replace("↓", ""));
                        oddInfo.setlPDXLb(lpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setlXWaterLb(lpktds.get(2).text().replace("↑", "").replace("↓", ""));

                        //初始盘口
                        final Elements cpktds = pks.get(1).select("tbody>tr>td");
                        oddInfo.setcDWaterLb(cpktds.get(0).text());
                        oddInfo.setcPDXLb(cpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setcXWaterLb(cpktds.get(2).text());
                    } else if (dxtr.attr("id").equals("293")) {
                        //威廉希尔盘口信息
                        final Elements pks = dxtr.select(".pl_table_data");
                        //最终盘口
                        final Elements lpktds = pks.get(0).select("tbody>tr>td");
                        oddInfo.setlDWaterWl(lpktds.get(0).text().replace("↑", "").replace("↓", ""));
                        oddInfo.setlPDXWl(lpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setlXWaterWl(lpktds.get(2).text().replace("↑", "").replace("↓", ""));

                        //初始盘口
                        final Elements cpktds = pks.get(1).select("tbody>tr>td");
                        oddInfo.setcDWaterWl(cpktds.get(0).text());
                        oddInfo.setcPDXWl(cpktds.get(1).attr("ref").replace("-", ""));
                        oddInfo.setcXWaterWl(cpktds.get(2).text());
                    }
                }
                oddInfos.add(oddInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (oddInfos.size() > 0) {
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

                        /*.append(new BigDecimal(oddInfo.getlDWaterAm() == null ? "0" : oddInfo.getlDWaterAm()).subtract(new BigDecimal(oddInfo.getcDWaterAm() == null ? "0" : oddInfo.getcDWaterAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlXWaterAm() == null ? "0" : oddInfo.getlXWaterAm()).subtract(new BigDecimal(oddInfo.getcXWaterAm() == null ? "0" : oddInfo.getcXWaterAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlPDXAm() == null ? "0" : oddInfo.getlPDXAm()).subtract(new BigDecimal(oddInfo.getcPDXAm() == null ? "0" : oddInfo.getcPDXAm()))).append("\t")*/

                        .append(oddInfo.getcDWaterAm()).append("\t")
                        .append(oddInfo.getcXWaterAm()).append("\t")
                        .append(oddInfo.getlDWaterAm()).append("\t")
                        .append(oddInfo.getlXWaterAm()).append("\t")
                        .append(oddInfo.getcPDXAm()).append("\t")
                        .append(oddInfo.getlPDXAm()).append("\t")
                        /*.append(new BigDecimal(oddInfo.getlDWaterLb() == null ? "0" : oddInfo.getlDWaterLb()).subtract(new BigDecimal(oddInfo.getcDWaterLb() == null ? "0" : oddInfo.getcDWaterLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlXWaterLb() == null ? "0" : oddInfo.getlXWaterLb()).subtract(new BigDecimal(oddInfo.getcXWaterLb() == null ? "0" : oddInfo.getcXWaterLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlPDXLb() == null ? "0" : oddInfo.getlPDXLb()).subtract(new BigDecimal(oddInfo.getcPDXLb() == null ? "0" : oddInfo.getcPDXLb()))).append("\t")*/

                        .append(oddInfo.getcDWaterLb()).append("\t")
                        .append(oddInfo.getcXWaterLb()).append("\t")
                        .append(oddInfo.getlDWaterLb()).append("\t")
                        .append(oddInfo.getlXWaterLb()).append("\t")
                        .append(oddInfo.getcPDXLb()).append("\t")
                        .append(oddInfo.getlPDXLb()).append("\t")

                        /*.append(new BigDecimal(oddInfo.getlDWaterWl() == null ? "0" : oddInfo.getlDWaterWl()).subtract(new BigDecimal(oddInfo.getcDWaterWl() == null ? "0" : oddInfo.getcDWaterWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlXWaterWl() == null ? "0" : oddInfo.getlXWaterWl()).subtract(new BigDecimal(oddInfo.getcXWaterWl() == null ? "0" : oddInfo.getcXWaterWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getlPDXWl() == null ? "0" : oddInfo.getlPDXWl()).subtract(new BigDecimal(oddInfo.getcPDXWl() == null ? "0" : oddInfo.getcPDXWl()))).append("\t")*/
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
                //stringBuffer.append(2).append("\n");
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
            Files.write(stringBuffer.toString(), new File("F:\\data\\lotteryodds\\test_dx_all.txt"), Charsets.UTF_8);

        }
    }
}
