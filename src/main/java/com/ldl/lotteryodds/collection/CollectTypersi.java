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

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/9/8 17:30
 */
public class CollectTypersi {

    private static final String BASE_URL = "http://www.typersi.com/index.php";

    private static final CloseableHttpClient client = HttpClientBuilder.create().build();


    public static void main(String[] args) throws IOException {
        CloseableHttpResponse response;
        HttpGet get;
        get = new HttpGet(BASE_URL);
        response = client.execute(get);
        final String bodyAsString = EntityUtils.toString(response.getEntity());
        final Document document = Jsoup.parse(bodyAsString);
        //获取比赛表格
        final Element right = document.getElementById("right");

        Elements ranks = right.select("div").get(4).select("li");


        for (Element rank : ranks) {
            System.out.println(rank.select("[href]").attr("href"));
        }

    }


}
