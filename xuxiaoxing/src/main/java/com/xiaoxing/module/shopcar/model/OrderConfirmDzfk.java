package com.xiaoxing.module.shopcar.model;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/17 06:19
 * 邮箱：2235445233@qq.com
 */
public class OrderConfirmDzfk {


    /**
     * code : 200
     * msg : success
     * data : {"state":1}
     */

    private String code;
    private String msg;
    private DataBean data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public OrderConfirmDzfk(String json) {
        OrderConfirmDzfk result = AbJsonUtil.fromJson(json, this.getClass());
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
        /**
         * state : 1
         */

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
