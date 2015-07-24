package com.ldl.lotteryodds;

/**
 * 作者: LDL
 * 功能说明: 500彩票网
 * 创建日期: 2015/7/24 15:22
 */
public class Entity500 {

    private String id;
    private String content;
    /**
     * 不让球主胜赔率
     */
    private double hwOdd;
    /**
     * 不让球主平赔率
     */
    private double hdOdd;
    /**
     * 不让球主负赔率
     */
    private double hlOdd;
    /**
     * 让球主胜赔率
     */
    private double rhwOdd;
    /**
     * 让球主平赔率
     */
    private double rhdOdd;
    /**
     * 让球主负赔率
     */
    private double rhlOdd;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getHwOdd() {
        return hwOdd;
    }

    public void setHwOdd(double hwOdd) {
        this.hwOdd = hwOdd;
    }

    public double getHdOdd() {
        return hdOdd;
    }

    public void setHdOdd(double hdOdd) {
        this.hdOdd = hdOdd;
    }

    public double getHlOdd() {
        return hlOdd;
    }

    public void setHlOdd(double hlOdd) {
        this.hlOdd = hlOdd;
    }

    public double getRhwOdd() {
        return rhwOdd;
    }

    public void setRhwOdd(double rhwOdd) {
        this.rhwOdd = rhwOdd;
    }

    public double getRhdOdd() {
        return rhdOdd;
    }

    public double getRhlOdd() {
        return rhlOdd;
    }

    public void setRhlOdd(double rhlOdd) {
        this.rhlOdd = rhlOdd;
    }

    public void setRhdOdd(double rhdOdd) {
        this.rhdOdd = rhdOdd;
    }
}
