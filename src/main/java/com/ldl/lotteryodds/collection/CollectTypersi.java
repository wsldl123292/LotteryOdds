package com.ldl.lotteryodds.collection;

import com.ldl.lotteryodds.entity.Tips;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/9/8 17:30
 */
public class CollectTypersi {

    private static final String BASE_URL = "http://www.typersi.com/";

    private static final String MORE = "typerlist,dane,ranking.html?height=400&width=190";
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
        if (ranks == null || ranks.size() == 0) {
            ranks = right.select("div").get(5).select("li");
        }

        ArrayList<String> urls = new ArrayList<>();

        for (Element rank : ranks) {
            urls.add(rank.select("[href]").attr("href"));
        }
        List<Tips> tipsList = new ArrayList<>();
        for (String url : urls) {
            System.out.println("当前处理:" + url);
            get = new HttpGet(BASE_URL + url);
            response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            Document d = Jsoup.parse(result);
            Element tbody = d.getElementsByTag("tbody").get(2);
            Elements trs = tbody.getElementsByTag("tr");

            toList(tipsList, trs, url);
        }


        System.out.println("开始更多查询");
        get = new HttpGet(BASE_URL + MORE);
        response = client.execute(get);
        final Document moreDoucument = Jsoup.parse(EntityUtils.toString(response.getEntity()));
        //获取比赛表格
        Elements moreRanks = moreDoucument.getElementById("typerlistbox").select("ul").get(0).select("li");

        for (int i = 0; i < 15; i++) {
            String url = moreRanks.get(i).select("[href]").attr("href");
            System.out.println("更多排序,当前处理:" + url);
            get = new HttpGet(BASE_URL + url);
            response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            Document d = Jsoup.parse(result);
            Element tbody = d.getElementsByTag("tbody").get(2);
            Elements trs = tbody.getElementsByTag("tr");

            toList(tipsList, trs, url);
        }


        Map<Tips, Integer> map = new HashMap<>();

        for (Tips tips : tipsList) {
            map.merge(tips, 1, (a, b) -> a + b);
        }

        for (Tips tips : map.keySet()) {
            LocalDate localDate = LocalDate.now();
            if (tips.getDay() == localDate.getDayOfMonth()) {
                System.out.println(tips + ":" + map.get(tips));
            }
        }

    }

    private static void toList(List<Tips> tipsList, Elements trs, String url) {
        for (Element tr : trs) {
            Tips tips = new Tips();
            tips.setDay(Integer.valueOf(tr.getElementsByTag("td").first().text()));
            tips.setMatch(tr.getElementsByTag("td").get(2).text());
            tips.setResult(tr.getElementsByTag("td").get(3).getElementsByTag("a").first().text());
            tips.setScore(tr.getElementsByTag("td").get(7).getElementsByTag("a").first().text());
            tips.setAuthor(url.split(",")[1]);
            tipsList.add(tips);
        }
    }


}
