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
    public void batchDeleteSourceById(List<Entity500> entity500s){
        String sql = "insert into ";
        List<Object[]> batchArgs = new ArrayList<>();
        for (String sourceId : sourceIds) {
            Object[] args = {sourceId};
            batchArgs.add(args);
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
