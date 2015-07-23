package com.ldl.lotteryodds;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/7/23 21:09
 */
public class OddsCrawling {

    public static void main(String[] args) {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        String url = "http://live.aicai.com/jsbf/timelyscore!dynamicMatchDataForJczq.htm?dateTime=2015-06-02";
        CloseableHttpResponse response = null;
        try {
            response = client.execute(new HttpGet(url));
            String bodyAsString = EntityUtils.toString(response.getEntity());
            SinaResponse sinaResponse = JSON.parseObject(bodyAsString, SinaResponse.class);
            ResultEntity resultEntity = JSON.parseObject(sinaResponse.getResult(),ResultEntity.class);
            System.out.println(resultEntity.getJsbf_matchs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
