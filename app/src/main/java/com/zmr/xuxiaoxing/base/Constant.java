package com.zmr.xuxiaoxing.base;


import com.xiaoxing.common.base.BaseConstant;

/**
 * 描述：java类中的常量都放在Constant中，xml中的常量都放在strings.xml中
 * 作者：徐小星 on 2016/7/21 12:42
 * 邮箱：2235445233@qq.com
 */
public class Constant extends BaseConstant {
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

    public static final String STU_IS_LOGIN = "stu_is_login"; //是否已经登录
    public static final String STU_UID = "stu_uid";
    public static final String STU_TOKEN = "stu_token";
    public static final String STU_USERNAME = "stu_username";
    public static final String STU_PASSWORD = "stu_password";
    public static final String SMS_MESSAGE_REGISTER = "为您的注册验证码,请在20分钟内完成注册。如非本人操作,请忽略。";

    //url
//    public static final String DOMAIN = "http://bc.bvhao.cc"; //域名
//    public static final String DOMAIN_API = "http://bc.bvhao.cc/api"; //接口访问地址
    public static final String DOMAIN = "http://yigoo.gxhao.cc"; //域名
    public static final String DOMAIN_API = "http://yigoo.gxhao.cc/api"; //接口访问地址
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

    //Shop
    public static final String SHOP_CATEGORY = "/Shop/category"; //主页获取分类
    public static final String SHOP_LIST = "/Shop/lists"; //店铺列表（超市外卖）
    public static final String SHOP_DETAIL = "/Shop/detail"; //店铺主页-店铺信息
    public static final String SHOP_PRODUCTS = "/Shop/shopProducts"; //店铺主页-分类和商品
    public static final String PRODUCT_DETAIL = "/Shop/productDetail"; //商品详情
    public static final String SHOP_TAKEAWAY_CATE = "/shop/takeawayCate"; //外卖分类商品获取
    public static final String SHOP_ACTIVITIES = "/shop/activities"; //1：满立减10 2：五折吃大餐 3：午餐秒杀

    //Teacher
    public static final String TEACHER_INDEX = "/Teacher/index"; //老师列表
    public static final String TEACHER_DETAIL = "/Teacher/detail"; //老师信息

    //Index
    public static final String INDEX_HOME = "/index/home"; //首页接口数据

    //Car
    public static final String CART_ADD = "/cart/add"; //添加到购物车
    public static final String CART_LIST = "/cart/lists"; //购物车列表
    public static final String CART_UPDATENUM = "/cart/updateNum"; //购物车商品数量修改
    public static final String CART_DEL = "/cart/del"; //购物车商品删除

    //Product

    public static final String PRODUCT_COLLECT = "/Product/collect"; //商品收藏
    public static final String PRODUCT_SEARCH = "/product/search"; //搜索页接口

    //Order
    public static final String ORDER_CONFIRMORDER = "/order/confirmOrder"; //提交订单
    public static final String ORDER_SET_ORDER = "/order/setOrder"; //确认订单
    public static final String ORDER_GET_LISTS = "/order/getLists"; //订单列表
    public static final String ORDER_DETAIL = "/order/orderDetail"; //订单详情
    public static final String ORDER_CONFIRM_ORDERNOW = "/order/confirmOrderNow"; //立即购买
    public static final String ORDER_AFFIRM = "/order/affirm"; //确认收货
    public static final String ORDER_COMMENT = "/order/comment"; //订单评论
    public static final String ORDER_DEL = "/order/del"; //删除订单
    public static final String ORDER_FEEDBACK_SUBMIT = "/order/feedback_submit"; //订单退货售后提交

    //Address
    public static final String ADDRESS_GET = "/address/get"; //地址列表
    public static final String ADDRESS_SET = "/address/set"; //添加地址
    public static final String ADDRESS_DEL = "/address/del"; //删除地址
    public static final String ADDRESS_UPDATE = "/address/update"; //修改地址
    public static final String ADDRESS_GETDEFAULT = "/address/getDefault"; //获取默认收货地址
    public static final String ADDRESS_SETDEFAULT = "/address/setDefault"; //修改默认收货地址

    //Question

    public static final String QUIZ_LISTS = "/quiz/lists"; //题目列表
    public static final String QUIZ_DETAIL = "/quiz/detail"; //题目详情
    public static final String QUIZ_ADD = "/quiz/add"; //发布题目
    public static final String QUIZ_GET_SUBJECT = "/quiz/getSubject"; //获取科目


    //Index
    public static final String INDEX_CATEGORY = "/index/category"; //分类页
    public static final String INDEX_PRODUCTS = "/index/products"; //分类内的商品

    //Course
    public static final String COURSE_ADD = "/Course/add"; //添加课程表
    public static final String COURSE_LISTS = "/Course/lists"; //获取课程表

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
