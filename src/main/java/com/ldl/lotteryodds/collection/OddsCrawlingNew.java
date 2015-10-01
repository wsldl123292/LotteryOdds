package com.ldl.lotteryodds.collection;

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
 * 说明: 数据采集 新版本 采集欧赔和亚盘页面  立博，威廉希尔，澳门，和平均值
 * 时间: 2015/9/28 17:44
 */
public class OddsCrawlingNew {

    public static void main(String[] args) {

        //采集开始时间2011-07-21
        /*LocalDate beginDate = LocalDate.of(2011, 7, 21);
        final LocalDate endDate = LocalDate.of(2011, 7, 20);*/

        LocalDate beginDate = LocalDate.of(2015, 9, 30);
        final LocalDate endDate = LocalDate.of(2015, 9, 29);

        /*LocalDate beginDate = LocalDate.of(2013, 1, 1);
        final LocalDate endDate = LocalDate.of(2012, 12, 31);*/

        /*LocalDate beginDate = LocalDate.of(2012, 1, 1);
        final LocalDate endDate = LocalDate.of(2011, 12, 31);*/
        int count = 0;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/?e=";
        /** 亚盘地址 */
        final String yapanUrl = "http://odds.500.com/fenxi/yazhi-";
        /** 欧赔地址 */
        final String oupeiUrl = "http://odds.500.com/fenxi/ouzhi-";

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

                    /** 比分 */
                    final Elements socre = element.select(".pk");
                    /** 避免出现无比分情况 */
                    if (!socre.select(".clt1").text().trim().equals("")) {
                        oddInfo.setZscore(Integer.parseInt(socre.select(".clt1").text()));
                        oddInfo.setKscore(Integer.parseInt(socre.select(".clt3").text()));
                        oddInfo.setResult(oddInfo.getZscore(), oddInfo.getKscore());
                    }

                    /** 采集亚盘地址 http://odds.500.com/fenxi/yazhi-331954.shtml */
                    url = yapanUrl + fid + ".shtml";
                    get = new HttpGet(url);
                    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                    response = client.execute(get);
                    final String yapanBody = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                    final Document yanpan = Jsoup.parse(yapanBody);
                    //获取亚盘数据表格
                    final Element ypDatatb = yanpan.getElementById("datatb");
                    final Elements yptrs = ypDatatb.select("tbody>tr").select("[xls=row]");
                    for (Element yptr : yptrs) {
                        if (yptr.attr("id").equals("5")) {
                            //澳门盘口信息
                            final Elements pks = yptr.select(".pl_table_data");
                            //最终盘口
                            final Elements lpktds = pks.get(0).select("tbody>tr>td");
                            oddInfo.setLzWaterAm(lpktds.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLpAm(lpktds.get(1).attr("ref"));
                            oddInfo.setLkWaterAm(lpktds.get(2).text().replace("↑", "").replace("↓", ""));

                            //初始盘口
                            final Elements cpktds = pks.get(1).select("tbody>tr>td");
                            oddInfo.setCzWaterAm(cpktds.get(0).text());
                            oddInfo.setCpAm(cpktds.get(1).attr("ref"));
                            oddInfo.setCkWaterAm(cpktds.get(2).text());
                        } else if (yptr.attr("id").equals("2")) {
                            //立博盘口信息
                            final Elements pks = yptr.select(".pl_table_data");
                            //最终盘口
                            final Elements lpktds = pks.get(0).select("tbody>tr>td");
                            oddInfo.setLzWaterLb(lpktds.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLpLb(lpktds.get(1).attr("ref"));
                            oddInfo.setLkWaterLb(lpktds.get(2).text().replace("↑", "").replace("↓", ""));

                            //初始盘口
                            final Elements cpktds = pks.get(1).select("tbody>tr>td");
                            oddInfo.setCzWaterLb(cpktds.get(0).text());
                            oddInfo.setCpLb(cpktds.get(1).attr("ref"));
                            oddInfo.setCkWaterLb(cpktds.get(2).text());
                        }
                    }


                    /** 采集欧赔地址 http://odds.500.com/fenxi/ouzhi-331954.shtml */
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
                        if (optr.attr("id").equals("5")) {
                            //澳门欧赔信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOddAm(cop.get(0).text());
                            oddInfo.setCdOddAm(cop.get(1).text());
                            oddInfo.setClOddAm(cop.get(2).text());

                            //最终赔率
                            final Elements lop = cpktrs.get(1).select("td");
                            oddInfo.setLwOddAm(lop.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdOddAm(lop.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlOddAm(lop.get(2).text().replace("↑", "").replace("↓", ""));


                            //凯利指数
                            final Elements klktrs = pks.get(3).select("tbody>tr");
                            //初始凯利指数
                            final Elements ckl = klktrs.get(0).select("td");
                            oddInfo.setCwKlAm(ckl.get(0).text());
                            oddInfo.setCdKlAm(ckl.get(1).text());
                            oddInfo.setClKlAm(ckl.get(2).text());

                            //最终凯利指数
                            final Elements lkl = klktrs.get(1).select("td");
                            oddInfo.setLwKlAm(lkl.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdKlAm(lkl.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlKlAm(lkl.get(2).text().replace("↑", "").replace("↓", ""));
                        } else if (optr.attr("id").equals("2")) {
                            //立博赔率信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOddLb(cop.get(0).text());
                            oddInfo.setCdOddLb(cop.get(1).text());
                            oddInfo.setClOddLb(cop.get(2).text());

                            //最终赔率
                            final Elements lop = cpktrs.get(1).select("td");
                            oddInfo.setLwOddLb(lop.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdOddLb(lop.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlOddLb(lop.get(2).text().replace("↑", "").replace("↓", ""));


                            //凯利指数
                            final Elements klktrs = pks.get(3).select("tbody>tr");
                            //初始凯利指数
                            final Elements ckl = klktrs.get(0).select("td");
                            oddInfo.setCwKlLb(ckl.get(0).text());
                            oddInfo.setCdKlLb(ckl.get(1).text());
                            oddInfo.setClKlLb(ckl.get(2).text());

                            //最终凯利指数
                            final Elements lkl = klktrs.get(1).select("td");
                            oddInfo.setLwKlLb(lkl.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdKlLb(lkl.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlKlLb(lkl.get(2).text().replace("↑", "").replace("↓", ""));
                        } else if (optr.attr("id").equals("293")) {
                            //威廉希尔赔率信息
                            final Elements pks = optr.select(".pl_table_data");
                            final Elements cpktrs = pks.get(0).select("tbody>tr");
                            //初始赔率
                            final Elements cop = cpktrs.get(0).select("td");
                            oddInfo.setCwOddWl(cop.get(0).text());
                            oddInfo.setCdOddWl(cop.get(1).text());
                            oddInfo.setClOddWl(cop.get(2).text());

                            //最终赔率
                            final Elements lop = cpktrs.get(1).select("td");
                            oddInfo.setLwOddWl(lop.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdOddWl(lop.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlOddWl(lop.get(2).text().replace("↑", "").replace("↓", ""));


                            //凯利指数
                            final Elements klktrs = pks.get(3).select("tbody>tr");
                            //初始凯利指数
                            final Elements ckl = klktrs.get(0).select("td");
                            oddInfo.setCwKlWl(ckl.get(0).text());
                            oddInfo.setCdKlWl(ckl.get(1).text());
                            oddInfo.setClKlWl(ckl.get(2).text());

                            //最终凯利指数
                            final Elements lkl = klktrs.get(1).select("td");
                            oddInfo.setLwKlWl(lkl.get(0).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLdKlWl(lkl.get(1).text().replace("↑", "").replace("↓", ""));
                            oddInfo.setLlKlWl(lkl.get(2).text().replace("↑", "").replace("↓", ""));
                        }
                    }
                    oddInfos.add(oddInfo);
                }

                if (count == 5) {
                    final int size = lotteryOddsDao.batchInsertOddInfo(oddInfos);
                    oddInfos.clear();
                    count = 0;
                    System.out.println("保存5天的数据,共" + size + "条");
                }

                //前一天数据
                beginDate = beginDate.minusDays(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (oddInfos.size() > 0) {
            final int size = lotteryOddsDao.batchInsertOddInfo(oddInfos);
            System.out.println("保存剩余数据,共" + size + "条");
        }

    }

}
