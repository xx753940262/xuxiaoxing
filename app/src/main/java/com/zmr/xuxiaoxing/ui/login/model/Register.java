package com.zmr.xuxiaoxing.ui.login.model;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：注册
 * 作者：xiaoxing on 17/4/9 10:51
 * 邮箱：2235445233@qq.com
 */
public class Register {

    /**
     * code : -1
     * msg : 手机号码格式错误
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
    public Register(String json) {
        Register result = AbJsonUtil.fromJson(json, this.getClass());
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
