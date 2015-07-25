package com.ldl.lotteryodds;

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

    private JdbcTemplate jdbcTemplate;
    /**
     * 返回实体列表
     *
     * @param r
     * @return
     */
    public List<Entity500> ConventToList(ResultSet r) {
        List<Entity500> list = new ArrayList<>();
        try {
            ResultSetMetaData rm = r.getMetaData();
            ArrayList<String> arr = new ArrayList<>();
            for (int i = 1; i <= rm.getColumnCount(); i++)
                arr.add(rm.getColumnName(i));
            while (r.next()) {
                Entity500 m = new Entity500();
                if (arr.contains("id")) m.setId(r.getInt("id"));
                if (arr.contains("matchid")) m.setMatchid(r.getString("matchid"));
                if (arr.contains("hwOdd")) m.setHwOdd(r.getString("hwOdd"));
                if (arr.contains("hdOdd")) m.setHdOdd(r.getString("hdOdd"));
                if (arr.contains("hlOdd")) m.setHlOdd(r.getString("hlOdd"));
                if (arr.contains("rhwOdd")) m.setRhwOdd(r.getString("rhwOdd"));
                if (arr.contains("rhdOdd")) m.setRhdOdd(r.getString("rhdOdd"));
                if (arr.contains("rhlOdd")) m.setRhlOdd(r.getString("rhlOdd"));
                if (arr.contains("date")) m.setDate(r.getString("date"));
                if (arr.contains("content")) m.setContent(r.getString("content"));
                list.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != r)
                    r.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 批量插入
     * @param entity500s
     */
    public int batchInsert(List<Entity500> entity500s){
        String sql = "insert into lotteryodds(matchid,content,hwOdd," +
                "hdOdd,hlOdd,rhwOdd,rhdOdd,rhlOdd,date)values(?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Entity500 entity500 : entity500s) {
            Object[] args = {entity500.getMatchid(),entity500.getContent(),entity500.getHwOdd(),
                    entity500.getHdOdd(),entity500.getHlOdd(),entity500.getRhwOdd(),entity500.getRhdOdd(),
            entity500.getRhlOdd(),entity500.getDate()};
            batchArgs.add(args);
        }
        return jdbcTemplate.batchUpdate(sql, batchArgs).length;
    }

    /**
     *
     * @param wodd 主胜赔率
     * @param dodd 主平赔率
     * @param lodd 主负赔率
     * @param type 1 不让球  2 让球
     * @return
     */
    public List<Entity500> getListsWithOdds(String wodd,String dodd,String lodd,int type){
        String wheresql;
        if(type==1){
            wheresql = "hwOdd = ? and hdOdd = ? and hlOdd = ?";
        }else {
            wheresql = "rhwOdd = ? and rhdOdd = ? and rhlOdd = ?";
        }
        String sql = "select * from lotteryodds where "+wheresql;
        return jdbcTemplate.query(sql, resultSet -> {
            List<Entity500> list = ConventToList(resultSet);
            return list;
        },new Object[]{wodd,dodd,lodd});
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
