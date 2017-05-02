package com.xiaoxing.common.model;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/7 21:01
 * 邮箱：2235445233@qq.com
 */
public class BaseModel<T> {


    /**
     * code : -1
     * msg : 输入的账号不存在!
     * data :
     */

    private String code;
    private String msg;
    private T data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public BaseModel(String json) {
        BaseModel result = AbJsonUtil.fromJson(json, this.getClass());
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = (T) result.getData();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
