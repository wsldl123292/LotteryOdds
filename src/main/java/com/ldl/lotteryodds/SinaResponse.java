package com.ldl.lotteryodds;

/**
 * 作者: LDL
 * 说明: 新浪彩票请求返回实体
 * 时间: 2015/7/23 21:19
 */
public class SinaResponse {

    private String result;
    private String status;
    private String msg;
    private String code;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
