package com.xiaoxing.module.splash.activity;

import android.content.Context;
import android.os.Bundle;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseConstant;


/**
 * 描述：
 * 作者：xiaoxing on 17/4/20 13:14
 * 邮箱：2235445233@qq.com
 */
public class Activity_Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
        Boolean is_first = sHelper.getBoolean(BaseConstant.IS_FIRST, true);
        if (is_first) {//第一次

            getOperation().startActivity(Activity_Animation.class);
//            getOperation().startActivity(Activity_Guide.class);
            sHelper.putBoolean(BaseConstant.IS_FIRST, false);
        } else {
            getOperation().startActivity(Activity_Animation.class);
        }
        finish();
    }
}
