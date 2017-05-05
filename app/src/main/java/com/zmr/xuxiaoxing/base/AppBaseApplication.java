package com.zmr.xuxiaoxing.base;


import com.xiaoxing.common.base.BaseApplication;
import com.xiaoxing.common.http.HttpUtil;
import com.zmr.xuxiaoxing.service.InitializeService;

/**
 * 描述：app的Application基类
 * 作者：徐小星 on 2016/7/20 17:05
 * 邮箱：2235445233@qq.com
 */
public class AppBaseApplication extends BaseApplication {

    private volatile static AppBaseApplication INSTANCE; //声明成 volatile

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtil.setServiceUrl(Constant.DOMAIN_API);

        InitializeService.start(this);

    }

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static AppBaseApplication getSingleton() {
        if (INSTANCE == null) {
            synchronized (AppBaseApplication.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppBaseApplication();
                }
            }
        }
        return INSTANCE;
    }


}