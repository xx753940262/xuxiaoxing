package com.zmr.xuxiaoxing.ui.address.model;
import com.xiaoxing.common.util.AbJsonUtil;
import com.xiaoxing.common.third.gson.annotations.SerializedName;

import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/16 13:22
 * 邮箱：2235445233@qq.com
 */
public class AddressGet {


    /**
     * code : 200
     * msg : success
     * data : [{"id":"114","user_id":"11","accept_name":"徐星","zip":"","telephone":"15965561796","address":"山东省 青岛市 崂山区","mobile":"","default":"1","street":"北宅街道","detail":"苏宁"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public AddressGet(String json) {
        AddressGet result = AbJsonUtil.fromJson(json, this.getClass());
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 114
         * user_id : 11
         * accept_name : 徐星
         * zip :
         * telephone : 15965561796
         * address : 山东省 青岛市 崂山区
         * mobile :
         * default : 1
         * street : 北宅街道
         * detail : 苏宁
         */

        private String id;
        private String user_id;
        private String accept_name;
        private String zip;
        private String telephone;
        private String address;
        private String mobile;
        @SerializedName("default")
        private String defaultX;
        private String street;
        private String detail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAccept_name() {
            return accept_name;
        }

        public void setAccept_name(String accept_name) {
            this.accept_name = accept_name;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(String defaultX) {
            this.defaultX = defaultX;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
