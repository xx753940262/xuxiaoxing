package com.xiaoxing.common.base;

/**
 * 描述：java类中的常量都放在Constant中，xml中的常量都放在strings.xml中
 * 作者：徐小星 on 2016/7/21 12:42
 * 邮箱：xx@yougudongli.com
 */
public class BaseConstant {
    public static final int SCANNIN_GREQUEST_CODE = -1;
    public static final double RESULT_OK = -1; //扫描二维码返回的参数
    public static final int SECURITY_CODE_TIME = 60000;
    public static final String HTTP_RESULT_OK = "200";


    /*客户端加签key*/
    public static final String SIGN_KEY = "70ee0b71c6f22066bbe36f72498468b3";
    /*AES加密解密key*/
    public static final String AES_KEY = "1d833e2f18c662edff37f508316821cc";

    public static final String DATA_LOADING = "数据加载中...";

    public static final boolean DEBUG = false;


    public static final boolean AUTO_LOGIN = true; //是否开启自动登陆

    public static final String IS_LOGIN = "is_login"; //是否已经登录
    public static final String BUGLY_APPID = "600e334eac"; //腾讯bugly

    public static final String IS_FIRST = "is_first"; //是否第一次登陆app

    public static final String UID = "uid";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String COVER = "cover";

    //url
    public static final String DOMAIN = "http://yigoo.gxhao.cc"; //域名
    public static final String DOMAIN_API = DOMAIN + "/api"; //接口访问地址


    //User
    public static final String LOGIN = "/User/login"; //登陆地址
    public static final String STU_LOGIN = "/User/school_login"; //学生登录
    public static final String SEND_SMS = "/User/sendCode"; //发送短信
    public static final String SENE_CHECK_CODE = "/User/sendCheckCode"; //忘记密码发送短信
    public static final String VERITY_CODE = "/User/verityCode"; //验证短信
    public static final String REGISTER = "/User/register"; //注册
    public static final String UPDATE_PASSWORD = "/User/updatePassword"; //找回密码
    public static final String CHANGE_PASSWORD = "/User/changePassword"; //用户修改密码
    public static final String MEMBER_INFO = "/User/memberInfo"; //用户信息
    public static final String UPDATE_USERINFO = "/User/updateUserInfo"; //修改用户信息
    public static final String UPLOAD_MEMBER_IMAGE = "/User/uploadMemberImage"; //上传头像
    public static final String USER_MEMBER_INFO = "/User/memberInfo"; //用户信息
    public static final String USER_UPDATE_USER_INFO = "/User/updateUserInfo"; //修改用户信息
    public static final String USER_UPLOAD_MEMBER_IMAGE = "/User/uploadMemberImage"; //上传头像

    //Address
    public static final String ADDRESS_GET = "/address/get"; //地址列表
    public static final String ADDRESS_SET = "/address/set"; //添加地址
    public static final String ADDRESS_DEL = "/address/del"; //删除地址
    public static final String ADDRESS_UPDATE = "/address/update"; //修改地址
    public static final String ADDRESS_GETDEFAULT = "/address/getDefault"; //获取默认收货地址
    public static final String ADDRESS_SETDEFAULT = "/address/setDefault"; //修改默认收货地址


    //Car
    public static final String CART_ADD = "/cart/add"; //添加到购物车
    public static final String CART_LIST = "/cart/lists"; //购物车列表
    public static final String CART_UPDATENUM = "/cart/updateNum"; //购物车商品数量修改
    public static final String CART_DEL = "/cart/del"; //购物车商品删除

    //Product

    public static final String PRODUCT_COLLECT = "/Product/collect"; //商品收藏


    //Order
//    public static final String ORDER_CONFIRMORDER = "/order/confirmOrder"; //提交订单
    public static final String ORDER_CONFIRMORDER = "/order/addorder"; //提交订单
    public static final String ORDER_SET_ORDER = "/order/setOrder"; //确认订单
    public static final String ORDER_GET_LISTS = "/order/getLists"; //订单列表
    public static final String ORDER_DETAIL = "/order/orderDetail"; //订单详情
    public static final String ORDER_CONFIRM_ORDERNOW = "/order/confirmOrderNow"; //立即购买


    public static final class BROADCAST_FILTER {
        public static final String FILTER_CODE = "broadcast_filter";
        public static final String EXTRA_CODE = "broadcast_intent";
    }

    public static final class INTENT_KEY {
        public static final String MENU_TO_GOODS_LIST = "category_menu";
        public static final int LOGIN_REQUEST_CODE = 20000;
        public static final int LOGIN_RESULT_SUCCESS_CODE = 20001;
        public static final int REQUEST_CART_TO_DETAIL = 50000;
        /**
         * 从我的到更多，返回时刷新登录信息
         */
        public static final int REQUEST_MOREACTIVITY = 60000;
        /**
         * 将GoodsInfo对象传给商品列表界面
         */
        public static final String INFO_TO_DETAIL = "goodsinfo_to_detail";
        /**
         * 从我的关注跳转到首页
         */
        public static final String FROM_FAVOR = "from_favor";
        /**
         * 从详情跳转到购物车
         */
        public static final String FROM_DETAIL = "from_detail";
        /**
         * 刷新购物车商品数
         */
        public static final String REFRESH_INCART = "refresh_incart";
        /**
         * 首页加载更多
         */
        public static final String LOAD_MORE = "load_more";
        /**
         * 用于显示商品详情
         */
        public static final String SHANG_PIN_XIANG_QING = "shang_pin_xiang_qing";
        /**
         * 更新用户的信息
         */
        public static final String UPDATE_USER_INFO = "update_user_info";
        /**
         * 确认订单，商家Id
         */
        public static final String QUE_REN_DING_DAN = "que_ren_ding_dan";
        /**
         * 更新购物车
         */
        public static final String UPDATE_CART = "update_cart";
        /**
         * 通过Bmob登录成功
         */
        public static final String LOGIN_BMOB_SUCCESS = "bmob_success";
        /**
         * 注销
         */
        public static final String LOGOUT = "logout";
    }

}
