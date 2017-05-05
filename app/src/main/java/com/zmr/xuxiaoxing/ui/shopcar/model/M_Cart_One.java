package com.zmr.xuxiaoxing.ui.shopcar.model;

import com.xiaoxing.common.util.AbJsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/12 16:06
 * 邮箱：2235445233@qq.com
 */
public class M_Cart_One implements Serializable {


    /**
     * error : 0
     * content : [{"supplier_id":"70","supplier_name":"皮尔卡丹专卖店","cart_goods":[{"rec_id":"2086","user_id":"119","session_id":"3c77a9ae88a370b368a9f1ef5c501f07","goods_id":"436","goods_sn":"P13128","product_id":"0","goods_name":"皮尔卡丹男士白色吊带背心男秋冬纯棉圆领无袖汗衫纯色内衣老头衫","market_price":"53.90","goods_price":"49.00","split_money":"0.00","goods_number":"1","goods_attr":"","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"1","can_handsel":"0","goods_attr_id":"0","add_time":"1481702903","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","goods_thumb":"images/201612/thumb_img/436_thumb_G_1481683785782.jpg","supplier_id":"70"}],"shipping_list":[{"shipping_id":"34","shipping_code":"bestex","shipping_name":"百世汇通","shipping_desc":"由商家选择合作快递为您配送：","enabled":"1","supplier_id":"70","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:2:\"15\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"5\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:3:\"100\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","shipping_area_name":"peisong1","shipping_area_id":"555","free_money":"100.00","item_fee":"15.00","area_list":[{"shipping_area_id":"555","region_id":"532","parent_id":"54","region_name":"漳平市","region_type":"3","agency_id":"0"},{"shipping_area_id":"555","region_id":"2347","parent_id":"284","region_name":"黄岛区","region_type":"3","agency_id":"0"}]},{"shipping_id":"34","shipping_code":"bestex","shipping_name":"百世汇通","shipping_desc":"由商家选择合作快递为您配送：","enabled":"1","supplier_id":"70","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:2:\"15\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"5\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:2:\"88\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","shipping_area_name":"peisong2","shipping_area_id":"556","free_money":"88.00","item_fee":"15.00","area_list":[{"shipping_area_id":"556","region_id":"693","parent_id":"76","region_name":"天河区","region_type":"3","agency_id":"0"},{"shipping_area_id":"556","region_id":"865","parent_id":"98","region_name":"秀峰区","region_type":"3","agency_id":"0"}]}]}]
     */

    private int error;
    private List<ContentBean> content;

    /**
     * 用json构造自己
     *
     * @param json
     */
    public M_Cart_One(String json) {
        super();
        M_Cart_One result = AbJsonUtil.fromJson(json, this.getClass());
        this.error = result.getError();
        this.content = result.getContent();
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }


    public static class ContentBean implements Serializable {
        /**
         * supplier_id : 70
         * supplier_name : 皮尔卡丹专卖店
         * cart_goods : [{"rec_id":"2086","user_id":"119","session_id":"3c77a9ae88a370b368a9f1ef5c501f07","goods_id":"436","goods_sn":"P13128","product_id":"0","goods_name":"皮尔卡丹男士白色吊带背心男秋冬纯棉圆领无袖汗衫纯色内衣老头衫","market_price":"53.90","goods_price":"49.00","split_money":"0.00","goods_number":"1","goods_attr":"","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"1","can_handsel":"0","goods_attr_id":"0","add_time":"1481702903","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","goods_thumb":"images/201612/thumb_img/436_thumb_G_1481683785782.jpg","supplier_id":"70"}]
         * shipping_list : [{"shipping_id":"34","shipping_code":"bestex","shipping_name":"百世汇通","shipping_desc":"由商家选择合作快递为您配送：","enabled":"1","supplier_id":"70","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:2:\"15\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"5\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:3:\"100\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","shipping_area_name":"peisong1","shipping_area_id":"555","free_money":"100.00","item_fee":"15.00","area_list":[{"shipping_area_id":"555","region_id":"532","parent_id":"54","region_name":"漳平市","region_type":"3","agency_id":"0"},{"shipping_area_id":"555","region_id":"2347","parent_id":"284","region_name":"黄岛区","region_type":"3","agency_id":"0"}]},{"shipping_id":"34","shipping_code":"bestex","shipping_name":"百世汇通","shipping_desc":"由商家选择合作快递为您配送：","enabled":"1","supplier_id":"70","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:2:\"15\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"5\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:2:\"88\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","shipping_area_name":"peisong2","shipping_area_id":"556","free_money":"88.00","item_fee":"15.00","area_list":[{"shipping_area_id":"556","region_id":"693","parent_id":"76","region_name":"天河区","region_type":"3","agency_id":"0"},{"shipping_area_id":"556","region_id":"865","parent_id":"98","region_name":"秀峰区","region_type":"3","agency_id":"0"}]}]
         */

        private String supplier_id;
        private String supplier_name;
        private List<CartGoodsBean> cart_goods;
        private List<ShippingListBean> shipping_list;

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public List<CartGoodsBean> getCart_goods() {
            return cart_goods;
        }

        public void setCart_goods(List<CartGoodsBean> cart_goods) {
            this.cart_goods = cart_goods;
        }

        public List<ShippingListBean> getShipping_list() {
            return shipping_list;
        }

        public void setShipping_list(List<ShippingListBean> shipping_list) {
            this.shipping_list = shipping_list;
        }

        public static class CartGoodsBean  implements Serializable{
            /**
             * rec_id : 2086
             * user_id : 119
             * session_id : 3c77a9ae88a370b368a9f1ef5c501f07
             * goods_id : 436
             * goods_sn : P13128
             * product_id : 0
             * goods_name : 皮尔卡丹男士白色吊带背心男秋冬纯棉圆领无袖汗衫纯色内衣老头衫
             * market_price : 53.90
             * goods_price : 49.00
             * split_money : 0.00
             * goods_number : 1
             * goods_attr :
             * is_real : 1
             * extension_code :
             * parent_id : 0
             * rec_type : 0
             * is_gift : 0
             * is_shipping : 1
             * can_handsel : 0
             * goods_attr_id : 0
             * add_time : 1481702903
             * package_attr_id :
             * cost_price : 0.00
             * promote_price : 0.00
             * exclusive : -1
             * goods_thumb : images/201612/thumb_img/436_thumb_G_1481683785782.jpg
             * supplier_id : 70
             */

            private String rec_id;
            private String user_id;
            private String session_id;
            private String goods_id;
            private String goods_sn;
            private String product_id;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String split_money;
            private String goods_number;
            private String goods_attr;
            private String is_real;
            private String extension_code;
            private String parent_id;
            private String rec_type;
            private String is_gift;
            private String is_shipping;
            private String can_handsel;
            private String goods_attr_id;
            private String add_time;
            private String package_attr_id;
            private String cost_price;
            private String promote_price;
            private String exclusive;
            private String goods_thumb;
            private String supplier_id;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getSplit_money() {
                return split_money;
            }

            public void setSplit_money(String split_money) {
                this.split_money = split_money;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(String goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getRec_type() {
                return rec_type;
            }

            public void setRec_type(String rec_type) {
                this.rec_type = rec_type;
            }

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getIs_shipping() {
                return is_shipping;
            }

            public void setIs_shipping(String is_shipping) {
                this.is_shipping = is_shipping;
            }

            public String getCan_handsel() {
                return can_handsel;
            }

            public void setCan_handsel(String can_handsel) {
                this.can_handsel = can_handsel;
            }

            public String getGoods_attr_id() {
                return goods_attr_id;
            }

            public void setGoods_attr_id(String goods_attr_id) {
                this.goods_attr_id = goods_attr_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPackage_attr_id() {
                return package_attr_id;
            }

            public void setPackage_attr_id(String package_attr_id) {
                this.package_attr_id = package_attr_id;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getPromote_price() {
                return promote_price;
            }

            public void setPromote_price(String promote_price) {
                this.promote_price = promote_price;
            }

            public String getExclusive() {
                return exclusive;
            }

            public void setExclusive(String exclusive) {
                this.exclusive = exclusive;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }
        }

        public static class ShippingListBean implements Serializable {
            /**
             * shipping_id : 34
             * shipping_code : bestex
             * shipping_name : 百世汇通
             * shipping_desc : 由商家选择合作快递为您配送：
             * enabled : 1
             * supplier_id : 70
             * configure : a:5:{i:0;a:2:{s:4:"name";s:8:"item_fee";s:5:"value";s:2:"15";}i:1;a:2:{s:4:"name";s:8:"base_fee";s:5:"value";s:2:"15";}i:2;a:2:{s:4:"name";s:8:"step_fee";s:5:"value";s:1:"5";}i:3;a:2:{s:4:"name";s:10:"free_money";s:5:"value";s:3:"100";}i:4;a:2:{s:4:"name";s:16:"fee_compute_mode";s:5:"value";s:9:"by_weight";}}
             * shipping_area_name : peisong1
             * shipping_area_id : 555
             * free_money : 100.00
             * item_fee : 15.00
             * area_list : [{"shipping_area_id":"555","region_id":"532","parent_id":"54","region_name":"漳平市","region_type":"3","agency_id":"0"},{"shipping_area_id":"555","region_id":"2347","parent_id":"284","region_name":"黄岛区","region_type":"3","agency_id":"0"}]
             */

            private String shipping_id;
            private String shipping_code;
            private String shipping_name;
            private String shipping_desc;
            private String enabled;
            private String supplier_id;
            private String configure;
            private String shipping_area_name;
            private String shipping_area_id;
            private String free_money;
            private String item_fee;
            private String base_fee;
            private List<AreaListBean> area_list;

            public String getBase_fee() {
                return base_fee;
            }

            public void setBase_fee(String base_fee) {
                this.base_fee = base_fee;
            }

            public String getShipping_id() {
                return shipping_id;
            }

            public void setShipping_id(String shipping_id) {
                this.shipping_id = shipping_id;
            }

            public String getShipping_code() {
                return shipping_code;
            }

            public void setShipping_code(String shipping_code) {
                this.shipping_code = shipping_code;
            }

            public String getShipping_name() {
                return shipping_name;
            }

            public void setShipping_name(String shipping_name) {
                this.shipping_name = shipping_name;
            }

            public String getShipping_desc() {
                return shipping_desc;
            }

            public void setShipping_desc(String shipping_desc) {
                this.shipping_desc = shipping_desc;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getConfigure() {
                return configure;
            }

            public void setConfigure(String configure) {
                this.configure = configure;
            }

            public String getShipping_area_name() {
                return shipping_area_name;
            }

            public void setShipping_area_name(String shipping_area_name) {
                this.shipping_area_name = shipping_area_name;
            }

            public String getShipping_area_id() {
                return shipping_area_id;
            }

            public void setShipping_area_id(String shipping_area_id) {
                this.shipping_area_id = shipping_area_id;
            }

            public String getFree_money() {
                return free_money;
            }

            public void setFree_money(String free_money) {
                this.free_money = free_money;
            }

            public String getItem_fee() {
                return item_fee;
            }

            public void setItem_fee(String item_fee) {
                this.item_fee = item_fee;
            }

            public List<AreaListBean> getArea_list() {
                return area_list;
            }

            public void setArea_list(List<AreaListBean> area_list) {
                this.area_list = area_list;
            }

            public static class AreaListBean  implements Serializable{
                /**
                 * shipping_area_id : 555
                 * region_id : 532
                 * parent_id : 54
                 * region_name : 漳平市
                 * region_type : 3
                 * agency_id : 0
                 */

                private String shipping_area_id;
                private String region_id;
                private String parent_id;
                private String region_name;
                private String region_type;
                private String agency_id;

                public String getShipping_area_id() {
                    return shipping_area_id;
                }

                public void setShipping_area_id(String shipping_area_id) {
                    this.shipping_area_id = shipping_area_id;
                }

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }

                public String getRegion_type() {
                    return region_type;
                }

                public void setRegion_type(String region_type) {
                    this.region_type = region_type;
                }

                public String getAgency_id() {
                    return agency_id;
                }

                public void setAgency_id(String agency_id) {
                    this.agency_id = agency_id;
                }
            }
        }
    }
}
