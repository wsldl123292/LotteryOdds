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
import java.util.ArrayList;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/9/8 17:30
 */
public class CollectTypersi {

    private static final String BASE_URL = "http://www.typersi.com/";

    private static final CloseableHttpClient client = HttpClientBuilder.create().build();


    public static void main(String[] args) throws IOException {
        CloseableHttpResponse response;
        HttpGet get;
        get = new HttpGet(BASE_URL + "index.php");
        response = client.execute(get);
        final String bodyAsString = EntityUtils.toString(response.getEntity());
        final Document document = Jsoup.parse(bodyAsString);
        //获取比赛表格
        final Element right = document.getElementById("right");

        Elements ranks = right.select("div").get(4).select("li");


        ArrayList<String> urls = new ArrayList<>();

        for (Element rank : ranks) {
            urls.add(rank.select("[href]").attr("href"));
        }


        /*for (String url : urls) {
            get = new HttpGet(BASE_URL + url);
            response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            Document d = Jsoup.parse(result);
            System.out.println(d.html());
        }*/
        get = new HttpGet(BASE_URL + urls.get(0));
        response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        Document d = Jsoup.parse(result);
        Element tbody = d.getElementsByTag("tbody").get(2);

    }


    class Tips {
        private Integer day;

        private String home;

        private String result;

        private String away;


        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getAway() {
            return away;
        }

        public void setAway(String away) {
            this.away = away;
        }

        @Override
        public String toString() {
            return "Tips{" +
                    "day=" + day +
                    ", home='" + home + '\'' +
                    ", result='" + result + '\'' +
                    ", away='" + away + '\'' +
                    '}';
        }
    }


}
