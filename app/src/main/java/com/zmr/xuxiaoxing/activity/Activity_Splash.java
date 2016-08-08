package com.zmr.xuxiaoxing.activity;

import android.content.Context;
import android.os.Bundle;

import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.base.AppBaseActivity;
import com.zmr.xuxiaoxing.base.Constant;

/**
 * 描述：启动界面
 * 作者：徐小星 on 2016/7/20 14:45
 * 邮箱：xx@yougudongli.com
 */
public class Activity_Splash extends AppBaseActivity {

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
        Boolean is_first = sHelper.getBoolean(Constant.IS_FIRST, true);
        if (is_first) {//第一次
            sHelper.putBoolean(Constant.IS_FIRST, false);
            getOperation().startActivity(Activity_Guide.class);
        } else {
            getOperation().startActivity(Activity_Animation.class);
        }
        finish();
    }
}
