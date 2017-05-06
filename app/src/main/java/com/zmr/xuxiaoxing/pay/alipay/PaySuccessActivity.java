package com.zmr.xuxiaoxing.pay.alipay;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.base.AppBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 描述：
 * 作者：xiaoxing on 17/4/22 15:48
 * 邮箱：2235445233@qq.com
 */
public class PaySuccessActivity extends AppBaseActivity {

    @BindView(R.id.dv_success)
    SimpleDraweeView dv_success;
    private DraweeController draweeController;

    @Override
    public int bindLayout() {
        return R.layout.activity_pay_success;
    }
    @Override
    public void doBussinessBeforeOnCreate() {
        super.doBussinessBeforeOnCreate();
        Fresco.initialize(this);
        /**
         * 下面是主要代码：
         */
        draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)//自动播放动画
//                .setUri(Uri.parse("http://s1.dwstatic.com/group1/M00/9B/31/468f0fc71e49479bf021c64cefee2882.gif"))//路径
                .setUri(Uri.parse("asset://com.baidumap.administrator.happystudy/success.gif"))//路径
                .build();

    }

    @Override
    public void initView(View view) {
        super.initView(view);

        dv_success.setController(draweeController);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderTitle("支付成功");
    }


    @OnClick({R.id.btn_wan_cheng})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wan_cheng:
                finish();
                break;
        }
    }
}

