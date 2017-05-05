package com.zmr.xuxiaoxing.ui.address.model;

import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/16 10:59
 * 邮箱：2235445233@qq.com
 */
public class QuInfo {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"flag":true,"data":{"provinceId":15,"id":2647,"parentId":239,"level":3,"areaName":"巨野县","areaCode":"0530","zipCode":"274900","children":[{"provinceId":15,"id":22871,"parentId":2647,"level":4,"areaName":"万丰镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22872,"parentId":2647,"level":4,"areaName":"大义镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22873,"parentId":2647,"level":4,"areaName":"大谢集镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22874,"parentId":2647,"level":4,"areaName":"太平镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22875,"parentId":2647,"level":4,"areaName":"巨野镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22877,"parentId":2647,"level":4,"areaName":"核桃园镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22876,"parentId":2647,"level":4,"areaName":"柳林镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22883,"parentId":2647,"level":4,"areaName":"董官屯镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22884,"parentId":2647,"level":4,"areaName":"陶庙镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22881,"parentId":2647,"level":4,"areaName":"章缝镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22882,"parentId":2647,"level":4,"areaName":"营里镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22880,"parentId":2647,"level":4,"areaName":"田桥镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22879,"parentId":2647,"level":4,"areaName":"田庄镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22878,"parentId":2647,"level":4,"areaName":"独山镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22885,"parentId":2647,"level":4,"areaName":"麒麟镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22886,"parentId":2647,"level":4,"areaName":"龙堌镇","areaCode":"0530","provinceName":"山东省"}],"provinceName":"山东省"}}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * ret_code : 0
     * flag : true
     * data : {"provinceId":15,"id":2647,"parentId":239,"level":3,"areaName":"巨野县","areaCode":"0530","zipCode":"274900","children":[{"provinceId":15,"id":22871,"parentId":2647,"level":4,"areaName":"万丰镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22872,"parentId":2647,"level":4,"areaName":"大义镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22873,"parentId":2647,"level":4,"areaName":"大谢集镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22874,"parentId":2647,"level":4,"areaName":"太平镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22875,"parentId":2647,"level":4,"areaName":"巨野镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22877,"parentId":2647,"level":4,"areaName":"核桃园镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22876,"parentId":2647,"level":4,"areaName":"柳林镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22883,"parentId":2647,"level":4,"areaName":"董官屯镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22884,"parentId":2647,"level":4,"areaName":"陶庙镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22881,"parentId":2647,"level":4,"areaName":"章缝镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22882,"parentId":2647,"level":4,"areaName":"营里镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22880,"parentId":2647,"level":4,"areaName":"田桥镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22879,"parentId":2647,"level":4,"areaName":"田庄镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22878,"parentId":2647,"level":4,"areaName":"独山镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22885,"parentId":2647,"level":4,"areaName":"麒麟镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22886,"parentId":2647,"level":4,"areaName":"龙堌镇","areaCode":"0530","provinceName":"山东省"}],"provinceName":"山东省"}
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
         * provinceId : 15
         * id : 2647
         * parentId : 239
         * level : 3
         * areaName : 巨野县
         * areaCode : 0530
         * zipCode : 274900
         * children : [{"provinceId":15,"id":22871,"parentId":2647,"level":4,"areaName":"万丰镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22872,"parentId":2647,"level":4,"areaName":"大义镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22873,"parentId":2647,"level":4,"areaName":"大谢集镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22874,"parentId":2647,"level":4,"areaName":"太平镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22875,"parentId":2647,"level":4,"areaName":"巨野镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22877,"parentId":2647,"level":4,"areaName":"核桃园镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22876,"parentId":2647,"level":4,"areaName":"柳林镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22883,"parentId":2647,"level":4,"areaName":"董官屯镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22884,"parentId":2647,"level":4,"areaName":"陶庙镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22881,"parentId":2647,"level":4,"areaName":"章缝镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22882,"parentId":2647,"level":4,"areaName":"营里镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22880,"parentId":2647,"level":4,"areaName":"田桥镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22879,"parentId":2647,"level":4,"areaName":"田庄镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22878,"parentId":2647,"level":4,"areaName":"独山镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22885,"parentId":2647,"level":4,"areaName":"麒麟镇","areaCode":"0530","provinceName":"山东省"},{"provinceId":15,"id":22886,"parentId":2647,"level":4,"areaName":"龙堌镇","areaCode":"0530","provinceName":"山东省"}]
         * provinceName : 山东省
         */

        private DataBean data;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            private int provinceId;
            private int id;
            private int parentId;
            private int level;
            private String areaName;
            private String areaCode;
            private String zipCode;
            private String provinceName;
            /**
             * provinceId : 15
             * id : 22871
             * parentId : 2647
             * level : 4
             * areaName : 万丰镇
             * areaCode : 0530
             * provinceName : 山东省
             */

            private List<ChildrenBean> children;

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

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

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                private int provinceId;
                private int id;
                private int parentId;
                private int level;
                private String areaName;
                private String areaCode;
                private String provinceName;

                public int getProvinceId() {
                    return provinceId;
                }

                public void setProvinceId(int provinceId) {
                    this.provinceId = provinceId;
                }

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

                public String getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(String areaCode) {
                    this.areaCode = areaCode;
                }

                public String getProvinceName() {
                    return provinceName;
                }

                public void setProvinceName(String provinceName) {
                    this.provinceName = provinceName;
                }
            }
        }
    }
}
