package com.ab.base;


import android.app.Application;

/**
 * Created by xuxing on 2015/11/18.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

}
