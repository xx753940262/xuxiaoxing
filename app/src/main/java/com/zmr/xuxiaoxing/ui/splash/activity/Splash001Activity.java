package com.zmr.xuxiaoxing.ui.splash.activity;

import android.content.Context;

import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseConstant;
import com.zmr.xuxiaoxing.R;


/**
 * 描述：欢迎引导页
 * 作者：xiaoxing on 17/4/20 13:14
 * 邮箱：2235445233@qq.com
 */
public class Splash001Activity extends BaseActivity {


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

            //startActivity(AnimationActivity.class);
            startActivity(GuideActivity.class);
            sHelperPutBoolen(BaseConstant.IS_FIRST, false);
        } else {
            startActivity(AnimationActivity.class);
        }
        finish();
    }
}
