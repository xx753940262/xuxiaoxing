package com.xiaoxing.module.login.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.util.ReadAssetsRawTextUtil;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/8 09:06
 * 邮箱：2235445233@qq.com
 */
public class XieYiActivity extends BaseActivity {
    TextView tvXieYi;

    @Override
    public int bindLayout() {
        return R.layout.activity_xie_yi;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        tvXieYi  = (TextView) view.findViewById(R.id.tv_xie_yi);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        setHeaderTitle(R.string.xieyi);
        tvXieYi.setText(ReadAssetsRawTextUtil.readFromAssets(this, "xieyi.txt"));
    }
}
