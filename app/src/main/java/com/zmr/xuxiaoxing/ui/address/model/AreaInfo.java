package com.zmr.xuxiaoxing.ui.address.model;
/**
 * 描述：
 * 作者：xiaoxing on 17/4/16 10:59
 * 邮箱：2235445233@qq.com
 */

import java.util.List;


public class AreaInfo {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"flag":true,"data":[{"id":4,"parentId":0,"level":1,"areaName":"山西省"},{"id":15,"parentId":0,"level":1,"areaName":"山东省"},{"id":7,"parentId":0,"level":1,"areaName":"吉林省"},{"id":11,"parentId":0,"level":1,"areaName":"浙江省"},{"id":20,"parentId":0,"level":1,"areaName":"广西省"},{"id":13,"parentId":0,"level":1,"areaName":"福建省"},{"id":2,"parentId":0,"level":1,"areaName":"天津市"},{"id":17,"parentId":0,"level":1,"areaName":"湖北省"},{"id":9,"parentId":0,"level":1,"areaName":"上海市"},{"id":3,"parentId":0,"level":1,"areaName":"河北省"},{"id":18,"parentId":0,"level":1,"areaName":"湖南省"},{"id":19,"parentId":0,"level":1,"areaName":"广东省"},{"id":16,"parentId":0,"level":1,"areaName":"河南省"},{"id":8,"parentId":0,"level":1,"areaName":"黑龙江"},{"id":12,"parentId":0,"level":1,"areaName":"安徽省"},{"id":5,"parentId":0,"level":1,"areaName":"内蒙古"},{"id":14,"parentId":0,"level":1,"areaName":"江西省"},{"id":6,"parentId":0,"level":1,"areaName":"辽宁省"},{"id":10,"parentId":0,"level":1,"areaName":"江苏省"},{"id":21,"parentId":0,"level":1,"areaName":"海南省"},{"id":23,"parentId":0,"level":1,"areaName":"四川省"},{"id":24,"parentId":0,"level":1,"areaName":"贵州省"},{"id":25,"parentId":0,"level":1,"areaName":"云南省"},{"id":26,"parentId":0,"level":1,"areaName":"西藏"},{"id":27,"parentId":0,"level":1,"areaName":"陕西省"},{"id":28,"parentId":0,"level":1,"areaName":"甘肃省"},{"id":29,"parentId":0,"level":1,"areaName":"青海省"},{"id":31,"parentId":0,"level":1,"areaName":"新疆"},{"id":30,"parentId":0,"level":1,"areaName":"宁夏"},{"id":32,"parentId":0,"level":1,"areaName":"台灣"},{"id":34,"parentId":0,"level":1,"areaName":"澳门"},{"id":35,"parentId":0,"level":1,"areaName":"海外"},{"id":36,"parentId":0,"level":1,"areaName":"其他"},{"provinceId":1,"id":1,"parentId":0,"level":1,"areaName":"北京市","areaCode":"010","zipCode":"100000","provinceName":"北京市"},{"provinceId":22,"id":22,"parentId":0,"level":1,"areaName":"重庆市","areaCode":"023","zipCode":"400000","provinceName":"重庆市"},{"provinceId":33,"id":33,"parentId":0,"level":1,"areaName":"香港","areaCode":"852","zipCode":"","provinceName":"香港"}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * ret_code : 0
     * flag : true
     * data : [{"id":4,"parentId":0,"level":1,"areaName":"山西省"},{"id":15,"parentId":0,"level":1,"areaName":"山东省"},{"id":7,"parentId":0,"level":1,"areaName":"吉林省"},{"id":11,"parentId":0,"level":1,"areaName":"浙江省"},{"id":20,"parentId":0,"level":1,"areaName":"广西省"},{"id":13,"parentId":0,"level":1,"areaName":"福建省"},{"id":2,"parentId":0,"level":1,"areaName":"天津市"},{"id":17,"parentId":0,"level":1,"areaName":"湖北省"},{"id":9,"parentId":0,"level":1,"areaName":"上海市"},{"id":3,"parentId":0,"level":1,"areaName":"河北省"},{"id":18,"parentId":0,"level":1,"areaName":"湖南省"},{"id":19,"parentId":0,"level":1,"areaName":"广东省"},{"id":16,"parentId":0,"level":1,"areaName":"河南省"},{"id":8,"parentId":0,"level":1,"areaName":"黑龙江"},{"id":12,"parentId":0,"level":1,"areaName":"安徽省"},{"id":5,"parentId":0,"level":1,"areaName":"内蒙古"},{"id":14,"parentId":0,"level":1,"areaName":"江西省"},{"id":6,"parentId":0,"level":1,"areaName":"辽宁省"},{"id":10,"parentId":0,"level":1,"areaName":"江苏省"},{"id":21,"parentId":0,"level":1,"areaName":"海南省"},{"id":23,"parentId":0,"level":1,"areaName":"四川省"},{"id":24,"parentId":0,"level":1,"areaName":"贵州省"},{"id":25,"parentId":0,"level":1,"areaName":"云南省"},{"id":26,"parentId":0,"level":1,"areaName":"西藏"},{"id":27,"parentId":0,"level":1,"areaName":"陕西省"},{"id":28,"parentId":0,"level":1,"areaName":"甘肃省"},{"id":29,"parentId":0,"level":1,"areaName":"青海省"},{"id":31,"parentId":0,"level":1,"areaName":"新疆"},{"id":30,"parentId":0,"level":1,"areaName":"宁夏"},{"id":32,"parentId":0,"level":1,"areaName":"台灣"},{"id":34,"parentId":0,"level":1,"areaName":"澳门"},{"id":35,"parentId":0,"level":1,"areaName":"海外"},{"id":36,"parentId":0,"level":1,"areaName":"其他"},{"provinceId":1,"id":1,"parentId":0,"level":1,"areaName":"北京市","areaCode":"010","zipCode":"100000","provinceName":"北京市"},{"provinceId":22,"id":22,"parentId":0,"level":1,"areaName":"重庆市","areaCode":"023","zipCode":"400000","provinceName":"重庆市"},{"provinceId":33,"id":33,"parentId":0,"level":1,"areaName":"香港","areaCode":"852","zipCode":"","provinceName":"香港"}]
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int ret_code;
        private boolean flag;
        /**
         * id : 4
         * parentId : 0
         * level : 1
         * areaName : 山西省
         */

        private List<DataBean> data;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private int id;
            private int parentId;
            private int level;
            private String areaName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }
        }
    }
}
