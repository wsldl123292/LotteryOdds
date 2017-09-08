package com.ldl.lotteryodds.entity;

import java.util.Date;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/6/22 13:45
 */
public class Bet {

    private Long id;

    private String home;
    private String visit;
    private String asianPlate;

    private String odds;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getAsianPlate() {
        return asianPlate;
    }

    public void setAsianPlate(String asianPlate) {
        this.asianPlate = asianPlate;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
