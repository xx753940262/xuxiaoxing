package com.xiaoxing.common.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.xiaoxing.common.http.AbRequestParams;
import com.xiaoxing.common.http.HttpUtil;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/24 23:17
 * 邮箱：2235445233@qq.com
 */
public class BaseApi {


    /**
     * 登陆
     *
     * @param context 上下文
     * @param tel     手机号
     * @param pwd     密码
     */
    public static void login(Context context, String tel, String pwd) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        params.put("password", pwd);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.LOGIN);
    }
    /**
     * 发送短信验证码
     *
     * @param context 上下文
     * @param tel     电话号
     */
    public static void sendSms(Context context, String tel) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.SEND_SMS);
    }

    /**
     * 找回密码发送短信
     *
     * @param context
     * @param tel
     */
    public static void sendCheckCode(Context context, String tel) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.SENE_CHECK_CODE);
    }

    /**
     * 验证短信
     *
     * @param context 上下文
     * @param tel     手机号
     * @param code    验证码
     */
    public static void verityCode(Context context, String tel, String code) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        params.put("code", code);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.VERITY_CODE);
    }

    /**
     * 注册
     *
     * @param context 上下文
     * @param tel     手机号
     * @param pwd     密码
     * @param code    验证码
     */
    public static void register(Context context, String tel, String pwd, String code) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        params.put("password", pwd);
        params.put("type", "2");
        params.put("code", code);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.REGISTER);
    }

    /**
     * 修改密码
     *
     * @param context
     * @param tel
     * @param pwd
     * @param code
     */
    public static void updatePassword(Context context, String tel, String pwd, String code) {

        AbRequestParams params = new AbRequestParams();
        params.put("user_name", tel);
        params.put("password", pwd);
        params.put("code", code);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.UPDATE_PASSWORD);
    }

    /**
     * 收货地址
     *
     * @param context
     * @param access_token
     * @param uid
     */
    public static void addressGet(Context context, String access_token, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_GET);
    }

    /**
     * 添加地址
     *
     * @param context            上下文
     * @param access_token       token
     * @param uid                用户id
     * @param accept_name        收货人姓名
     * @param zip                邮编
     * @param telephone          手机号码
     * @param address            省市区地址
     * @param street             街道名称
     * @param detail             详细地址
     * @param is_default_address 是否默认地址 1默认 0不是
     */
    public static void addressSet(Context context, String access_token, String uid, String accept_name, String zip, String telephone, String address, String street, String detail, String is_default_address) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("accept_name", accept_name);
        params.put("zip", zip);
        params.put("telephone", telephone);
        params.put("address", address);
        params.put("street", street);
        params.put("detail", detail);
        params.put("is_default_address", is_default_address);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_SET);
    }

    /**
     * 修改地址
     *
     * @param context            上下文
     * @param access_token       token
     * @param uid                用户id
     * @param accept_name        收货人姓名
     * @param zip                邮编
     * @param telephone          手机号码
     * @param address            省市区地址
     * @param street             街道名称
     * @param detail             详细地址
     * @param is_default_address 是否默认地址 1默认 0不是
     */
    public static void addressUpdate(Context context, String access_token, String uid, String accept_name, String zip, String telephone, String address, String street, String detail, String is_default_address, String id) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("accept_name", accept_name);
        params.put("zip", zip);
        params.put("telephone", telephone);
        params.put("address", address);
        params.put("street", street);
        params.put("detail", detail);
        params.put("is_default_address", is_default_address);
        params.put("address_id", id);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_UPDATE);
    }

    /**
     * 删除地址
     *
     * @param context      上下文
     * @param access_token token
     * @param address_id   地址id
     */
    public static void addressDel(Context context, String access_token, String address_id) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("address_id", address_id);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_DEL);
    }

    /**
     * 获取默认的
     *
     * @param context      上下文
     * @param access_token token
     * @param uid          用户id
     */
    public static void addressGetDefault(Context context, String access_token, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_GETDEFAULT);
    }

    /**
     * 修改默认地址
     *
     * @param context      上下文
     * @param access_token token
     * @param uid          用户id
     * @param address_id   地址id
     */
    public static void addressSetDefault(Context context, String access_token, String uid, String address_id) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("address_id", address_id);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ADDRESS_SETDEFAULT);
    }

    /**
     * 添加到购物车
     *
     * @param context      上下文
     * @param access_token token
     * @param uid          用户id
     * @param goods_id     商品id
     * @param goods_num    商品数量
     */
    public static void carAdd(Context context, String access_token, String uid, String goods_id, String goods_num) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("goods_id", goods_id);
        params.put("goods_num", goods_num);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.CART_ADD);
    }

    /**
     * 购物车列表
     *
     * @param context      上下文
     * @param access_token token
     * @param uid          用户id
     */
    public static void carList(Fragment context, String access_token, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        HttpUtil.httpClientFragmentGetSend(context, params, BaseConstant.CART_LIST);
    }

    /**
     * 购物车列表
     *
     * @param context      上下文
     * @param access_token token
     * @param uid          用户id
     */
    public static void carList(Context context, String access_token, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.CART_LIST);
    }

    /**
     * 购物车商品数量修改
     *
     * @param context      上下文
     * @param access_token token
     * @param cart_id      购物车id
     * @param goods_num    数量
     */
    public static void carupdateNum(Fragment context, String access_token, String cart_id, String goods_num) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("cart_id", cart_id);
        params.put("goods_num", goods_num);
        HttpUtil.httpClientFragmentGetSend(context, params, BaseConstant.CART_UPDATENUM);
    }

    /**
     * 删除购物车
     *
     * @param context      上下文
     * @param access_token token
     * @param cart_id      购物车id
     * @param uid          用户id
     */
    public static void carDel(Fragment context, String access_token, String cart_id, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("cart_id", cart_id);
        params.put("uid", uid);
        HttpUtil.httpClientFragmentGetSend(context, params, BaseConstant.CART_DEL);
    }

    /**
     * 删除购物车
     *
     * @param context      上下文
     * @param access_token token
     * @param cart_id      购物车id
     * @param uid          用户id
     */
    public static void carDel(Context context, String access_token, String cart_id, String uid) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("cart_id", cart_id);
        params.put("uid", uid);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.CART_DEL);
    }

    /**
     * 商品收藏
     *
     * @param context      上下文
     * @param product_id   商品id
     * @param uid          用户id
     * @param access_token token
     */
    public static void productCollect(Context context, String product_id, String uid, String access_token) {

        AbRequestParams params = new AbRequestParams();
        params.put("product_id", product_id);
        params.put("uid", uid);
        params.put("access_token", access_token);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.PRODUCT_COLLECT);
    }

    /**
     * 购物车商品收藏
     *
     * @param context
     * @param product_id
     * @param uid
     * @param access_token
     */

    public static void productCollectCar(Fragment context, String product_id, String uid, String access_token) {

        AbRequestParams params = new AbRequestParams();
        params.put("product_id", product_id);
        params.put("uid", uid);
        params.put("access_token", access_token);
        HttpUtil.httpClientFragmentGetSend(context, params, BaseConstant.PRODUCT_COLLECT);
    }

    /**
     * 购物车商品收藏
     *
     * @param context
     * @param product_id
     * @param uid
     * @param access_token
     */
    public static void productCollectCar(Context context, String product_id, String uid, String access_token) {

        AbRequestParams params = new AbRequestParams();
        params.put("product_id", product_id);
        params.put("uid", uid);
        params.put("access_token", access_token);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.PRODUCT_COLLECT);
    }

    /**
     * 立即购买
     *
     * @param context
     * @param access_token
     * @param uid
     * @param goods_id
     * @param address_id
     * @param payment
     * @param delivery_time
     * @param shipping_type
     */
    public static void orderConfirmOrderNow(Context context, String access_token, String uid, String goods_id, String address_id, String payment, String delivery_time, String shipping_type) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("goods_id", goods_id);
        params.put("address_id", address_id);
        params.put("payment", payment);
        params.put("delivery_time", delivery_time);
        params.put("shipping_type", shipping_type);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ORDER_CONFIRM_ORDERNOW);
    }

    /**
     * 提交订单
     *
     * @param context
     * @param access_token
     * @param uid
     * @param cart_id
     * @param address_id
     * @param payment
     * @param delivery_time
     * @param shipping_type
     */
    public static void orderConfirmOrder(Context context, String access_token, String uid, String cart_id, String address_id, String payment, String delivery_time, String shipping_type,String message) {

        AbRequestParams params = new AbRequestParams();
        params.put("access_token", access_token);
        params.put("uid", uid);
        params.put("cart_id", cart_id);
//        params.put("address_id", address_id);
//        params.put("payment", payment);
//        params.put("message", message);
//        params.put("delivery_time", delivery_time);
//        params.put("shipping_type", shipping_type);
        HttpUtil.httpClientGetSend(context, params, BaseConstant.ORDER_CONFIRMORDER);
    }


}
