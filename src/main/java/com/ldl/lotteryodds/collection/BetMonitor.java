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
import java.text.SimpleDateFormat;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/6/22 13:44
 */
public class BetMonitor {

    public static void main(String[] args) throws IOException {
        final String url = "https://www.3163.com/list_1_";
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        long time = 60 * 20 * 1000 + System.currentTimeMillis();
        do {
            System.out.println("开始监控，时间 : " + new SimpleDateFormat("yyyyMMdd HH:mm:ss"));
            response = client.execute(new HttpGet(url));
            final String bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            final Document document = Jsoup.parse(bodyAsString);
            //获取比赛表格
            final Elements tableMatch = document.select("ul.t_linea");

            /*for (Element match : tableMatch) {
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
                            + "   " + match.select(".Sixa").first().text()
                            + "      " + score.text());
                    error++;
                }
                if (result.html().contains("3.gif")) {
                    n++;
                }

            }*/
        } while (time == System.currentTimeMillis());


    }
}
