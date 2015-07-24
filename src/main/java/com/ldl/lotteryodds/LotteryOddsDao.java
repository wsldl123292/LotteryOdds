package com.ldl.lotteryodds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/7/24 17:41
 */
@Repository
public class LotteryOddsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 批量插入
     * @param entity500s
     */
    public void batchInsert(List<Entity500> entity500s){
        String sql = "insert into lotteryodds(id,content,hwOdd," +
                "hdOdd,hlOdd,rhwOdd,rhdOdd,rhlOdd)values(?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Entity500 entity500 : entity500s) {
            Object[] args = {entity500.getId(),entity500.getContent(),entity500.getHwOdd(),
            entity500.getHdOdd(),entity500.getHlOdd(),entity500.getRhwOdd(),entity500.getRhdOdd(),
            entity500.getRhlOdd()};
            batchArgs.add(args);
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
