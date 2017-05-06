package com.xiaoxing.common.base;


import android.app.Application;

/**
 * Created by xuxing on 2015/11/18.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }

    }
    public static BaseApplication getInstance() {
        return instance;
    }

}
