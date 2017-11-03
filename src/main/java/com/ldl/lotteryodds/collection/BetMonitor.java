package com.ldl.lotteryodds.collection;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/6/22 13:44
 */
public class BetMonitor {

    public static void main(String[] args) throws IOException {
        final String url = "http://d.dacai.com/huicha/peilvtong.html?matchId=2112083&corpid=251&corpname=%E7%AB%8B%E5%8D%9A&chuwin=1.80&chudarw=3.40&chulose=3.90&win=2.10&darw=3.25&lose=3.10";
        /*final CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        response = client.execute(new HttpGet(url));
        final String bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
        System.out.println(bodyAsString);*/

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(url);

        final String pageAsXml = page.asXml();
        System.out.println(pageAsXml);

        final String pageAsText = page.asText();
        System.out.println(pageAsText);
    }
}
