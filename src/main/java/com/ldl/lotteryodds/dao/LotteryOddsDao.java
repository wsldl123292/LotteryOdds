package com.ldl.lotteryodds.dao;

import com.ldl.lotteryodds.entity.Entity500;
import com.ldl.lotteryodds.entity.OddInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/7/24 17:41
 */
public class LotteryOddsDao {

    /**
     * jdbc
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 返回实体列表
     *
     * @param resultSet 结果集
     * @return list
     */
    public List<Entity500> conventToList(ResultSet resultSet) {
        final List<Entity500> list = new ArrayList<>();
        try {
            final ResultSetMetaData rm = resultSet.getMetaData();
            final ArrayList<String> arr = new ArrayList<>();
            for (int i = 1; i <= rm.getColumnCount(); i++) {
                arr.add(rm.getColumnName(i));
            }
            while (resultSet.next()) {
                final Entity500 m = new Entity500();
                if (arr.contains("id")) {
                    m.setId(resultSet.getInt("id"));
                }
                if (arr.contains("matchid")) {
                    m.setMatchid(resultSet.getString("matchid"));
                }
                if (arr.contains("hwOdd")) {
                    m.setHwOdd(resultSet.getString("hwOdd"));
                }
                if (arr.contains("hdOdd")) {
                    m.setHdOdd(resultSet.getString("hdOdd"));
                }
                if (arr.contains("hlOdd")) {
                    m.setHlOdd(resultSet.getString("hlOdd"));
                }
                if (arr.contains("rhwOdd")) {
                    m.setRhwOdd(resultSet.getString("rhwOdd"));
                }
                if (arr.contains("rhdOdd")) {
                    m.setRhdOdd(resultSet.getString("rhdOdd"));
                }
                if (arr.contains("rhlOdd")) {
                    m.setRhlOdd(resultSet.getString("rhlOdd"));
                }
                if (arr.contains("date")) {
                    m.setDate(resultSet.getString("date"));
                }
                if (arr.contains("content")) {
                    m.setContent(resultSet.getString("content"));
                }
                list.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != resultSet)
                    resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }


    /**
     * 返回实体列表
     *
     * @param resultSet 结果集
     * @return list
     */
    public List<OddInfo> conventToOddInfoList(ResultSet resultSet) {
        final List<OddInfo> list = new ArrayList<>();
        try {
            final ResultSetMetaData rm = resultSet.getMetaData();
            final ArrayList<String> arr = new ArrayList<>();
            for (int i = 1; i <= rm.getColumnCount(); i++) {
                arr.add(rm.getColumnName(i));
            }
            while (resultSet.next()) {
                final OddInfo m = new OddInfo();
                if (arr.contains("matchId")) {
                    m.setMatchId(resultSet.getString("matchId"));
                }
                if (arr.contains("result")) {
                    m.setResult(resultSet.getInt("result"));
                }
                if (arr.contains("zscore")) {
                    m.setZscore(resultSet.getInt("zscore"));
                }
                if (arr.contains("kscore")) {
                    m.setKscore(resultSet.getInt("kscore"));
                }
                if (arr.contains("cwOddAm")) {
                    m.setCwOddAm(resultSet.getString("cwOddAm"));
                }
                if (arr.contains("cdOddAm")) {
                    m.setCdOddAm(resultSet.getString("cdOddAm"));
                }
                if (arr.contains("clOddAm")) {
                    m.setClOddAm(resultSet.getString("clOddAm"));
                }
                if (arr.contains("lwOddAm")) {
                    m.setLwOddAm(resultSet.getString("lwOddAm"));
                }
                if (arr.contains("ldOddAm")) {
                    m.setLdOddAm(resultSet.getString("ldOddAm"));
                }
                if (arr.contains("llOddAm")) {
                    m.setLlOddAm(resultSet.getString("llOddAm"));
                }
                if (arr.contains("cwKlAm")) {
                    m.setCwKlAm(resultSet.getString("cwKlAm"));
                }
                if (arr.contains("cdKlAm")) {
                    m.setCdKlAm(resultSet.getString("cdKlAm"));
                }
                if (arr.contains("clKlAm")) {
                    m.setClKlAm(resultSet.getString("clKlAm"));
                }
                if (arr.contains("lwKlAm")) {
                    m.setLwKlAm(resultSet.getString("lwKlAm"));
                }
                if (arr.contains("ldKlAm")) {
                    m.setLdKlAm(resultSet.getString("ldKlAm"));
                }
                if (arr.contains("llKlAm")) {
                    m.setLlKlAm(resultSet.getString("llKlAm"));
                }
                if (arr.contains("cwOddWl")) {
                    m.setCwOddWl(resultSet.getString("cwOddWl"));
                }
                if (arr.contains("cdOddWl")) {
                    m.setCdOddWl(resultSet.getString("cdOddWl"));
                }
                if (arr.contains("clOddWl")) {
                    m.setClOddWl(resultSet.getString("clOddWl"));
                }
                if (arr.contains("lwOddWl")) {
                    m.setLwOddWl(resultSet.getString("lwOddWl"));
                }
                if (arr.contains("ldOddWl")) {
                    m.setLdOddWl(resultSet.getString("ldOddWl"));
                }
                if (arr.contains("llOddWl")) {
                    m.setLlOddWl(resultSet.getString("llOddWl"));
                }
                if (arr.contains("cwKlWl")) {
                    m.setCwKlWl(resultSet.getString("cwKlWl"));
                }
                if (arr.contains("cdKlWl")) {
                    m.setCdKlWl(resultSet.getString("cdKlWl"));
                }
                if (arr.contains("clKlWl")) {
                    m.setClKlWl(resultSet.getString("clKlWl"));
                }
                if (arr.contains("lwKlWl")) {
                    m.setLwKlWl(resultSet.getString("lwKlWl"));
                }
                if (arr.contains("ldKlWl")) {
                    m.setLdKlWl(resultSet.getString("ldKlWl"));
                }
                if (arr.contains("llKlWl")) {
                    m.setLlKlWl(resultSet.getString("llKlWl"));
                }
                if (arr.contains("cwOddLb")) {
                    m.setCwOddLb(resultSet.getString("cwOddLb"));
                }
                if (arr.contains("cdOddLb")) {
                    m.setCdOddLb(resultSet.getString("cdOddLb"));
                }
                if (arr.contains("clOddLb")) {
                    m.setClOddLb(resultSet.getString("clOddLb"));
                }
                if (arr.contains("lwOddLb")) {
                    m.setLwOddLb(resultSet.getString("lwOddLb"));
                }
                if (arr.contains("ldOddLb")) {
                    m.setLdOddLb(resultSet.getString("ldOddLb"));
                }
                if (arr.contains("llOddLb")) {
                    m.setLlOddLb(resultSet.getString("llOddLb"));
                }
                if (arr.contains("cwKlLb")) {
                    m.setCwKlLb(resultSet.getString("cwKlLb"));
                }
                if (arr.contains("cdKlLb")) {
                    m.setCdKlLb(resultSet.getString("cdKlLb"));
                }
                if (arr.contains("clKlLb")) {
                    m.setClKlLb(resultSet.getString("clKlLb"));
                }
                if (arr.contains("lwKlLb")) {
                    m.setLwKlLb(resultSet.getString("lwKlLb"));
                }
                if (arr.contains("ldKlLb")) {
                    m.setLdKlLb(resultSet.getString("ldKlLb"));
                }
                if (arr.contains("llKlLb")) {
                    m.setLlKlLb(resultSet.getString("llKlLb"));
                }
                if (arr.contains("scwOdd")) {
                    m.setScwOdd(resultSet.getString("scwOdd"));
                }
                if (arr.contains("scdOdd")) {
                    m.setScdOdd(resultSet.getString("scdOdd"));
                }
                if (arr.contains("sclOdd")) {
                    m.setSclOdd(resultSet.getString("sclOdd"));
                }
                if (arr.contains("slwOdd")) {
                    m.setSlwOdd(resultSet.getString("slwOdd"));
                }
                if (arr.contains("sldOdd")) {
                    m.setSldOdd(resultSet.getString("sldOdd"));
                }
                if (arr.contains("sllOdd")) {
                    m.setSllOdd(resultSet.getString("sllOdd"));
                }
                if (arr.contains("scwKl")) {
                    m.setScwKl(resultSet.getString("scwKl"));
                }
                if (arr.contains("scdKl")) {
                    m.setScdKl(resultSet.getString("scdKl"));
                }
                if (arr.contains("sclKl")) {
                    m.setSclKl(resultSet.getString("sclKl"));
                }
                if (arr.contains("slwKl")) {
                    m.setSlwKl(resultSet.getString("slwKl"));
                }
                if (arr.contains("sldKl")) {
                    m.setSldKl(resultSet.getString("sldKl"));
                }
                if (arr.contains("sllKl")) {
                    m.setSllKl(resultSet.getString("sllKl"));
                }
                if (arr.contains("czWaterAm")) {
                    m.setCzWaterAm(resultSet.getString("czWaterAm"));
                }
                if (arr.contains("cpAm")) {
                    m.setCpAm(resultSet.getString("cpAm"));
                }
                if (arr.contains("ckWaterAm")) {
                    m.setCkWaterAm(resultSet.getString("ckWaterAm"));
                }
                if (arr.contains("lzWaterAm")) {
                    m.setLzWaterAm(resultSet.getString("lzWaterAm"));
                }
                if (arr.contains("lpAm")) {
                    m.setLpAm(resultSet.getString("lpAm"));
                }
                if (arr.contains("lkWaterAm")) {
                    m.setLkWaterAm(resultSet.getString("lkWaterAm"));
                }
                if (arr.contains("czWaterLb")) {
                    m.setCzWaterLb(resultSet.getString("czWaterLb"));
                }
                if (arr.contains("cpLb")) {
                    m.setCpLb(resultSet.getString("cpLb"));
                }
                if (arr.contains("ckWaterLb")) {
                    m.setCkWaterLb(resultSet.getString("ckWaterLb"));
                }
                if (arr.contains("lzWaterLb")) {
                    m.setLzWaterLb(resultSet.getString("lzWaterLb"));
                }
                if (arr.contains("lpLb")) {
                    m.setLpLb(resultSet.getString("lpLb"));
                }
                if (arr.contains("lkWaterLb")) {
                    m.setLkWaterLb(resultSet.getString("lkWaterLb"));
                }
                if (arr.contains("sczWater")) {
                    m.setSczWater(resultSet.getString("sczWater"));
                }
                if (arr.contains("scp")) {
                    m.setScp(resultSet.getString("scp"));
                }
                if (arr.contains("sckWater")) {
                    m.setSckWater(resultSet.getString("sckWater"));
                }
                if (arr.contains("slzWater")) {
                    m.setSlzWater(resultSet.getString("slzWater"));
                }
                if (arr.contains("slp")) {
                    m.setSlp(resultSet.getString("slp"));
                }
                if (arr.contains("slkWater")) {
                    m.setSlkWater(resultSet.getString("slkWater"));
                }
                if (arr.contains("date")) {
                    m.setDate(resultSet.getString("date"));
                }
                if (arr.contains("win")) {
                    m.setWin(resultSet.getString("win"));
                }
                if (arr.contains("down")) {
                    m.setDown(resultSet.getString("down"));
                }
                if (arr.contains("lose")) {
                    m.setLose(resultSet.getString("lose"));
                }
                if (arr.contains("zwin")) {
                    m.setZwin(resultSet.getString("zwin"));
                }
                if (arr.contains("zdown")) {
                    m.setZdown(resultSet.getString("zdown"));
                }
                if (arr.contains("zlose")) {
                    m.setZlose(resultSet.getString("zlose"));
                }
                if (arr.contains("kwin")) {
                    m.setKwin(resultSet.getString("kwin"));
                }
                if (arr.contains("kdown")) {
                    m.setKdown(resultSet.getString("kdown"));
                }
                if (arr.contains("klose")) {
                    m.setKlose(resultSet.getString("klose"));
                }
                if (arr.contains("zzwin")) {
                    m.setZzwin(resultSet.getString("zzwin"));
                }
                if (arr.contains("zzdown")) {
                    m.setZzdown(resultSet.getString("zzdown"));
                }
                if (arr.contains("zzlose")) {
                    m.setZzlose(resultSet.getString("zzlose"));
                }
                if (arr.contains("kkwin")) {
                    m.setKkwin(resultSet.getString("kkwin"));
                }
                if (arr.contains("kkdown")) {
                    m.setKkdown(resultSet.getString("kkdown"));
                }
                if (arr.contains("kklose")) {
                    m.setKklose(resultSet.getString("kklose"));
                }

                list.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != resultSet)
                    resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 批量插入
     *
     * @param entity500s 实体
     */
    public int batchInsert(List<Entity500> entity500s) {
        final String sql = "insert into lotteryodds(matchid,content,hwOdd," +
                "hdOdd,hlOdd,rhwOdd,rhdOdd,rhlOdd,date)values(?,?,?,?,?,?,?,?,?)";
        final List<Object[]> batchArgs = new ArrayList<>();
        for (Entity500 entity500 : entity500s) {
            final Object[] args = {entity500.getMatchid(), entity500.getContent(), entity500.getHwOdd(),
                    entity500.getHdOdd(), entity500.getHlOdd(), entity500.getRhwOdd(), entity500.getRhdOdd(),
                    entity500.getRhlOdd(), entity500.getDate()};
            batchArgs.add(args);
        }
        return jdbcTemplate.batchUpdate(sql, batchArgs).length;
    }

    /**
     * 批量插入
     *
     * @param oddInfos 实体
     */
    public int batchInsertOddInfo(List<OddInfo> oddInfos) {
        final String sql = "insert into lotteryoddsnew(matchId,result,zscore,kscore,cwOddAm,cdOddAm,clOddAm,lwOddAm,ldOddAm," +
                "llOddAm,cwKlAm,cdKlAm,clKlAm,lwKlAm,ldKlAm,llKlAm,cwOddWl,cdOddWl,clOddWl,lwOddWl,ldOddWl,llOddWl" +
                ",cwKlWl,cdKlWl,clKlWl,lwKlWl,ldKlWl,llKlWl,cwOddLb,cdOddLb,clOddLb,lwOddLb,ldOddLb,llOddLb,cwKlLb," +
                "cdKlLb,clKlLb,lwKlLb,ldKlLb,llKlLb,scwOdd,scdOdd,sclOdd,slwOdd,sldOdd,sllOdd,scwKl,scdKl,sclKl," +
                "slwKl,sldKl,sllKl,czWaterAm,cpAm,ckWaterAm,lzWaterAm,lpAm,lkWaterAm,czWaterLb,cpLb,ckWaterLb," +
                "lzWaterLb,lpLb,lkWaterLb,sczWater,scp,sckWater,slzWater,slp,slkWater,date,win ,down ,lose,zwin,zdown," +
                "zlose,kwin,kdown,klose,zjscore,zlscore,kjscore,klscore,zzwin,zzdown,zzlose,zzjscore,zzlscore,kkwin,kkdown,kklose,kkjscore,kklscore)values(?,?,?,?,?,?,?,?,?,?" +
                ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final List<Object[]> batchArgs = new ArrayList<>();
        for (OddInfo oddInfo : oddInfos) {
            final Object[] args = {oddInfo.getMatchId(), oddInfo.getResult(), oddInfo.getZscore(),
                    oddInfo.getKscore(), oddInfo.getCwOddAm(), oddInfo.getCdOddAm(), oddInfo.getClOddAm(),
                    oddInfo.getLwOddAm(), oddInfo.getLdOddAm(), oddInfo.getLlOddAm(), oddInfo.getCwKlAm(), oddInfo.getCdKlAm(),
                    oddInfo.getClKlAm(), oddInfo.getLwKlAm(), oddInfo.getLdKlAm(), oddInfo.getLlKlAm(), oddInfo.getCwOddWl(),
                    oddInfo.getCdOddWl(), oddInfo.getClOddWl(), oddInfo.getLwOddWl(), oddInfo.getLdOddWl(), oddInfo.getLlOddWl(),
                    oddInfo.getCwKlWl(), oddInfo.getCdKlWl(), oddInfo.getClKlWl(), oddInfo.getLwKlWl(), oddInfo.getLdKlWl(), oddInfo.getLlKlWl(),
                    oddInfo.getCwOddLb(), oddInfo.getCdOddLb(), oddInfo.getClOddLb(), oddInfo.getLwOddLb(), oddInfo.getLdOddLb(),
                    oddInfo.getLlOddLb(), oddInfo.getCwKlLb(), oddInfo.getCdKlLb(), oddInfo.getClKlLb(), oddInfo.getLwKlLb(),
                    oddInfo.getLdKlLb(), oddInfo.getLlKlLb(), oddInfo.getScwOdd(), oddInfo.getScdOdd(), oddInfo.getSclOdd(),
                    oddInfo.getSlwOdd(), oddInfo.getSldOdd(), oddInfo.getSllOdd(), oddInfo.getScwKl(), oddInfo.getScdKl(),
                    oddInfo.getSclKl(), oddInfo.getSlwKl(), oddInfo.getSldKl(), oddInfo.getSllKl(), oddInfo.getCzWaterAm(),
                    oddInfo.getCpAm(), oddInfo.getCkWaterAm(), oddInfo.getLzWaterAm(), oddInfo.getLpAm(), oddInfo.getLkWaterAm(),
                    oddInfo.getCzWaterLb(), oddInfo.getCpLb(), oddInfo.getCkWaterLb(), oddInfo.getLzWaterLb(), oddInfo.getLpLb(),
                    oddInfo.getLkWaterLb(), oddInfo.getSczWater(), oddInfo.getScp(), oddInfo.getSckWater(), oddInfo.getSlzWater(),
                    oddInfo.getSlp(), oddInfo.getSlkWater(), oddInfo.getDate(), oddInfo.getWin(), oddInfo.getDown(), oddInfo.getLose(),
                    oddInfo.getZwin(), oddInfo.getZdown(), oddInfo.getZlose(), oddInfo.getKwin(),
                    oddInfo.getKdown(), oddInfo.getKlose(), oddInfo.getZjscore(), oddInfo.getZlscore(),
                    oddInfo.getKjscore(), oddInfo.getKlscore(), oddInfo.getZzwin(), oddInfo.getZzdown(),
                    oddInfo.getZzlose(), oddInfo.getZzjscore(), oddInfo.getZzlscore(), oddInfo.getKkwin(),
                    oddInfo.getKkdown(), oddInfo.getKklose(), oddInfo.getKkjscore(), oddInfo.getKklsocre()};
            batchArgs.add(args);
        }
        return jdbcTemplate.batchUpdate(sql, batchArgs).length;
    }


    /**
     * 批量更新
     *
     * @param oddInfos 实体
     */
    public int batchUpdateOddInfo(List<OddInfo> oddInfos) {
        final String sql = "update lotteryoddsnew set win = ?,down = ?,lose=?,zwin=?,zdown=?,zlose=?,kwin=?,kdown=?,klose=?,zjscore=?,zlscore=?,kjscore=?,klscore=?," +
                "zzwin=?,zzdown=?,zzlose=?,zzjscore=?,zzlscore=?,kkwin=?,kkdown=?,kklose=?,kkjscore=?,kklscore=? where matchId=?";
        final List<Object[]> batchArgs = new ArrayList<>();
        for (OddInfo oddInfo : oddInfos) {
            final Object[] args = {oddInfo.getWin(), oddInfo.getDown(), oddInfo.getLose(), oddInfo.getZwin(), oddInfo.getZdown(), oddInfo.getZlose(), oddInfo.getKwin(),
                    oddInfo.getKdown(), oddInfo.getKlose(), oddInfo.getZjscore(), oddInfo.getZlscore(), oddInfo.getKjscore(), oddInfo.getKlscore(), oddInfo.getZzwin(), oddInfo.getZzdown(),
                    oddInfo.getZzlose(), oddInfo.getZzjscore(), oddInfo.getZzlscore(), oddInfo.getKkwin(), oddInfo.getKkdown(), oddInfo.getKklose(), oddInfo.getKkjscore(), oddInfo.getKklsocre(),
                    oddInfo.getMatchId()};
            batchArgs.add(args);
        }
        return jdbcTemplate.batchUpdate(sql, batchArgs).length;
    }

    /**
     * @param wodd 主胜赔率
     * @param dodd 主平赔率
     * @param lodd 主负赔率
     * @param type 1 不让球  2 让球
     * @return list
     */
    public List<Entity500> getListsWithOdds(String wodd, String dodd, String lodd, int type) {
        String wheresql;
        if (type == 1) {
            wheresql = "hwOdd = ? and hdOdd = ? and hlOdd = ?";
        } else {
            wheresql = "rhwOdd = ? and rhdOdd = ? and rhlOdd = ?";
        }
        final String sql = "select * from lotteryodds where " + wheresql;
        return jdbcTemplate.query(sql, resultSet -> {
            final List<Entity500> list = conventToList(resultSet);
            return list;
        }, wodd, dodd, lodd);
    }


    /**
     * 查询oddinfo结果
     *
     * @param sql sql语句
     * @return list
     */
    public List<OddInfo> getOddInfos(String sql) {
        return jdbcTemplate.query(sql, resultSet -> {
            final List<OddInfo> list = conventToOddInfoList(resultSet);
            return list;
        });
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
