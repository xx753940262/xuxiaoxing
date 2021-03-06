package com.zmr.xuxiaoxing.ui.login.model;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：发送短信
 * 作者：xiaoxing on 17/4/9 10:47
 * 邮箱：2235445233@qq.com
 */
public class SendSMS {


    /**
     * code : 200
     * msg : success
     * data : {}
     */

    private String code;
    private String msg;
    private DataBean data;


    /**
     * 用json构造自己
     *
     * @param json
     */
    public SendSMS(String json) {
        SendSMS result = AbJsonUtil.fromJson(json, this.getClass());
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = result.getData();

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
