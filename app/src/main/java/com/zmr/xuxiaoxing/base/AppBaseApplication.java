package com.zmr.xuxiaoxing.base;

import com.ab.base.BaseApplication;
import com.ab.http.HttpUtil;
import com.socks.library.KLog;
import com.zmr.xuxiaoxing.BuildConfig;

/**
 * 描述：app的Application基类
 * 作者：徐小星 on 2016/7/20 17:05
 * 邮箱：xx@yougudongli.com
 */
public class AppBaseApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtil.setServiceUrl(Constant.DOMAIN);
        initKLog();

    }

    /**
     * 初始化KLog工具
     */
    private void initKLog() {
        KLog.init(BuildConfig.LOG_DEBUG);
    }




}