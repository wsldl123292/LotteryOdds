package com.ldl.lotteryodds;

/**
 * 作者: LDL
 * 说明: 新浪彩票请求返回值中result实体
 * 时间: 2015/7/23 22:45
 */
public class ResultEntity {

    private String jsbf_matchs;
    private String league_select;

    public String getLeague_select() {
        return league_select;
    }

    public void setLeague_select(String league_select) {
        this.league_select = league_select;
    }

    public String getJsbf_matchs() {
        return jsbf_matchs;
    }

    public void setJsbf_matchs(String jsbf_matchs) {
        this.jsbf_matchs = jsbf_matchs;
    }
}
