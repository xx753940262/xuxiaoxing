package com.xiaoxing.module.splash.activity;

import android.content.Context;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseConstant;


/**
 * 描述：欢迎引导页
 * 作者：xiaoxing on 17/4/20 13:14
 * 邮箱：2235445233@qq.com
 */
public class SplashActivity extends BaseActivity {


    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        check_is_first();
    }

    /**
     * 检查是否第一次登陆
     */
    private void check_is_first() {
        if (getIsFirstLogin()) {//第一次

            //startActivity(Activity_Animation.class);
            startActivity(Activity_Guide.class);
            sHelperPutBoolen(BaseConstant.IS_FIRST, false);
        } else {
            startActivity(Activity_Animation.class);
        }
        finish();
    }
}
