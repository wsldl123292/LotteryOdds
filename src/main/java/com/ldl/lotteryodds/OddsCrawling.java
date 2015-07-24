package com.ldl.lotteryodds;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/7/23 21:09
 */
public class OddsCrawling {

    public static void main(String[] args) {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        String url = "http://live.500.com/?e=2014-06-02";
        CloseableHttpResponse response;
        try {
            response = client.execute(new HttpGet(url));
            String bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
            Document document = Jsoup.parse(bodyAsString);
            //获取比赛表格
            Element tableMatch = document.getElementById("table_match");
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
            Elements scriptElements = document.getElementsByTag("script");
            for (int i = 0; i < scriptElements.size(); i++) {
                Element element = scriptElements.get(i);
                if(element.html().contains("liveOddsList")){
                    odds = element.html();
                    break;
                }
            }
            Map<String,Object> liveOddsMap = (Map) JSON.parse(odds.substring("var liveOddsList=".length(), odds.length() - 1));
            Map<String,Map<String,String>> oddMaps = new HashMap<>();
            for(String key:liveOddsMap.keySet()){
                String value = liveOddsMap.get(key).toString().replace("\"", "").replace("[","").replace("],","-").replace("{","").replace("}","").replace("]","");
                Map<String, String> join = Splitter.on("-").withKeyValueSeparator(":").split(value);
                oddMaps.put(key,join);
            }

            List<Entity500> entity500s = new ArrayList<>();
            //解析每个tr转换为实体
            Elements trs = tableMatch.select("tbody>tr");
            for (int i = 0; i < trs.size(); i++) {
                Element element = trs.get(i);
                String fid = element.attr("fid");
                Entity500 entity500 = new Entity500();
                entity500.setId(fid);
                Map<String,String> map = oddMaps.get(fid);
                //拼接赔率
                String rqhtml = "";
                if(map.get("sp")!=null){
                    String[] arr = map.get("sp").split(",");
                    entity500.setHwOdd(Double.parseDouble(arr[0]));
                    entity500.setHdOdd(Double.parseDouble(arr[1]));
                    entity500.setHlOdd(Double.parseDouble(arr[2]));
                    rqhtml += "<td align=\"center\" class=\"bf_op\"><span>"+arr[0]+"</span><span>"+arr[1]+"</span><span>"+arr[2]+"</span></td>";
                }
                if(map.get("rqsp")!=null){
                    String[] arr = map.get("rqsp").split(",");
                    entity500.setRhwOdd(Double.parseDouble(arr[0]));
                    entity500.setRhdOdd(Double.parseDouble(arr[1]));
                    entity500.setRhlOdd(Double.parseDouble(arr[2]));
                    rqhtml += "<td align=\"center\" class=\"bf_op\"><span>"+arr[0]+"</span><span>"+arr[1]+"</span><span>"+arr[2]+"</span></td>";
                }
                element.select(".bf_op").first().after(rqhtml);
                element.select(".bf_op").first().remove();
                entity500.setContent(element.outerHtml());
                entity500s.add(entity500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
