package com.xiaoxing.module.login.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.util.ReadAssetsRawTextUtil;

/**
 * 描述：协议
 * 作者：xiaoxing on 17/4/8 09:06
 * 邮箱：2235445233@qq.com
 */
public class XieYiActivity extends BaseActivity {
    private TextView mTvXieYi; //协议内容

    @Override
    public int bindLayout() {
        return R.layout.activity_xie_yi;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        setHeaderTitle(R.string.xieyi);

        mTvXieYi = (TextView) view.findViewById(R.id.tv_xie_yi);
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
