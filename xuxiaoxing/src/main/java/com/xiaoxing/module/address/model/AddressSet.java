package com.xiaoxing.module.address.model;
import com.xiaoxing.common.util.AbJsonUtil;
import com.xiaoxing.common.gson.annotations.SerializedName;

/**
 * 描述：登录
 * 作者：xiaoxing on 17/4/7 20:52
 * 邮箱：2235445233@qq.com
 */
public class AddressSet {


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
    public AddressSet(String json) {
        AddressSet result = AbJsonUtil.fromJson(json, this.getClass());
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
        private Object lng;
        private Object lat;
        private String reg_time;
        private Object type;
        private Object cover;
        private Object address;
        private Object nick_name;
        private Object name;
        private String is_admin;
        private String is_superadmin;
        private String sex;
        private String member_money;
        private String school_no;
        private Object birthday;
        private String points;
        private Object school;
        private Object section;
        @SerializedName("class")
        private Object classX;
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

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getCover() {
            return cover;
        }

        public void setCover(Object cover) {
            this.cover = cover;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        public Object getSection() {
            return section;
        }

        public void setSection(Object section) {
            this.section = section;
        }

        public Object getClassX() {
            return classX;
        }

        public void setClassX(Object classX) {
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
