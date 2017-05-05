package com.zmr.xuxiaoxing.ui.login.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.util.ReadAssetsRawTextUtil;
import com.zmr.xuxiaoxing.R;

import butterknife.Bind;

/**
 * 描述：协议
 * 作者：xiaoxing on 17/4/8 09:06
 * 邮箱：2235445233@qq.com
 */
public class XieYiActivity extends BaseActivity {
    @Bind(R.id.tv_xie_yi)
    TextView mTvXieYi;

    @Override
    public int bindLayout() {
        return R.layout.activity_xie_yi;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        setHeaderTitle(R.string.xieyi);

    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        initXieYiData();
    }

    /**
     * 初始化协议数据
     */
    private void initXieYiData() {
        mTvXieYi.setText(ReadAssetsRawTextUtil.readFromAssets(this, "xieyi.txt"));
    }

}
