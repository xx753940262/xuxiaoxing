package com.zmr.xuxiaoxing.ui.shopcar.model;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/11 15:54
 * 邮箱：2235445233@qq.com
 */
public class AddCarDzck {


    /**
     * code : 200
     * msg : success
     * data : 没有数据
     */

    private String code;
    private String msg;
    private String data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public AddCarDzck(String json) {
        AddCarDzck result = AbJsonUtil.fromJson(json, this.getClass());
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
