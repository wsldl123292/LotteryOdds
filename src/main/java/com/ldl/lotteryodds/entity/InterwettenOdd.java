package com.ldl.lotteryodds.entity;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/1/12 16:55
 */
public class InterwettenOdd {

    public InterwettenOdd() {
    }

    public InterwettenOdd(String matchId) {
        this.matchId = matchId;
    }

    /**
     * 比赛结果 0--负 1--平 3--胜 2--未知
     */
    private int result = 2;

    /**
     * 主队进球
     */
    private int zscore;
    /**
     * 客队进球
     */
    private int kscore;

    /**
     * 比赛id
     */
    private String matchId;

    /**
     * 比赛时间
     */
    private String date;

    private String zName;

    private String kName;

    private String sid;


    private String cp = "";
    private String ww;
    private String wd;
    private String wl;
    private String dw;
    private String dd;
    private String dl;
    private String lw;
    private String ld;
    private String ll;

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl = wl;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getLw() {
        return lw;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public String getLl() {
        return ll;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int zscore, int kscore) {
        if (zscore > kscore) {
            this.result = 3;
        } else if (zscore == kscore) {
            this.result = 1;
        } else if (zscore < kscore) {
            this.result = 0;
        }
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    public int getKscore() {
        return kscore;
    }

    public void setKscore(int kscore) {
        this.kscore = kscore;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
