package com.zmr.xuxiaoxing.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaoxing.common.download.down01.SystemParams;
import com.xiaoxing.common.http.HttpUtil;
import com.xiaoxing.common.view.dialog.StyledDialog;
import com.zmr.xuxiaoxing.BuildConfig;
import com.zmr.xuxiaoxing.base.Constant;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/17 11:00
 * 邮箱：2235445233@qq.com
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.anly.githubapp.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        SystemParams.init(getApplicationContext());
        initKLog();

        StyledDialog.init(getApplicationContext());


        initActiveAndroid();
        initBugly();
        //CrashReport.testJavaCrash();
    }

    /**
     * 初始化Bugly
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APPID, false);
    }

    /**
     * 初始化数据库
     *
     * @return
     */
    private void initActiveAndroid() {
        //        ActiveAndroid.initialize(this,true); //在5.0系统上不支持这种初始化方式

//        Configuration configuration = new Configuration.Builder(getApplicationContext())
//                .setDatabaseName("test.db")
//                .setDatabaseVersion(1)
//                .setModelClasses(InCart.class, GoodsInfo.class)
//                .create();
//
//        ActiveAndroid.initialize(configuration, true);
    }


    /**
     * 初始化KLog工具
     */
    private void initKLog() {
        KLog.init(BuildConfig.LOG_DEBUG);
    }
}