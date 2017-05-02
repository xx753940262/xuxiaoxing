package com.xiaoxing.module.shopcar.model;

import com.xiaoxing.common.util.AbJsonUtil;

import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/25 22:47
 * 邮箱：2235445233@qq.com
 */
public class ShopCarJiuTangGuoJi {


    /**
     * code : 200
     * msg : success
     * data : {"goods_data":[{"type_id":"29","type_name":"小菜類","products":[{"cart_id":"389","user_id":"53","product_id":"149","product_name":"去骨豬腳","product_image":"http://zidan.bvhao.cc/Uploads/2017-01-19/5880771a280d5.jpg","price":"270.00","sale_price":"0.00","points":"0","discount":"0.00","goods_num":"3","is_del":"0","register_date":"1493272701","update_date":"0","type_id":"29","type_name":"小菜類","uid":null,"type_logo":"/Uploads/2017-01-23/5884db832a770.png","t_id":"0","community_id":null}]}],"goods_num":"1"}
     */

    private String code;
    private String msg;
    private DataBean data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public ShopCarJiuTangGuoJi(String json) {
        ShopCarJiuTangGuoJi result = AbJsonUtil.fromJson(json, this.getClass());
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
         * goods_data : [{"type_id":"29","type_name":"小菜類","products":[{"cart_id":"389","user_id":"53","product_id":"149","product_name":"去骨豬腳","product_image":"http://zidan.bvhao.cc/Uploads/2017-01-19/5880771a280d5.jpg","price":"270.00","sale_price":"0.00","points":"0","discount":"0.00","goods_num":"3","is_del":"0","register_date":"1493272701","update_date":"0","type_id":"29","type_name":"小菜類","uid":null,"type_logo":"/Uploads/2017-01-23/5884db832a770.png","t_id":"0","community_id":null}]}]
         * goods_num : 1
         */

        private String goods_num;
        private List<GoodsDataBean> goods_data;

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public List<GoodsDataBean> getGoods_data() {
            return goods_data;
        }

        public void setGoods_data(List<GoodsDataBean> goods_data) {
            this.goods_data = goods_data;
        }

        public static class GoodsDataBean {
            /**
             * type_id : 29
             * type_name : 小菜類
             * products : [{"cart_id":"389","user_id":"53","product_id":"149","product_name":"去骨豬腳","product_image":"http://zidan.bvhao.cc/Uploads/2017-01-19/5880771a280d5.jpg","price":"270.00","sale_price":"0.00","points":"0","discount":"0.00","goods_num":"3","is_del":"0","register_date":"1493272701","update_date":"0","type_id":"29","type_name":"小菜類","uid":null,"type_logo":"/Uploads/2017-01-23/5884db832a770.png","t_id":"0","community_id":null}]
             */

            private String type_id;
            private String type_name;
            private List<ProductsBean> products;

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
                /**
                 * cart_id : 389
                 * user_id : 53
                 * product_id : 149
                 * product_name : 去骨豬腳
                 * product_image : http://zidan.bvhao.cc/Uploads/2017-01-19/5880771a280d5.jpg
                 * price : 270.00
                 * sale_price : 0.00
                 * points : 0
                 * discount : 0.00
                 * goods_num : 3
                 * is_del : 0
                 * register_date : 1493272701
                 * update_date : 0
                 * type_id : 29
                 * type_name : 小菜類
                 * uid : null
                 * type_logo : /Uploads/2017-01-23/5884db832a770.png
                 * t_id : 0
                 * community_id : null
                 */

                private String cart_id;
                private String user_id;
                private String product_id;
                private String product_name;
                private String product_image;
                private String price;
                private String sale_price;
                private String points;
                private String discount;
                private String goods_num;
                private String is_del;
                private String register_date;
                private String update_date;
                private String type_id;
                private String type_name;
                private Object uid;
                private String type_logo;
                private String t_id;
                private Object community_id;

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public String getProduct_image() {
                    return product_image;
                }

                public void setProduct_image(String product_image) {
                    this.product_image = product_image;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSale_price() {
                    return sale_price;
                }

                public void setSale_price(String sale_price) {
                    this.sale_price = sale_price;
                }

                public String getPoints() {
                    return points;
                }

                public void setPoints(String points) {
                    this.points = points;
                }

                public String getDiscount() {
                    return discount;
                }

                public void setDiscount(String discount) {
                    this.discount = discount;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getIs_del() {
                    return is_del;
                }

                public void setIs_del(String is_del) {
                    this.is_del = is_del;
                }

                public String getRegister_date() {
                    return register_date;
                }

                public void setRegister_date(String register_date) {
                    this.register_date = register_date;
                }

                public String getUpdate_date() {
                    return update_date;
                }

                public void setUpdate_date(String update_date) {
                    this.update_date = update_date;
                }

                public String getType_id() {
                    return type_id;
                }

                public void setType_id(String type_id) {
                    this.type_id = type_id;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }

                public Object getUid() {
                    return uid;
                }

                public void setUid(Object uid) {
                    this.uid = uid;
                }

                public String getType_logo() {
                    return type_logo;
                }

                public void setType_logo(String type_logo) {
                    this.type_logo = type_logo;
                }

                public String getT_id() {
                    return t_id;
                }

                public void setT_id(String t_id) {
                    this.t_id = t_id;
                }

                public Object getCommunity_id() {
                    return community_id;
                }

                public void setCommunity_id(Object community_id) {
                    this.community_id = community_id;
                }
            }
        }
    }
}
