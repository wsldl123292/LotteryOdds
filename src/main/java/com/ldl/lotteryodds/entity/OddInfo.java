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
    private String cwOddAm;
    /**
     * 初始主平赔率澳门
     */
    private String cdOddAm;
    /**
     * 初始主负赔率澳门
     */
    private String clOddAm;

    /**
     * 最终主胜赔率澳门
     */
    private String lwOddAm;
    /**
     * 最终主平赔率澳门
     */
    private String ldOddAm;
    /**
     * 最终主负赔率澳门
     */
    private String llOddAm;

    /**
     * 初始主胜凯利指数澳门
     */
    private String cwKlAm;
    /**
     * 初始主平凯利指数澳门
     */
    private String cdKlAm;
    /**
     * 初始主负凯利指数澳门
     */
    private String clKlAm;
    /**
     * 最终主胜凯利指数澳门
     */
    private String lwKlAm;
    /**
     * 最终主平凯利指数澳门
     */
    private String ldKlAm;
    /**
     * 最终主负凯利指数澳门
     */
    private String llKlAm;


    /** 威廉希尔 */

    /**
     * 初始主胜赔率威廉希尔
     */
    private String cwOddWl;
    /**
     * 初始主平赔率威廉希尔
     */
    private String cdOddWl;
    /**
     * 初始主负赔率威廉希尔
     */
    private String clOddWl;

    /**
     * 最终主胜赔率威廉希尔
     */
    private String lwOddWl;
    /**
     * 最终主平赔率威廉希尔
     */
    private String ldOddWl;
    /**
     * 最终主负赔率威廉希尔
     */
    private String llOddWl;

    /**
     * 初始主胜凯利指数威廉希尔
     */
    private String cwKlWl;
    /**
     * 初始主平凯利指数威廉希尔
     */
    private String cdKlWl;
    /**
     * 初始主负凯利指数威廉希尔
     */
    private String clKlWl;
    /**
     * 最终主胜凯利指数威廉希尔
     */
    private String lwKlWl;
    /**
     * 最终主平凯利指数威廉希尔
     */
    private String ldKlWl;
    /**
     * 最终主负凯利指数威廉希尔
     */
    private String llKlWl;


    /** 立博 */

    /**
     * 初始主胜赔率立博
     */
    private String cwOddLb;
    /**
     * 初始主平赔率立博
     */
    private String cdOddLb;
    /**
     * 初始主负赔率立博
     */
    private String clOddLb;

    /**
     * 最终主胜赔率立博
     */
    private String lwOddLb;
    /**
     * 最终主平赔率立博
     */
    private String ldOddLb;
    /**
     * 最终主负赔率立博
     */
    private String llOddLb;

    /**
     * 初始主胜凯利指数立博
     */
    private String cwKlLb;
    /**
     * 初始主平凯利指数立博
     */
    private String cdKlLb;
    /**
     * 初始主负凯利指数立博
     */
    private String clKlLb;
    /**
     * 最终主胜凯利指数立博
     */
    private String lwKlLb;
    /**
     * 最终主平凯利指数立博
     */
    private String ldKlLb;
    /**
     * 最终主负凯利指数立博
     */
    private String llKlLb;


    /**
     * 初始平均主胜赔率
     */
    private String scwOdd;
    /**
     * 初始平均主平赔率
     */
    private String scdOdd;
    /**
     * 初始平均主负赔率
     */
    private String sclOdd;

    /**
     * 最终平均主胜赔率
     */
    private String slwOdd;
    /**
     * 最终平均主平赔率
     */
    private String sldOdd;
    /**
     * 最终平均主负赔率
     */
    private String sllOdd;


    /**
     * 初始平均主胜凯利指数
     */
    private String scwKl;
    /**
     * 初始平均主平凯利指数
     */
    private String scdKl;
    /**
     * 初始平均主负凯利指数
     */
    private String sclKl;
    /**
     * 最终平均主胜凯利指数
     */
    private String slwKl;
    /**
     * 最终平均主平凯利指数
     */
    private String sldKl;
    /**
     * 最终平均主负凯利指数
     */
    private String sllKl;


    /** 澳门 */

    /**
     * 初始主水位澳门
     */
    private String czWaterAm;
    /**
     * 初始盘口澳门
     */
    private String cpAm;
    /**
     * 初始客水位澳门
     */
    private String ckWaterAm;

    /**
     * 最终主水位澳门
     */
    private String lzWaterAm;
    /**
     * 最终盘口澳门
     */
    private String lpAm;
    /**
     * 最终客水位澳门
     */
    private String lkWaterAm;


    /** 立博 */

    /**
     * 初始主水位立博
     */
    private String czWaterLb;
    /**
     * 初始盘口立博
     */
    private String cpLb;
    /**
     * 初始客水位立博
     */
    private String ckWaterLb;

    /**
     * 最终主水位立博
     */
    private String lzWaterLb;
    /**
     * 最终盘口立博
     */
    private String lpLb;
    /**
     * 最终客水位立博
     */
    private String lkWaterLb;


    /**
     * 平均初始主水位
     */
    private String sczWater;
    /**
     * 平均初始盘口
     */
    private String scp;
    /**
     * 平均初始客水位
     */
    private String sckWater;

    /**
     * 平均最终主水位
     */
    private String slzWater;
    /**
     * 平均最终盘口
     */
    private String slp;
    /**
     * 平均最终客水位
     */
    private String slkWater;

    /**
     * 比赛时间
     */
    private String date;

    /**
     * 主队胜
     */
    private String win;
    /**
     * 平
     */
    private String down;
    /**
     * 负
     */
    private String lose;
    /**
     * 主队近10场胜
     */
    private String zwin;
    /**
     * 主队近10场平
     */
    private String zdown;
    /**
     * 主队近10场负
     */
    private String zlose;
    /**
     * 客队近10场胜
     */
    private String kwin;
    /**
     * 客队近10场平
     */
    private String kdown;
    /**
     * 客队近10场负
     */
    private String klose;
    /**
     * 主队主场胜
     */
    private String zzwin;
    /**
     * 主队主场平
     */
    private String zzdown;
    /**
     * 主队主场负
     */
    private String zzlose;
    /**
     * 客队客场胜
     */
    private String kkwin;
    /**
     * 客队客场平
     */
    private String kkdown;
    /**
     * 客队客场负
     */
    private String kklose;

    /**
     * 主队近10场进球
     */
    private int zjscore;
    /**
     * 主队近10场丢球
     */
    private int zlscore;
    /**
     * 客队近10场进球
     */
    private int kjscore;
    /**
     * 客队近10场丢球
     */
    private int klscore;
    /**
     * 主队近10场主场进球
     */
    private int zzjscore;
    /**
     * 主队近10场主场丢球
     */
    private int zzlscore;
    /**
     * 客队近10场客场进球
     */
    private int kkjscore;
    /**
     * 客队近10场客场丢球
     */
    private int kklsocre;

    public int getZjscore() {
        return zjscore;
    }

    public void setZjscore(int zjscore) {
        this.zjscore = zjscore;
    }

    public int getZlscore() {
        return zlscore;
    }

    public void setZlscore(int zlscore) {
        this.zlscore = zlscore;
    }

    public int getKjscore() {
        return kjscore;
    }

    public void setKjscore(int kjscore) {
        this.kjscore = kjscore;
    }

    public int getKlscore() {
        return klscore;
    }

    public void setKlscore(int klscore) {
        this.klscore = klscore;
    }

    public int getZzjscore() {
        return zzjscore;
    }

    public void setZzjscore(int zzjscore) {
        this.zzjscore = zzjscore;
    }

    public int getZzlscore() {
        return zzlscore;
    }

    public void setZzlscore(int zzlscore) {
        this.zzlscore = zzlscore;
    }

    public int getKkjscore() {
        return kkjscore;
    }

    public void setKkjscore(int kkjscore) {
        this.kkjscore = kkjscore;
    }

    public int getKklsocre() {
        return kklsocre;
    }

    public void setKklsocre(int kklsocre) {
        this.kklsocre = kklsocre;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getCwOddAm() {
        return cwOddAm;
    }

    public void setCwOddAm(String cwOddAm) {
        this.cwOddAm = cwOddAm;
    }

    public String getCdOddAm() {
        return cdOddAm;
    }

    public void setCdOddAm(String cdOddAm) {
        this.cdOddAm = cdOddAm;
    }

    public String getClOddAm() {
        return clOddAm;
    }

    public void setClOddAm(String clOddAm) {
        this.clOddAm = clOddAm;
    }

    public String getLwOddAm() {
        return lwOddAm;
    }

    public void setLwOddAm(String lwOddAm) {
        this.lwOddAm = lwOddAm;
    }

    public String getLdOddAm() {
        return ldOddAm;
    }

    public void setLdOddAm(String ldOddAm) {
        this.ldOddAm = ldOddAm;
    }

    public String getLlOddAm() {
        return llOddAm;
    }

    public void setLlOddAm(String llOddAm) {
        this.llOddAm = llOddAm;
    }

    public String getCwKlAm() {
        return cwKlAm;
    }

    public void setCwKlAm(String cwKlAm) {
        this.cwKlAm = cwKlAm;
    }

    public String getCdKlAm() {
        return cdKlAm;
    }

    public void setCdKlAm(String cdKlAm) {
        this.cdKlAm = cdKlAm;
    }

    public String getClKlAm() {
        return clKlAm;
    }

    public void setClKlAm(String clKlAm) {
        this.clKlAm = clKlAm;
    }

    public String getLdKlAm() {
        return ldKlAm;
    }

    public void setLdKlAm(String ldKlAm) {
        this.ldKlAm = ldKlAm;
    }

    public String getLlKlAm() {
        return llKlAm;
    }

    public void setLlKlAm(String llKlAm) {
        this.llKlAm = llKlAm;
    }

    public String getCwOddWl() {
        return cwOddWl;
    }

    public void setCwOddWl(String cwOddWl) {
        this.cwOddWl = cwOddWl;
    }

    public String getCdOddWl() {
        return cdOddWl;
    }

    public void setCdOddWl(String cdOddWl) {
        this.cdOddWl = cdOddWl;
    }

    public String getClOddWl() {
        return clOddWl;
    }

    public void setClOddWl(String clOddWl) {
        this.clOddWl = clOddWl;
    }

    public String getLwOddWl() {
        return lwOddWl;
    }

    public void setLwOddWl(String lwOddWl) {
        this.lwOddWl = lwOddWl;
    }

    public String getLdOddWl() {
        return ldOddWl;
    }

    public void setLdOddWl(String ldOddWl) {
        this.ldOddWl = ldOddWl;
    }

    public String getLlOddWl() {
        return llOddWl;
    }

    public void setLlOddWl(String llOddWl) {
        this.llOddWl = llOddWl;
    }

    public String getCwKlWl() {
        return cwKlWl;
    }

    public void setCwKlWl(String cwKlWl) {
        this.cwKlWl = cwKlWl;
    }

    public String getCdKlWl() {
        return cdKlWl;
    }

    public void setCdKlWl(String cdKlWl) {
        this.cdKlWl = cdKlWl;
    }

    public String getClKlWl() {
        return clKlWl;
    }

    public void setClKlWl(String clKlWl) {
        this.clKlWl = clKlWl;
    }

    public String getLdKlWl() {
        return ldKlWl;
    }

    public void setLdKlWl(String ldKlWl) {
        this.ldKlWl = ldKlWl;
    }

    public String getLlKlWl() {
        return llKlWl;
    }

    public void setLlKlWl(String llKlWl) {
        this.llKlWl = llKlWl;
    }

    public String getCwOddLb() {
        return cwOddLb;
    }

    public void setCwOddLb(String cwOddLb) {
        this.cwOddLb = cwOddLb;
    }

    public String getCdOddLb() {
        return cdOddLb;
    }

    public void setCdOddLb(String cdOddLb) {
        this.cdOddLb = cdOddLb;
    }

    public String getClOddLb() {
        return clOddLb;
    }

    public void setClOddLb(String clOddLb) {
        this.clOddLb = clOddLb;
    }

    public String getLwOddLb() {
        return lwOddLb;
    }

    public void setLwOddLb(String lwOddLb) {
        this.lwOddLb = lwOddLb;
    }

    public String getLdOddLb() {
        return ldOddLb;
    }

    public void setLdOddLb(String ldOddLb) {
        this.ldOddLb = ldOddLb;
    }

    public String getLlOddLb() {
        return llOddLb;
    }

    public void setLlOddLb(String llOddLb) {
        this.llOddLb = llOddLb;
    }

    public String getCwKlLb() {
        return cwKlLb;
    }

    public void setCwKlLb(String cwKlLb) {
        this.cwKlLb = cwKlLb;
    }

    public String getCdKlLb() {
        return cdKlLb;
    }

    public void setCdKlLb(String cdKlLb) {
        this.cdKlLb = cdKlLb;
    }

    public String getClKlLb() {
        return clKlLb;
    }

    public void setClKlLb(String clKlLb) {
        this.clKlLb = clKlLb;
    }

    public String getLdKlLb() {
        return ldKlLb;
    }

    public void setLdKlLb(String ldKlLb) {
        this.ldKlLb = ldKlLb;
    }

    public String getLlKlLb() {
        return llKlLb;
    }

    public void setLlKlLb(String llKlLb) {
        this.llKlLb = llKlLb;
    }

    public String getScwOdd() {
        return scwOdd;
    }

    public void setScwOdd(String scwOdd) {
        this.scwOdd = scwOdd;
    }

    public String getScdOdd() {
        return scdOdd;
    }

    public void setScdOdd(String scdOdd) {
        this.scdOdd = scdOdd;
    }

    public String getSclOdd() {
        return sclOdd;
    }

    public void setSclOdd(String sclOdd) {
        this.sclOdd = sclOdd;
    }

    public String getSlwOdd() {
        return slwOdd;
    }

    public void setSlwOdd(String slwOdd) {
        this.slwOdd = slwOdd;
    }

    public String getSldOdd() {
        return sldOdd;
    }

    public void setSldOdd(String sldOdd) {
        this.sldOdd = sldOdd;
    }

    public String getSllOdd() {
        return sllOdd;
    }

    public void setSllOdd(String sllOdd) {
        this.sllOdd = sllOdd;
    }

    public String getScwKl() {
        return scwKl;
    }

    public void setScwKl(String scwKl) {
        this.scwKl = scwKl;
    }

    public String getScdKl() {
        return scdKl;
    }

    public void setScdKl(String scdKl) {
        this.scdKl = scdKl;
    }

    public String getSclKl() {
        return sclKl;
    }

    public void setSclKl(String sclKl) {
        this.sclKl = sclKl;
    }

    public String getSldKl() {
        return sldKl;
    }

    public void setSldKl(String sldKl) {
        this.sldKl = sldKl;
    }

    public String getSllKl() {
        return sllKl;
    }

    public void setSllKl(String sllKl) {
        this.sllKl = sllKl;
    }

    public String getCzWaterAm() {
        return czWaterAm;
    }

    public void setCzWaterAm(String czWaterAm) {
        this.czWaterAm = czWaterAm;
    }

    public String getCpAm() {
        return cpAm;
    }

    public void setCpAm(String cpAm) {
        this.cpAm = cpAm;
    }

    public String getCkWaterAm() {
        return ckWaterAm;
    }

    public void setCkWaterAm(String ckWaterAm) {
        this.ckWaterAm = ckWaterAm;
    }

    public String getLzWaterAm() {
        return lzWaterAm;
    }

    public void setLzWaterAm(String lzWaterAm) {
        this.lzWaterAm = lzWaterAm;
    }

    public String getLpAm() {
        return lpAm;
    }

    public void setLpAm(String lpAm) {
        this.lpAm = lpAm;
    }

    public String getLkWaterAm() {
        return lkWaterAm;
    }

    public void setLkWaterAm(String lkWaterAm) {
        this.lkWaterAm = lkWaterAm;
    }

    public String getCzWaterLb() {
        return czWaterLb;
    }

    public void setCzWaterLb(String czWaterLb) {
        this.czWaterLb = czWaterLb;
    }

    public String getCpLb() {
        return cpLb;
    }

    public void setCpLb(String cpLb) {
        this.cpLb = cpLb;
    }

    public String getCkWaterLb() {
        return ckWaterLb;
    }

    public void setCkWaterLb(String ckWaterLb) {
        this.ckWaterLb = ckWaterLb;
    }

    public String getLzWaterLb() {
        return lzWaterLb;
    }

    public void setLzWaterLb(String lzWaterLb) {
        this.lzWaterLb = lzWaterLb;
    }

    public String getLpLb() {
        return lpLb;
    }

    public void setLpLb(String lpLb) {
        this.lpLb = lpLb;
    }

    public String getLkWaterLb() {
        return lkWaterLb;
    }

    public void setLkWaterLb(String lkWaterLb) {
        this.lkWaterLb = lkWaterLb;
    }

    public String getSczWater() {
        return sczWater;
    }

    public void setSczWater(String sczWater) {
        this.sczWater = sczWater;
    }

    public String getScp() {
        return scp;
    }

    public void setScp(String scp) {
        this.scp = scp;
    }

    public String getSckWater() {
        return sckWater;
    }

    public void setSckWater(String sckWater) {
        this.sckWater = sckWater;
    }

    public String getSlzWater() {
        return slzWater;
    }

    public void setSlzWater(String slzWater) {
        this.slzWater = slzWater;
    }

    public String getSlp() {
        return slp;
    }

    public void setSlp(String slp) {
        this.slp = slp;
    }

    public String getSlkWater() {
        return slkWater;
    }

    public void setSlkWater(String slkWater) {
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

    public String getLwKlAm() {
        return lwKlAm;
    }

    public void setLwKlAm(String lwKlAm) {
        this.lwKlAm = lwKlAm;
    }

    public String getLwKlLb() {
        return lwKlLb;
    }

    public void setLwKlLb(String lwKlLb) {
        this.lwKlLb = lwKlLb;
    }

    public String getSlwKl() {
        return slwKl;
    }

    public void setSlwKl(String slwKl) {
        this.slwKl = slwKl;
    }

    public String getLwKlWl() {
        return lwKlWl;
    }

    public void setLwKlWl(String lwKlWl) {
        this.lwKlWl = lwKlWl;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getZwin() {
        return zwin;
    }

    public void setZwin(String zwin) {
        this.zwin = zwin;
    }

    public String getZdown() {
        return zdown;
    }

    public void setZdown(String zdown) {
        this.zdown = zdown;
    }

    public String getZlose() {
        return zlose;
    }

    public void setZlose(String zlose) {
        this.zlose = zlose;
    }

    public String getKwin() {
        return kwin;
    }

    public void setKwin(String kwin) {
        this.kwin = kwin;
    }

    public String getKdown() {
        return kdown;
    }

    public void setKdown(String kdown) {
        this.kdown = kdown;
    }

    public String getKlose() {
        return klose;
    }

    public void setKlose(String klose) {
        this.klose = klose;
    }

    public String getZzwin() {
        return zzwin;
    }

    public void setZzwin(String zzwin) {
        this.zzwin = zzwin;
    }

    public String getZzdown() {
        return zzdown;
    }

    public void setZzdown(String zzdown) {
        this.zzdown = zzdown;
    }

    public String getZzlose() {
        return zzlose;
    }

    public void setZzlose(String zzlose) {
        this.zzlose = zzlose;
    }

    public String getKkwin() {
        return kkwin;
    }

    public void setKkwin(String kkwin) {
        this.kkwin = kkwin;
    }

    public String getKkdown() {
        return kkdown;
    }

    public void setKkdown(String kkdown) {
        this.kkdown = kkdown;
    }

    public String getKklose() {
        return kklose;
    }

    public void setKklose(String kklose) {
        this.kklose = kklose;
    }

    @Override
    public String toString() {
        return "比赛Id: " + getMatchId() + "\n" + "主队得分: " + getZscore() + "\n"
                + "客队得分: " + getKscore() + "\n" + "结果: " + getResult()

                + "\n澳门初盘主胜欧赔: " + getCwOddAm() + "\n澳门初盘平局欧赔: " + getCdOddAm()
                + "\n澳门初盘主负欧赔: " + getClOddAm() + "\n澳门终盘主胜欧赔: " + getLwOddAm()
                + "\n澳门终盘平局欧赔: " + getLdOddAm() + "\n澳门终盘主负欧赔: " + getLlOddAm()
                + "\n澳门初盘主胜凯利指数: " + getCwKlAm() + "\n澳门初盘平局凯利指数: " + getCdKlAm()
                + "\n澳门初盘主负凯利指数: " + getClKlAm() + "\n澳门终盘主胜凯利指数: " + getLwKlAm() + "\n澳门终盘平局凯利指数: " + getLdKlAm()
                + "\n澳门终盘主负凯利指数: " + getLlKlAm()

                + "\n立博初盘主胜欧赔: " + getCwOddLb() + "\n立博初盘平局欧赔: " + getCdOddLb()
                + "\n立博初盘主负欧赔: " + getClOddLb() + "\n立博终盘主胜欧赔: " + getLwOddLb()
                + "\n立博终盘平局欧赔: " + getLdOddLb() + "\n立博终盘主负欧赔: " + getLlOddLb()
                + "\n立博初盘主胜凯利指数: " + getCwKlLb() + "\n立博初盘平局凯利指数: " + getCdKlLb()
                + "\n立博初盘主负凯利指数: " + getClKlLb() + "\n立博终盘主胜凯利指数: " + getLwKlLb() + "\n立博终盘平局凯利指数: " + getLdKlLb()
                + "\n立博终盘主负凯利指数: " + getLlKlLb()

                + "\n威廉希尔初盘主胜欧赔: " + getCwOddWl() + "\n威廉希尔初盘平局欧赔: " + getCdOddWl()
                + "\n威廉希尔初盘主负欧赔: " + getClOddWl() + "\n威廉希尔终盘主胜欧赔: " + getLwOddWl()
                + "\n威廉希尔终盘平局欧赔: " + getLdOddWl() + "\n威廉希尔终盘主负欧赔: " + getLlOddWl()
                + "\n威廉希尔初盘主胜凯利指数: " + getCwKlWl() + "\n威廉希尔初盘平局凯利指数: " + getCdKlWl()
                + "\n威廉希尔初盘主负凯利指数: " + getClKlWl() + "\n威廉希尔终盘主胜凯利指数: " + getLwKlWl() + "\n威廉希尔终盘平局凯利指数: " + getLdKlWl()
                + "\n威廉希尔终盘主负凯利指数: " + getLlKlWl()


                + "\n澳门初盘主胜水位: " + getCzWaterAm() + "\n澳门初盘盘口: " + getCpAm()
                + "\n澳门初盘主负水位: " + getCkWaterAm() + "\n澳门终盘主胜水位: " + getLzWaterAm()
                + "\n澳门终盘盘口: " + getLpAm() + "\n澳门终盘主负水位: " + getLkWaterAm()

                + "\n立博初盘主胜水位: " + getCzWaterLb() + "\n立博初盘盘口: " + getCpLb()
                + "\n立博初盘主负水位: " + getCkWaterLb() + "\n立博终盘主胜水位: " + getLzWaterLb()
                + "\n立博终盘盘口: " + getLpLb() + "\n立博终盘主负水位: " + getLkWaterLb();

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    private int type;

    public int getType() {
        if (getResult() == 3) {
            return 0;
        } else if (getResult() == 0 || getResult() == 1) {
            return 1;
        }
        return 2;
    }

    public void setType(int type) {
        this.type = type;
    }


    /**
     * 澳门初盘大球水位
     */
    private String cDWaterAm;
    /**
     * 澳门初盘小球水位
     */
    private String cXWaterAm;
    /**
     * 澳门终盘大球水位
     */
    private String lDWaterAm;
    /**
     * 澳门终盘小球水位
     */
    private String lXWaterAm;
    /**
     * 澳门初盘盘口
     */
    private String cPDXAm;
    /**
     * 澳门终盘盘口
     */
    private String lPDXAm;

    /**
     * 立博初盘大球水位
     */
    private String cDWaterLb;
    /**
     * 立博初盘小球水位
     */
    private String cXWaterLb;
    /**
     * 立博终盘大球水位
     */
    private String lDWaterLb;
    /**
     * 立博终盘小球水位
     */
    private String lXWaterLb;
    /**
     * 立博初盘盘口
     */
    private String cPDXLb;
    /**
     * 立博终盘盘口
     */
    private String lPDXLb;


    /**
     * 威廉希尔初盘大球水位
     */
    private String cDWaterWl;
    /**
     * 威廉希尔初盘小球水位
     */
    private String cXWaterWl;
    /**
     * 威廉希尔终盘大球水位
     */
    private String lDWaterWl;
    /**
     * 威廉希尔终盘小球水位
     */
    private String lXWaterWl;
    /**
     * 威廉希尔初盘盘口
     */
    private String cPDXWl;
    /**
     * 威廉希尔终盘盘口
     */
    private String lPDXWl;


    /**
     * 联赛Id
     */
    private String sid;


    /**
     * 联赛序号
     */
    private String number;

    public String getcDWaterAm() {
        return cDWaterAm;
    }

    public void setcDWaterAm(String cDWaterAm) {
        this.cDWaterAm = cDWaterAm;
    }

    public String getcXWaterAm() {
        return cXWaterAm;
    }

    public void setcXWaterAm(String cXWaterAm) {
        this.cXWaterAm = cXWaterAm;
    }

    public String getlXWaterAm() {
        return lXWaterAm;
    }

    public void setlXWaterAm(String lXWaterAm) {
        this.lXWaterAm = lXWaterAm;
    }

    public String getcPDXAm() {
        return cPDXAm;
    }

    public void setcPDXAm(String cPDXAm) {
        this.cPDXAm = cPDXAm;
    }

    public String getlPDXAm() {
        return lPDXAm;
    }

    public void setlPDXAm(String lPDXAm) {
        this.lPDXAm = lPDXAm;
    }

    public String getcDWaterLb() {
        return cDWaterLb;
    }

    public void setcDWaterLb(String cDWaterLb) {
        this.cDWaterLb = cDWaterLb;
    }

    public String getcXWaterLb() {
        return cXWaterLb;
    }

    public void setcXWaterLb(String cXWaterLb) {
        this.cXWaterLb = cXWaterLb;
    }

    public String getlXWaterLb() {
        return lXWaterLb;
    }

    public void setlXWaterLb(String lXWaterLb) {
        this.lXWaterLb = lXWaterLb;
    }

    public String getcPDXLb() {
        return cPDXLb;
    }

    public void setcPDXLb(String cPDXLb) {
        this.cPDXLb = cPDXLb;
    }

    public String getlPDXLb() {
        return lPDXLb;
    }

    public void setlPDXLb(String lPDXLb) {
        this.lPDXLb = lPDXLb;
    }

    public String getcDWaterWl() {
        return cDWaterWl;
    }

    public void setcDWaterWl(String cDWaterWl) {
        this.cDWaterWl = cDWaterWl;
    }

    public String getcXWaterWl() {
        return cXWaterWl;
    }

    public void setcXWaterWl(String cXWaterWl) {
        this.cXWaterWl = cXWaterWl;
    }

    public String getlXWaterWl() {
        return lXWaterWl;
    }

    public void setlXWaterWl(String lXWaterWl) {
        this.lXWaterWl = lXWaterWl;
    }

    public String getcPDXWl() {
        return cPDXWl;
    }

    public void setcPDXWl(String cPDXWl) {
        this.cPDXWl = cPDXWl;
    }

    public String getlPDXWl() {
        return lPDXWl;
    }

    public void setlPDXWl(String lPDXWl) {
        this.lPDXWl = lPDXWl;
    }

    public String getlDWaterAm() {
        return lDWaterAm;
    }

    public void setlDWaterAm(String lDWaterAm) {
        this.lDWaterAm = lDWaterAm;
    }

    public String getlDWaterLb() {
        return lDWaterLb;
    }

    public void setlDWaterLb(String lDWaterLb) {
        this.lDWaterLb = lDWaterLb;
    }

    public String getlDWaterWl() {
        return lDWaterWl;
    }

    public void setlDWaterWl(String lDWaterWl) {
        this.lDWaterWl = lDWaterWl;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
