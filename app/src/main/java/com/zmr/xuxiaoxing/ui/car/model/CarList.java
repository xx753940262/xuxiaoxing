package com.zmr.xuxiaoxing.ui.car.model;

import com.xiaoxing.common.util.AbJsonUtil;
import com.xiaoxing.common.third.gson.annotations.SerializedName;

import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/12 15:44
 * 邮箱：2235445233@qq.com
 */
public class CarList {


    /**
     * code : 200
     * msg : success
     * data : {"goods_data":[{"shop_id":"300","shop_name":"山西名吃","products":[{"cart_id":"283","user_id":"11","product_id":"2184","product_name":"韭菜鸡蛋水饺","product_image":"","price":"11.00","sale_price":"11.00","points":"0","discount":"0.00","goods_num":"1","is_del":"0","register_date":"1492238974","update_date":"0","package":"0","shipping_fee":"0"}]}],"goods_num":"1"}
     */

    private String code;
    private String msg;
    private DataBean data;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public CarList(String json) {
        CarList result = AbJsonUtil.fromJson(json, this.getClass());
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
         * goods_data : [{"shop_id":"300","shop_name":"山西名吃","products":[{"cart_id":"283","user_id":"11","product_id":"2184","product_name":"韭菜鸡蛋水饺","product_image":"","price":"11.00","sale_price":"11.00","points":"0","discount":"0.00","goods_num":"1","is_del":"0","register_date":"1492238974","update_date":"0","package":"0","shipping_fee":"0"}]}]
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
             * shop_id : 300
             * shop_name : 山西名吃
             * products : [{"cart_id":"283","user_id":"11","product_id":"2184","product_name":"韭菜鸡蛋水饺","product_image":"","price":"11.00","sale_price":"11.00","points":"0","discount":"0.00","goods_num":"1","is_del":"0","register_date":"1492238974","update_date":"0","package":"0","shipping_fee":"0"}]
             */

            private String shop_id;
            private String shop_name;
            private List<ProductsBean> products;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
                /**
                 * cart_id : 283
                 * user_id : 11
                 * product_id : 2184
                 * product_name : 韭菜鸡蛋水饺
                 * product_image :
                 * price : 11.00
                 * sale_price : 11.00
                 * points : 0
                 * discount : 0.00
                 * goods_num : 1
                 * is_del : 0
                 * register_date : 1492238974
                 * update_date : 0
                 * package : 0
                 * shipping_fee : 0
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
                @SerializedName("package")
                private String packageX;
                private String shipping_fee;

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

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }

                public String getShipping_fee() {
                    return shipping_fee;
                }

                public void setShipping_fee(String shipping_fee) {
                    this.shipping_fee = shipping_fee;
                }
            }
        }
    }
}
