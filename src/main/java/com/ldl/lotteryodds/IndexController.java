package com.ldl.lotteryodds;

import com.alibaba.fastjson.JSON;
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

    @Autowired
    private LotteryOddsDao lotteryOddsDao;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/search",produces = "text/html;charset=UTF-8")
    public String search(String wodd,String dodd,String lodd,int type){

        List<Entity500> entity500s = lotteryOddsDao.getListsWithOdds(wodd,dodd,lodd,type);
        return JSON.toJSONString(entity500s);
    }
}
