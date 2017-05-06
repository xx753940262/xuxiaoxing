package com.zmr.xuxiaoxing.ui.car.model;

import java.io.Serializable;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/12 16:06
 * 邮箱：2235445233@qq.com
 */
public class GoodsInfo2 implements Serializable {
    protected String recId;
    protected String goodsId;
    protected String name;
    protected boolean isChoosed;
    private String imageUrl;
    private String desc;
    private double price;
    private int count;
    private int position;// 绝对位置，只在ListView构造的购物车中，在删除时有效
    private String color;
    private String size;
    private String goodsImg;
    private double discountPrice;
    private String is_shipping;
    private String goods_attr_id;
    private String packagemoney;
    private String shipping_fee;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIs_shipping() {
        return is_shipping;
    }

    public void setIs_shipping(String is_shipping) {
        this.is_shipping = is_shipping;
    }

    public String getGoods_attr_id() {
        return goods_attr_id;
    }

    public void setGoods_attr_id(String goods_attr_id) {
        this.goods_attr_id = goods_attr_id;
    }

    public GoodsInfo2(String recId, String goodsId, String name, String desc, double price, int count, String color,
                      String size, String goodsImg, double discountPrice,String is_shipping,String goods_attr_id,String packagemoney,String shipping_fee) {
        this.recId = recId;
        this.goodsId = goodsId;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.count = count;
        this.color = color;
        this.size = size;
        this.goodsImg = goodsImg;
        this.discountPrice = discountPrice;
        this.is_shipping = is_shipping;
        this.goods_attr_id = goods_attr_id;
        this.packagemoney = packagemoney;
        this.shipping_fee = shipping_fee;

    }


    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean isChoosed) {
        this.isChoosed = isChoosed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPackagemoney() {
        return packagemoney;
    }

    public void setPackagemoney(String packagemoney) {
        this.packagemoney = packagemoney;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }
}