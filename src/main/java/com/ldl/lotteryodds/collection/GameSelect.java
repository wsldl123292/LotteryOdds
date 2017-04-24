package com.ldl.lotteryodds.collection;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;
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
import java.util.List;

/**
 * @描述 大彩网或澳客 选择大必发赔率  澳客暂不可用
 * @作者 liudelin
 * @日期 2017/4/24 14:32
 */
public class GameSelect {

    private static final String OKOOO_BASE_URL = "http://www.okooo.com/";
    private static final String OKOOO_GAME_LIST_URL = "livecenter/danchang/?date=170404";
    private static final String DACAI_BASE_URL = "http://www.dacai.com/live/";
    private static final String DACAI_GAME_LIST_URL = "?gameid=638740";
    private static HttpGet get;
    private static final CloseableHttpClient client = HttpClientBuilder.create().build();
    private static CloseableHttpResponse response;

    public static void main(String[] args) throws IOException {
        //CheckBackDaCai(8800f, 8800f, 100, 100);
        CheckBackOkooo(8800f, 8800f, 10);
    }


    public static void selectOkoooGameByRatio(float winningRatio, float overUnderRatio, List<Game> games) {

        List<Game> winLoseGameList = Lists.newArrayList();
        List<Game> overUnderGameList = Lists.newArrayList();
        String bodyAsString;
        final WebClient webClient=new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        try {
            for (Game game : games) {
                //get = new HttpGet(OKOOO_BASE_URL + game.getUrl());
                //get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.69 Safari/537.36 QQBrowser/9.1.4060.400");
                //response = client.execute(get);
                //bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));

                final HtmlPage page=webClient.getPage(OKOOO_BASE_URL + game.getUrl());
                //System.out.println(page.asText());

                final Document document = Jsoup.parse(page.asXml());
                //获取数据表格
                final Elements tables = document.select(".noBberBottom");
                if (tables.size() < 1) {
                    System.out.println(game.getNumber() + ":" + OKOOO_BASE_URL + game.getUrl());
                    continue;
                }
                //胜负
                Element table = tables.get(1);
                Elements trs = table.select("tbody>tr");
                int count = 2;
                while (count < 5) {
                    if (compare(trs.get(count).select(".borderLeft").get(1).text(), winningRatio)) {
                        winLoseGameList.add(game);
                        break;
                    }
                    count++;
                }

                //大小
                table = tables.last();
                trs = table.select("tbody>tr");
                count = 2;
                while (count < 4) {
                    if (compare(trs.get(count).select(".borderLeft").last().text(), overUnderRatio)) {
                        overUnderGameList.add(game);
                        break;
                    }
                    count++;
                }
            }
            webClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        print(winLoseGameList, overUnderGameList);

    }

    public static void print(List<Game> winLoseGameList, List<Game> overUnderGameList) {
        System.out.println("胜负符合条件比赛");
        System.out.println(winLoseGameList);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("大小符合条件比赛");
        System.out.println(overUnderGameList);
    }

    /**
     * 获取澳客比赛列表
     *
     * @return
     */
    public static List<Game> getOkoooGameList(int size, String type) {
        String bodyAsString;
        List<Game> gameList = Lists.newArrayList();
        try {
            get = new HttpGet(OKOOO_BASE_URL + OKOOO_GAME_LIST_URL);
            response = client.execute(get);
            bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
            final Document document = Jsoup.parse(bodyAsString);
            //获取比赛表格
            final Element mathTable = document.getElementById("livescore_table");
            //未开始的比赛
            final Elements games = mathTable.select("tbody>tr").select("[state=" + type + "]");
            for (int i = 0; i < size; i++) {
                Game game = new Game();
                Elements tds = games.get(i).getElementsByTag("td");
                game.setNumber(Integer.parseInt(tds.first().child(1).tagName("span").text()));
                game.setUrl("soccer/match/" + games.get(i).attr("matchid") + "/exchanges/");
                gameList.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameList;
    }


    /**
     * 获取大彩网比赛列表
     *
     * @return
     */
    public static List<Game> getDaCaiGameList(int begin, int size, String type) {
        String bodyAsString;
        List<Game> gameList = Lists.newArrayList();
        try {
            get = new HttpGet(DACAI_BASE_URL + DACAI_GAME_LIST_URL);
            response = client.execute(get);
            bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
            final Document document = Jsoup.parse(bodyAsString);
            //获取比赛表格
            final Element mathTable = document.getElementById("matchTable");
            //未开始的比赛
            final Elements games = mathTable.select("tbody>tr").select("." + type);
            for (int i = begin; i < size + begin; i++) {
                Game game = new Game();
                Elements tds = games.get(i).getElementsByTag("td");
                game.setNumber(Integer.parseInt(tds.first().getElementsByTag("span").first().text()));
                game.setUrl("http://d.dacai.com/zhishu/jyyk.html?matchid=" + games.get(i).attr("matchid"));
                gameList.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("数据获取完成");
        return gameList;
    }

    public static void selectDaCaiGameByRatio(float winningRatio, float overUnderRatio, List<Game> games) {

        List<Game> winLoseGameList = Lists.newArrayList();
        List<Game> overUnderGameList = Lists.newArrayList();
        String bodyAsString;
        try {
            for (Game game : games) {
                System.out.println("开始处理数据:" + game.getNumber());
                get = new HttpGet(game.getUrl());
                response = client.execute(get);
                bodyAsString = EntityUtils.toString(response.getEntity(), Charset.forName("gb2312"));
                final Document document = Jsoup.parse(bodyAsString);
                //获取数据表格
                final Element table = document.getElementById("_0_by");
                //胜负
                Elements trs = table.select("tr");
                int count = 0;
                while (count < 3) {
                    if (compare(trs.get(count).getElementsByTag("td").get(7).text(), winningRatio)) {
                        winLoseGameList.add(game);
                        break;
                    }
                    count++;
                }

                count = 3;
                //大小
                while (count < 5) {
                    if (compare(trs.get(count).getElementsByTag("td").get(7).text(), overUnderRatio)) {
                        overUnderGameList.add(game);
                        break;
                    }
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        print(winLoseGameList, overUnderGameList);

    }

    private static boolean compare(String a, float b) {
        float s = Float.parseFloat(a.replace("%", "")) * 100;
        return s > b;
    }


    /**
     * 大彩网回查
     *
     * @param winningRatio
     * @param overUnderRatio
     * @param size
     */
    public static void CheckBackDaCai(float winningRatio, float overUnderRatio, int begin, int size) {
        List<Game> games = getDaCaiGameList(begin, size, "endgame");
        selectDaCaiGameByRatio(winningRatio, overUnderRatio, games);
    }

    /**
     * 大彩网
     *
     * @param winningRatio
     * @param overUnderRatio
     * @param size
     */
    public static void CheckDaCai(float winningRatio, float overUnderRatio, int begin, int size) {
        List<Game> games = getDaCaiGameList(begin, size, "back");
        selectDaCaiGameByRatio(winningRatio, overUnderRatio, games);
    }

    /**
     * 澳客网回查
     *
     * @param winningRatio
     * @param overUnderRatio
     * @param size
     */
    public static void CheckBackOkooo(float winningRatio, float overUnderRatio, int size) {
        List<Game> games = getOkoooGameList(size, "End");
        selectOkoooGameByRatio(winningRatio, overUnderRatio, games);
    }

    /**
     * 澳客网
     *
     * @param winningRatio
     * @param overUnderRatio
     * @param size
     */
    public static void CheckOkooo(float winningRatio, float overUnderRatio, int size) {
        List<Game> games = getOkoooGameList(size, "Not");
        selectOkoooGameByRatio(winningRatio, overUnderRatio, games);
    }

    private static class Game {
        private int number;
        private String url;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "number=" + number +
                    '}';
        }
    }
}
