package com.xiaoxing.module.login.model;

import com.xiaoxing.common.gson.annotations.SerializedName;
import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：登录
 * 作者：xiaoxing on 17/4/7 20:52
 * 邮箱：2235445233@qq.com
 */
public class Login {


    /**
     * code : 200
     * msg : success
     * data : {"uid":"5","user_name":"15965561796","mobile":"15965561796","password":"961b9740b914ae693ca193dfa01e2dc6","salt":"yusx4o","lng":null,"lat":null,"reg_time":"1491622524","type":null,"cover":null,"address":null,"nick_name":null,"name":null,"is_admin":"0","is_superadmin":"0","sex":"0","member_money":"0","school_no":"0","birthday":null,"points":"0","school":null,"section":null,"class":null,"access_token":"ca4eqjYNLXAk0TaT/58M+oaLoXCYqnc1g1VFYC8b"}
     */

    private String code;
    private String msg;
    private DataBean data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public Login(String json) {
        Login result = AbJsonUtil.fromJson(json, this.getClass());
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
         * uid : 5
         * user_name : 15965561796
         * mobile : 15965561796
         * password : 961b9740b914ae693ca193dfa01e2dc6
         * salt : yusx4o
         * lng : null
         * lat : null
         * reg_time : 1491622524
         * type : null
         * cover : null
         * address : null
         * nick_name : null
         * name : null
         * is_admin : 0
         * is_superadmin : 0
         * sex : 0
         * member_money : 0
         * school_no : 0
         * birthday : null
         * points : 0
         * school : null
         * section : null
         * class : null
         * access_token : ca4eqjYNLXAk0TaT/58M+oaLoXCYqnc1g1VFYC8b
         */

        private String uid;
        private String user_name;
        private String mobile;
        private String password;
        private String salt;
        private String lng;
        private String lat;
        private String reg_time;
        private String type;
        private String cover;
        private String address;
        private String nick_name;
        private String name;
        private String is_admin;
        private String is_superadmin;
        private String sex;
        private String member_money;
        private String school_no;
        private String birthday;
        private String points;
        private String school;
        private String section;
        @SerializedName("class")
        private String classX;
        private String access_token;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }

        public String getIs_superadmin() {
            return is_superadmin;
        }

        public void setIs_superadmin(String is_superadmin) {
            this.is_superadmin = is_superadmin;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMember_money() {
            return member_money;
        }

        public void setMember_money(String member_money) {
            this.member_money = member_money;
        }

        public String getSchool_no() {
            return school_no;
        }

        public void setSchool_no(String school_no) {
            this.school_no = school_no;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }

}
