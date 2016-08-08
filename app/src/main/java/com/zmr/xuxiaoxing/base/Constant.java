package com.zmr.xuxiaoxing.base;

/**
 * 描述：java类中的常量都放在Constant中，xml中的常量都放在strings.xml中
 * 作者：徐小星 on 2016/7/21 12:42
 * 邮箱：xx@yougudongli.com
 */
public class Constant {
    public static final boolean DEBUG = false;

    public static final String BUGLY_APPID = "900041867";//Bugly的APPID

    public static final boolean AUTO_LOGIN = true; //是否开启自动登陆
    public static final String IS_FIRST = "is_first"; //是否第一次登陆app
    public static final String LOADING = "数据加载中..."; //数据加载中...


    public static final double RESULT_OK = -1; //扫描二维码返回的参数

    public static final int STATUS_ZERO = 0; //失败
    public static final int STATUS_ONE = 1; //成功
    public static final String OK = "确认"; //确认
    public static final String CANCEL = "取消"; //取消


    public static final String USERNAME_EMPTY = "账号不能为空！"; //账号为空提示
    public static final String PASSWORD_EMPTY = "密码不能为空！"; //密码为空提示
    public static final String LOGIN_LOADING = "正在登陆,请稍候..."; //登陆提示
    public static final String USERNAME = "username"; //用于SharedPreferences保存用户名
    public static final String PASSWORD = "password";//用于SharedPreferences保存密码


    public static final String YAN_ZHENG = "验证";//用于首页tab,验证
    public static final String PING_JIA = "评价";//用于首页tab,评价
    public static final String JING_YING = "经营";//用于首页tab,经营
    public static final String MY = "我的";//用于首页tab,我的
    public static final String MORE = "更多";//用于首页tab,更多

    public static final String WO = "我";//用于首页tab,验证
    public static final String SHI = "是";//用于首页tab,评价
    public static final String XU = "徐";//用于首页tab,经营
    public static final String XIAO = "小";//用于首页tab,我的
    public static final String XING = "星";//用于首页tab,更多

    public static final String FRAG_NUM_EMPTY_TIP = "验证密码不能为空";//消费码不能为空提示
    public static final String FRAG_NUM_LENGTH_14 = "验证密码最多14位数";//消费码最多14位数提示
    public static final String MEN_DIAN_EMPTY_TIP = "请选择门店";//请选择门店
    public static final String CHECKING = "验证中,请稍候...";//验证中,请稍候...

    public static final int GROUPON_TYPE = 1; //消费券验证
    public static final int COUPONS_TYPE = 2; //优惠券验证
    public static final int ACTIVITY_TYPE = 3; //活动报名验证
    public static final String CHECK_COUPON_TITLE = "消费券验证";//消费券验证标题
    public static final String CHECK_YOUHUI_TITLE = "优惠券验证";//优惠券验证标题
    public static final String CHECK_ACTIVITY_TITLE = "活动报名验证";//活动报名验证标题

    //url
    public static final String DOMAIN = "http://t.pinyuan.me/mapi/index.php?"; //接口访问地址
    public static final String LOGIN = "ctl=biz_user&act=dologin"; //登陆地址
    public static final String GET_MEN_DIAN_LIST = "ctl=biz_dealv&act=index"; //获取门店列表
    public static final String CHECK_COUPON = "ctl=biz_dealv&act=check_coupon"; //验证消费券
    public static final String USE_COUPON = "ctl=biz_dealv&act=use_coupon"; //使用消费券
    public static final String CHECK_YOUHUI = "ctl=biz_youhuiv&act=check_youhui"; //验证优惠券
    public static final String USE_YOUHUI = "ctl=biz_youhuiv&act=use_youhui"; //使用优惠券
    public static final String CHECK_ACTIVITY = "ctl=biz_eventv&act=check_event"; //验证活动券
    public static final String USE_ACTIVITY = "ctl=biz_eventv&act=use_event"; //使用活动


}
