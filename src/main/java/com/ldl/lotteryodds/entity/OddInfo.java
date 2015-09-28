package com.ldl.lotteryodds.entity;

/**
 * 作者: LDL
 * 功能说明: 采集实体
 * 创建日期: 2015/9/28 17:31
 */
public class OddInfo {

    public OddInfo() {
    }

    public OddInfo(String matchId) {
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


    /** 澳门 */

    /**
     * 初始主胜赔率澳门
     */
    private double cwOddAm;
    /**
     * 初始主平赔率澳门
     */
    private double cdOddAm;
    /**
     * 初始主负赔率澳门
     */
    private double clOddAm;

    /**
     * 最终主胜赔率澳门
     */
    private double lwOddAm;
    /**
     * 最终主平赔率澳门
     */
    private double ldOddAm;
    /**
     * 最终主负赔率澳门
     */
    private double llOddAm;

    /**
     * 初始主胜凯利指数澳门
     */
    private double cwKlAm;
    /**
     * 初始主平凯利指数澳门
     */
    private double cdKlAm;
    /**
     * 初始主负凯利指数澳门
     */
    private double clKlAm;
    /**
     * 最终主胜凯利指数澳门
     */
    private double lwClAm;
    /**
     * 最终主平凯利指数澳门
     */
    private double ldKlAm;
    /**
     * 最终主负凯利指数澳门
     */
    private double llKlAm;




    /** 威廉希尔 */

    /**
     * 初始主胜赔率威廉希尔
     */
    private double cwOddWl;
    /**
     * 初始主平赔率威廉希尔
     */
    private double cdOddWl;
    /**
     * 初始主负赔率威廉希尔
     */
    private double clOddWl;

    /**
     * 最终主胜赔率威廉希尔
     */
    private double lwOddWl;
    /**
     * 最终主平赔率威廉希尔
     */
    private double ldOddWl;
    /**
     * 最终主负赔率威廉希尔
     */
    private double llOddWl;

    /**
     * 初始主胜凯利指数威廉希尔
     */
    private double cwKlWl;
    /**
     * 初始主平凯利指数威廉希尔
     */
    private double cdKlWl;
    /**
     * 初始主负凯利指数威廉希尔
     */
    private double clKlWl;
    /**
     * 最终主胜凯利指数威廉希尔
     */
    private double lwClWl;
    /**
     * 最终主平凯利指数威廉希尔
     */
    private double ldKlWl;
    /**
     * 最终主负凯利指数威廉希尔
     */
    private double llKlWl;




    /** 立博 */

    /**
     * 初始主胜赔率立博
     */
    private double cwOddLb;
    /**
     * 初始主平赔率立博
     */
    private double cdOddLb;
    /**
     * 初始主负赔率立博
     */
    private double clOddLb;

    /**
     * 最终主胜赔率立博
     */
    private double lwOddLb;
    /**
     * 最终主平赔率立博
     */
    private double ldOddLb;
    /**
     * 最终主负赔率立博
     */
    private double llOddLb;

    /**
     * 初始主胜凯利指数立博
     */
    private double cwKlLb;
    /**
     * 初始主平凯利指数立博
     */
    private double cdKlLb;
    /**
     * 初始主负凯利指数立博
     */
    private double clKlLb;
    /**
     * 最终主胜凯利指数立博
     */
    private double lwClLb;
    /**
     * 最终主平凯利指数立博
     */
    private double ldKlLb;
    /**
     * 最终主负凯利指数立博
     */
    private double llKlLb;



    /**
     * 初始平均主胜赔率
     */
    private double scwOdd;
    /**
     * 初始平均主平赔率
     */
    private double scdOdd;
    /**
     * 初始平均主负赔率
     */
    private double sclOdd;

    /**
     * 最终平均主胜赔率
     */
    private double slwOdd;
    /**
     * 最终平均主平赔率
     */
    private double sldOdd;
    /**
     * 最终平均主负赔率
     */
    private double sllOdd;


    /**
     * 初始平均主胜凯利指数
     */
    private double scwKl;
    /**
     * 初始平均主平凯利指数
     */
    private double scdKl;
    /**
     * 初始平均主负凯利指数
     */
    private double sclKl;
    /**
     * 最终平均主胜凯利指数
     */
    private double slwCl;
    /**
     * 最终平均主平凯利指数
     */
    private double sldKl;
    /**
     * 最终平均主负凯利指数
     */
    private double sllKl;



    /** 澳门 */

    /**
     * 初始主水位澳门
     */
    private double czWaterAm;
    /**
     * 初始盘口澳门
     */
    private double cpAm;
    /**
     * 初始客水位澳门
     */
    private double ckWaterAm;

    /**
     * 最终主水位澳门
     */
    private double lzWaterAm;
    /**
     * 最终盘口澳门
     */
    private double lpAm;
    /**
     * 最终客水位澳门
     */
    private double lkWaterAm;


    /** 立博 */

    /**
     * 初始主水位立博
     */
    private double czWaterLb;
    /**
     * 初始盘口立博
     */
    private double cpLb;
    /**
     * 初始客水位立博
     */
    private double ckWaterLb;

    /**
     * 最终主水位立博
     */
    private double lzWaterLb;
    /**
     * 最终盘口立博
     */
    private double lpLb;
    /**
     * 最终客水位立博
     */
    private double lkWaterLb;


    /**
     * 平均初始主水位
     */
    private double sczWater;
    /**
     * 平均初始盘口
     */
    private double scp;
    /**
     * 平均初始客水位
     */
    private double sckWater;

    /**
     * 平均最终主水位
     */
    private double slzWater;
    /**
     * 平均最终盘口
     */
    private double slp;
    /**
     * 平均最终客水位
     */
    private double slkWater;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public double getCwOddAm() {
        return cwOddAm;
    }

    public void setCwOddAm(double cwOddAm) {
        this.cwOddAm = cwOddAm;
    }

    public double getCdOddAm() {
        return cdOddAm;
    }

    public void setCdOddAm(double cdOddAm) {
        this.cdOddAm = cdOddAm;
    }

    public double getClOddAm() {
        return clOddAm;
    }

    public void setClOddAm(double clOddAm) {
        this.clOddAm = clOddAm;
    }

    public double getLwOddAm() {
        return lwOddAm;
    }

    public void setLwOddAm(double lwOddAm) {
        this.lwOddAm = lwOddAm;
    }

    public double getLdOddAm() {
        return ldOddAm;
    }

    public void setLdOddAm(double ldOddAm) {
        this.ldOddAm = ldOddAm;
    }

    public double getLlOddAm() {
        return llOddAm;
    }

    public void setLlOddAm(double llOddAm) {
        this.llOddAm = llOddAm;
    }

    public double getCwKlAm() {
        return cwKlAm;
    }

    public void setCwKlAm(double cwKlAm) {
        this.cwKlAm = cwKlAm;
    }

    public double getCdKlAm() {
        return cdKlAm;
    }

    public void setCdKlAm(double cdKlAm) {
        this.cdKlAm = cdKlAm;
    }

    public double getClKlAm() {
        return clKlAm;
    }

    public void setClKlAm(double clKlAm) {
        this.clKlAm = clKlAm;
    }

    public double getLwClAm() {
        return lwClAm;
    }

    public void setLwClAm(double lwClAm) {
        this.lwClAm = lwClAm;
    }

    public double getLdKlAm() {
        return ldKlAm;
    }

    public void setLdKlAm(double ldKlAm) {
        this.ldKlAm = ldKlAm;
    }

    public double getLlKlAm() {
        return llKlAm;
    }

    public void setLlKlAm(double llKlAm) {
        this.llKlAm = llKlAm;
    }

    public double getCwOddWl() {
        return cwOddWl;
    }

    public void setCwOddWl(double cwOddWl) {
        this.cwOddWl = cwOddWl;
    }

    public double getCdOddWl() {
        return cdOddWl;
    }

    public void setCdOddWl(double cdOddWl) {
        this.cdOddWl = cdOddWl;
    }

    public double getClOddWl() {
        return clOddWl;
    }

    public void setClOddWl(double clOddWl) {
        this.clOddWl = clOddWl;
    }

    public double getLwOddWl() {
        return lwOddWl;
    }

    public void setLwOddWl(double lwOddWl) {
        this.lwOddWl = lwOddWl;
    }

    public double getLdOddWl() {
        return ldOddWl;
    }

    public void setLdOddWl(double ldOddWl) {
        this.ldOddWl = ldOddWl;
    }

    public double getLlOddWl() {
        return llOddWl;
    }

    public void setLlOddWl(double llOddWl) {
        this.llOddWl = llOddWl;
    }

    public double getCwKlWl() {
        return cwKlWl;
    }

    public void setCwKlWl(double cwKlWl) {
        this.cwKlWl = cwKlWl;
    }

    public double getCdKlWl() {
        return cdKlWl;
    }

    public void setCdKlWl(double cdKlWl) {
        this.cdKlWl = cdKlWl;
    }

    public double getClKlWl() {
        return clKlWl;
    }

    public void setClKlWl(double clKlWl) {
        this.clKlWl = clKlWl;
    }

    public double getLwClWl() {
        return lwClWl;
    }

    public void setLwClWl(double lwClWl) {
        this.lwClWl = lwClWl;
    }

    public double getLdKlWl() {
        return ldKlWl;
    }

    public void setLdKlWl(double ldKlWl) {
        this.ldKlWl = ldKlWl;
    }

    public double getLlKlWl() {
        return llKlWl;
    }

    public void setLlKlWl(double llKlWl) {
        this.llKlWl = llKlWl;
    }

    public double getCwOddLb() {
        return cwOddLb;
    }

    public void setCwOddLb(double cwOddLb) {
        this.cwOddLb = cwOddLb;
    }

    public double getCdOddLb() {
        return cdOddLb;
    }

    public void setCdOddLb(double cdOddLb) {
        this.cdOddLb = cdOddLb;
    }

    public double getClOddLb() {
        return clOddLb;
    }

    public void setClOddLb(double clOddLb) {
        this.clOddLb = clOddLb;
    }

    public double getLwOddLb() {
        return lwOddLb;
    }

    public void setLwOddLb(double lwOddLb) {
        this.lwOddLb = lwOddLb;
    }

    public double getLdOddLb() {
        return ldOddLb;
    }

    public void setLdOddLb(double ldOddLb) {
        this.ldOddLb = ldOddLb;
    }

    public double getLlOddLb() {
        return llOddLb;
    }

    public void setLlOddLb(double llOddLb) {
        this.llOddLb = llOddLb;
    }

    public double getCwKlLb() {
        return cwKlLb;
    }

    public void setCwKlLb(double cwKlLb) {
        this.cwKlLb = cwKlLb;
    }

    public double getCdKlLb() {
        return cdKlLb;
    }

    public void setCdKlLb(double cdKlLb) {
        this.cdKlLb = cdKlLb;
    }

    public double getClKlLb() {
        return clKlLb;
    }

    public void setClKlLb(double clKlLb) {
        this.clKlLb = clKlLb;
    }

    public double getLwClLb() {
        return lwClLb;
    }

    public void setLwClLb(double lwClLb) {
        this.lwClLb = lwClLb;
    }

    public double getLdKlLb() {
        return ldKlLb;
    }

    public void setLdKlLb(double ldKlLb) {
        this.ldKlLb = ldKlLb;
    }

    public double getLlKlLb() {
        return llKlLb;
    }

    public void setLlKlLb(double llKlLb) {
        this.llKlLb = llKlLb;
    }

    public double getScwOdd() {
        return scwOdd;
    }

    public void setScwOdd(double scwOdd) {
        this.scwOdd = scwOdd;
    }

    public double getScdOdd() {
        return scdOdd;
    }

    public void setScdOdd(double scdOdd) {
        this.scdOdd = scdOdd;
    }

    public double getSclOdd() {
        return sclOdd;
    }

    public void setSclOdd(double sclOdd) {
        this.sclOdd = sclOdd;
    }

    public double getSlwOdd() {
        return slwOdd;
    }

    public void setSlwOdd(double slwOdd) {
        this.slwOdd = slwOdd;
    }

    public double getSldOdd() {
        return sldOdd;
    }

    public void setSldOdd(double sldOdd) {
        this.sldOdd = sldOdd;
    }

    public double getSllOdd() {
        return sllOdd;
    }

    public void setSllOdd(double sllOdd) {
        this.sllOdd = sllOdd;
    }

    public double getScwKl() {
        return scwKl;
    }

    public void setScwKl(double scwKl) {
        this.scwKl = scwKl;
    }

    public double getScdKl() {
        return scdKl;
    }

    public void setScdKl(double scdKl) {
        this.scdKl = scdKl;
    }

    public double getSclKl() {
        return sclKl;
    }

    public void setSclKl(double sclKl) {
        this.sclKl = sclKl;
    }

    public double getSlwCl() {
        return slwCl;
    }

    public void setSlwCl(double slwCl) {
        this.slwCl = slwCl;
    }

    public double getSldKl() {
        return sldKl;
    }

    public void setSldKl(double sldKl) {
        this.sldKl = sldKl;
    }

    public double getSllKl() {
        return sllKl;
    }

    public void setSllKl(double sllKl) {
        this.sllKl = sllKl;
    }

    public double getCzWaterAm() {
        return czWaterAm;
    }

    public void setCzWaterAm(double czWaterAm) {
        this.czWaterAm = czWaterAm;
    }

    public double getCpAm() {
        return cpAm;
    }

    public void setCpAm(double cpAm) {
        this.cpAm = cpAm;
    }

    public double getCkWaterAm() {
        return ckWaterAm;
    }

    public void setCkWaterAm(double ckWaterAm) {
        this.ckWaterAm = ckWaterAm;
    }

    public double getLzWaterAm() {
        return lzWaterAm;
    }

    public void setLzWaterAm(double lzWaterAm) {
        this.lzWaterAm = lzWaterAm;
    }

    public double getLpAm() {
        return lpAm;
    }

    public void setLpAm(double lpAm) {
        this.lpAm = lpAm;
    }

    public double getLkWaterAm() {
        return lkWaterAm;
    }

    public void setLkWaterAm(double lkWaterAm) {
        this.lkWaterAm = lkWaterAm;
    }

    public double getCzWaterLb() {
        return czWaterLb;
    }

    public void setCzWaterLb(double czWaterLb) {
        this.czWaterLb = czWaterLb;
    }

    public double getCpLb() {
        return cpLb;
    }

    public void setCpLb(double cpLb) {
        this.cpLb = cpLb;
    }

    public double getCkWaterLb() {
        return ckWaterLb;
    }

    public void setCkWaterLb(double ckWaterLb) {
        this.ckWaterLb = ckWaterLb;
    }

    public double getLzWaterLb() {
        return lzWaterLb;
    }

    public void setLzWaterLb(double lzWaterLb) {
        this.lzWaterLb = lzWaterLb;
    }

    public double getLpLb() {
        return lpLb;
    }

    public void setLpLb(double lpLb) {
        this.lpLb = lpLb;
    }

    public double getLkWaterLb() {
        return lkWaterLb;
    }

    public void setLkWaterLb(double lkWaterLb) {
        this.lkWaterLb = lkWaterLb;
    }

    public double getSczWater() {
        return sczWater;
    }

    public void setSczWater(double sczWater) {
        this.sczWater = sczWater;
    }

    public double getScp() {
        return scp;
    }

    public void setScp(double scp) {
        this.scp = scp;
    }

    public double getSckWater() {
        return sckWater;
    }

    public void setSckWater(double sckWater) {
        this.sckWater = sckWater;
    }

    public double getSlzWater() {
        return slzWater;
    }

    public void setSlzWater(double slzWater) {
        this.slzWater = slzWater;
    }

    public double getSlp() {
        return slp;
    }

    public void setSlp(double slp) {
        this.slp = slp;
    }

    public double getSlkWater() {
        return slkWater;
    }

    public void setSlkWater(double slkWater) {
        this.slkWater = slkWater;
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
}
