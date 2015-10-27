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
public class TestTextBinaryPython {
    public static void main(String[] args) throws IOException {
//采集开始时间2011-07-21
        LocalDate beginDate = LocalDate.of(2015, 10, 25);
        int size = 78;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        HttpGet get;

        /** 列表地址 */
        final String baseurl = "http://live.500.com/?e=";
        /** 亚盘地址 */
        final String yapanUrl = "http://odds.500.com/fenxi/yazhi-";
        /** 欧赔地址 */
        final String oupeiUrl = "http://odds.500.com/fenxi/ouzhi-";
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


                /** 采集数据地址 http://odds.500.com/fenxi/shuju-331954.shtml */
                url = shujuUrl + fid + ".shtml";
                get = new HttpGet(url);
                get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                response = client.execute(get);
                final String shujunBody = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                final Document shuju = Jsoup.parse(shujunBody);
                //获取双方交战记录
                final Elements hits = shuju.select(".his_info");
                Element hit = hits.first();
                Elements f16 = hit.select(".f16");
                if (f16 != null && f16.size() > 0) {
                    oddInfo.setWin(f16.select(".red").get(0).text().replace("胜", ""));
                    oddInfo.setDown(f16.select(".green").get(0).text().replace("平", ""));
                    oddInfo.setLose(f16.select(".blue").get(0).text().replace("负", ""));
                }
                //双方战绩
                final Elements zhanjis = shuju.select(".bottom_info");
                /** 主队近10场 */
                Elements z = zhanjis.get(0).select(".mar_left20");
                if (z != null && z.size() > 0) {
                    oddInfo.setZwin(z.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setZdown(z.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setZlose(z.get(0).select(".shu").get(0).text().replace("负", ""));

                    oddInfo.setZjscore(Integer.parseInt(z.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZlscore(Integer.parseInt(z.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 客队近10场 */
                Elements k = zhanjis.get(1).select(".mar_left20");
                if (k != null && k.size() > 0) {
                    oddInfo.setKwin(k.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setKdown(k.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setKlose(k.get(0).select(".shu").get(0).text().replace("负", ""));

                    oddInfo.setKjscore(Integer.parseInt(k.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKlscore(Integer.parseInt(k.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                /** 主队近10主场 */
                Elements zz = zhanjis.get(2).select(".mar_left20");
                if (zz != null && zz.size() > 0) {
                    oddInfo.setZzwin(zz.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setZzdown(zz.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setZzlose(zz.get(0).select(".shu").get(0).text().replace("负", ""));

                    oddInfo.setZzjscore(Integer.parseInt(zz.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setZzlscore(Integer.parseInt(zz.get(1).select(".shu").get(0).text().replace("球", "")));
                }

                /** 客队近10客场 */
                Elements kk = zhanjis.get(3).select(".mar_left20");
                if (kk != null && kk.size() > 0) {
                    oddInfo.setKkwin(kk.get(0).select(".ying").get(0).text().replace("胜", ""));
                    oddInfo.setKkdown(kk.get(0).select(".ping").get(0).text().replace("平", ""));
                    oddInfo.setKklose(kk.get(0).select(".shu").get(0).text().replace("负", ""));

                    oddInfo.setKkjscore(Integer.parseInt(kk.get(1).select(".ying").get(0).text().replace("球", "")));
                    oddInfo.setKklsocre(Integer.parseInt(kk.get(1).select(".shu").get(0).text().replace("球", "")));
                }
                oddInfos.add(oddInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (oddInfos.size() > 0) {//getCpAm()  getLpAm()
            final StringBuilder stringBuffer = new StringBuilder();
            for (OddInfo oddInfo : oddInfos) {
                stringBuffer
                        /*.append(oddInfo.getCwOddAm()).append("\t")
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
                        .append(oddInfo.getCpLb()).append("\t")
                        .append(oddInfo.getCkWaterLb()).append("\t")
                        .append(oddInfo.getLzWaterLb()).append("\t")
                        .append(oddInfo.getLpLb()).append("\t")
                        .append(oddInfo.getLkWaterLb()).append("\t")
                        .append(oddInfo.getCwOddWl()).append("\t")
                        .append(oddInfo.getCdOddWl()).append("\t")
                        .append(oddInfo.getClOddWl()).append("\t")
                        .append(oddInfo.getLwOddWl()).append("\t")
                        .append(oddInfo.getLdOddWl()).append("\t")*/
                        .append(oddInfo.getCpAm().replace("-", "")).append("\t")
                        .append(oddInfo.getLpAm().replace("-", "")).append("\t")
                        //.append(oddInfo.getLlOddWl()).append("\t")
                        /*.append(oddInfo.getCwKlWl()).append("\t")
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







                        .append(new BigDecimal(oddInfo.getLwOddAm()).subtract(new BigDecimal(oddInfo.getCwOddAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdOddAm()).subtract(new BigDecimal(oddInfo.getCdOddAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlOddAm()).subtract(new BigDecimal(oddInfo.getClOddAm()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLwKlAm()).subtract(new BigDecimal(oddInfo.getCwKlAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdKlAm()).subtract(new BigDecimal(oddInfo.getCdKlAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlKlAm()).subtract(new BigDecimal(oddInfo.getClKlAm()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLzWaterAm()).subtract(new BigDecimal(oddInfo.getCzWaterAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLpAm()).subtract(new BigDecimal(oddInfo.getCpAm()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLkWaterAm()).subtract(new BigDecimal(oddInfo.getCkWaterAm()))).append("\t")



                        .append(new BigDecimal(oddInfo.getLwOddLb()).subtract(new BigDecimal(oddInfo.getCwOddLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdOddLb()).subtract(new BigDecimal(oddInfo.getCdOddLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlOddLb()).subtract(new BigDecimal(oddInfo.getClOddLb()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLwKlLb()).subtract(new BigDecimal(oddInfo.getCwKlLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdKlLb()).subtract(new BigDecimal(oddInfo.getCdKlLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlKlLb()).subtract(new BigDecimal(oddInfo.getClKlLb()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLzWaterLb()).subtract(new BigDecimal(oddInfo.getCzWaterLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLpLb()).subtract(new BigDecimal(oddInfo.getCpLb()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLkWaterLb()).subtract(new BigDecimal(oddInfo.getCkWaterLb()))).append("\t")




                        .append(new BigDecimal(oddInfo.getLwOddWl()).subtract(new BigDecimal(oddInfo.getCwOddWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdOddWl()).subtract(new BigDecimal(oddInfo.getCdOddWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlOddWl()).subtract(new BigDecimal(oddInfo.getClOddWl()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLwKlWl()).subtract(new BigDecimal(oddInfo.getCwKlWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLdKlWl()).subtract(new BigDecimal(oddInfo.getCdKlWl()))).append("\t")
                        .append(new BigDecimal(oddInfo.getLlKlWl()).subtract(new BigDecimal(oddInfo.getClKlWl()))).append("\t")

                        .append(new BigDecimal(oddInfo.getLose()).add(new BigDecimal(oddInfo.getDown()))).append("\t")

                        .append(new BigDecimal(oddInfo.getZlose()).add(new BigDecimal(oddInfo.getZdown()))).append("\t")

                        .append(new BigDecimal(oddInfo.getKlose()).add(new BigDecimal(oddInfo.getKdown()))).append("\t")

                        .append(new BigDecimal(oddInfo.getZzdown()).add(new BigDecimal(oddInfo.getZzlose()))).append("\t")

                        .append(new BigDecimal(oddInfo.getKkdown()).add(new BigDecimal(oddInfo.getKklose()))).append("\t")*/
                        .append(oddInfo.getType()).append("\n");
            }
            Files.write(stringBuffer.toString(), new File("F:\\data\\lotteryodds\\test_all_binary.txt"), Charsets.UTF_8);

        }
    }
}
