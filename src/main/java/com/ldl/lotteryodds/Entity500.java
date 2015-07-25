package com.ldl.lotteryodds;

/**
 * 作者: LDL
 * 功能说明: 500彩票网
 * 创建日期: 2015/7/24 15:22
 */
public class Entity500 {

    private int id;
    /**
     * 比赛id
     */
    private String matchid;
    private String content;
    /**
     * 不让球主胜赔率
     */
    private String hwOdd;
    /**
     * 不让球主平赔率
     */
    private String hdOdd;
    /**
     * 不让球主负赔率
     */
    private String hlOdd;
    /**
     * 让球主胜赔率
     */
    private String rhwOdd;
    /**
     * 让球主平赔率
     */
    private String rhdOdd;
    /**
     * 让球主负赔率
     */
    private String rhlOdd;

    /**
     * 比赛日期
     */
    private String date;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHwOdd() {
        return hwOdd;
    }

    public void setHwOdd(String hwOdd) {
        this.hwOdd = hwOdd;
    }

    public String getHdOdd() {
        return hdOdd;
    }

    public void setHdOdd(String hdOdd) {
        this.hdOdd = hdOdd;
    }

    public String getHlOdd() {
        return hlOdd;
    }

    public void setHlOdd(String hlOdd) {
        this.hlOdd = hlOdd;
    }

    public String getRhwOdd() {
        return rhwOdd;
    }

    public void setRhwOdd(String rhwOdd) {
        this.rhwOdd = rhwOdd;
    }

    public String getRhdOdd() {
        return rhdOdd;
    }

    public void setRhdOdd(String rhdOdd) {
        this.rhdOdd = rhdOdd;
    }

    public String getRhlOdd() {
        return rhlOdd;
    }

    public void setRhlOdd(String rhlOdd) {
        this.rhlOdd = rhlOdd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchid() {
        return matchid;
    }

    public void setMatchid(String matchid) {
        this.matchid = matchid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
