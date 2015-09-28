package com.ldl.lotteryodds.controller;

import com.alibaba.fastjson.JSON;
import com.ldl.lotteryodds.dao.LotteryOddsDao;
import com.ldl.lotteryodds.entity.Entity500;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/7/24 21:28
 */
@Controller
public class IndexController {

    /**
     * dao
     */
    @Autowired
    private LotteryOddsDao lotteryOddsDao;

    /**
     * 首页
     *
     * @return string
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * @param wodd 胜赔率
     * @param dodd 平赔率
     * @param lodd 负赔率
     * @param type 1 不让球  2 让球
     * @return string
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = "text/html;charset=UTF-8")
    public String search(String wodd, String dodd, String lodd, int type) {

        final List<Entity500> entity500s = lotteryOddsDao.getListsWithOdds(wodd, dodd, lodd, type);
        return JSON.toJSONString(entity500s);
    }
}
