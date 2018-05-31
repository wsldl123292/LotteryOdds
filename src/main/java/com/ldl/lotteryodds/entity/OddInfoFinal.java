package com.ldl.lotteryodds.entity;

/**
 * 作者: LDL
 * 功能说明: 采集实体
 * 创建日期: 2015/9/28 17:31
 */
public class OddInfoFinal {

    public OddInfoFinal() {
    }

    public OddInfoFinal(String matchId) {
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
     * 初始主胜赔率
     */
    private String cwOdd;
    /**
     * 初始主平赔率
     */
    private String cdOdd;
    /**
     * 初始主负赔率
     */
    private String clOdd;


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

    public String getCwOdd() {
        return cwOdd;
    }

    public void setCwOdd(String cwOdd) {
        this.cwOdd = cwOdd;
    }

    public String getCdOdd() {
        return cdOdd;
    }

    public void setCdOdd(String cdOdd) {
        this.cdOdd = cdOdd;
    }

    public String getClOdd() {
        return clOdd;
    }

    public void setClOdd(String clOdd) {
        this.clOdd = clOdd;
    }

    @Override
    public String toString() {
        return "OddInfoFinal{" +
                "result=" + result +
                ", zscore=" + zscore +
                ", kscore=" + kscore +
                ", matchId='" + matchId + '\'' +
                ", cwOdd='" + cwOdd + '\'' +
                ", cdOdd='" + cdOdd + '\'' +
                ", clOdd='" + clOdd + '\'' +
                '}';
    }
}
