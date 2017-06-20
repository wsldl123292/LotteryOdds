package com.ldl.lotteryodds.collection;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 作者: LDL
 * 说明: 数据采集 博彩通
 * 时间: 2015/7/23 21:09
 */
public class BCTOddsCrawling {

    public static void main(String[] args) throws IOException {

        final String baseurl = "https://www.3163.com/list_1_";
        int count = 27;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        String url;
        int total = 0;
        int error = 0;
        int right = 0;
        int basketBall = 0;
        int wait = 0;
        int n = 0;
        while (count > 0) {
            url = baseurl + count + ".html";
            System.out.println("开始采集 " + url + " 的数据");
            count--;
            response = client.execute(new HttpGet(url));
            final String bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            final Document document = Jsoup.parse(bodyAsString);
            //获取比赛表格
            final Elements tableMatch = document.select("ul.t_linea");

            for (Element match : tableMatch) {
                final Element score = match.select(".Sevena").first();
                final Element result = match.select(".Eighta").first();
                total++;
                //排除等待的
                if (!score.text().trim().contains(":") || score.text().contains("?") || result.html().contains("0.gif")) {
                    wait++;
                    continue;
                }
                //排除篮球
                if (Integer.parseInt(score.text().trim().split(":")[0]) > 10) {
                    basketBall++;
                    continue;
                }
                if (result.html().contains("1.gif")) {
                    right++;
                }
                if (result.html().contains("2.gif")) {
                    System.out.println(match.select(".Threea").first().text() + "   " +
                            match.select(".Foura").first().text() + "   " + match.select(".Fivea").first().text()
                            +"   "+match.select(".Sixa").first().text()
                            + "      " + score.text());
                    error++;
                }
                if (result.html().contains("3.gif")) {
                    n++;
                }

            }

        }

        System.out.println("一共 :" + total);
        System.out.println("等待的 :" + wait);
        System.out.println("篮球 :" + basketBall);
        System.out.println("正确 :" + right);
        System.out.println("错误 :" + error);
        System.out.println("和局 :" + n);

        System.out.println("有效的 : " + (right + error));

        System.out.println("正确率 ： " + ((double) right / (right + error)));
        System.out.println("错误率 ： " + ((double) error / (right + error)));
    }

}
