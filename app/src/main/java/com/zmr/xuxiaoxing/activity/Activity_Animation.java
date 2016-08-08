package com.zmr.xuxiaoxing.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.socks.library.KLog;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.base.AppBaseActivity;

import butterknife.Bind;

/**
 * 描述：启动动画
 * 作者：徐小星 on 2016/7/20 13:26
 * 邮箱：xx@yougudongli.com
 */
public class Activity_Animation extends AppBaseActivity {
    public static final int ANIMATION_TIME = 3000;
    @Bind(R.id.iv_animation_logo)
    ImageView ivAnimationLogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void doBussinessBeforeOnCreate() {
        super.doBussinessBeforeOnCreate();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉界面任务条
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    @Override
    public int bindLayout() {
        return R.layout.activity_animation;
    }


    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setTitleHidden();
        begin_animation();
    }

    /**
     * 开始动画
     */
    private void begin_animation() {

        //图片渐变模糊度始终
        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        //渐变时间
        aa.setDuration(ANIMATION_TIME);
        //展示图片渐变动画
        ivAnimationLogo.startAnimation(aa);

        //渐变过程监听
        aa.setAnimationListener(new Animation.AnimationListener() {

            /**
             * 动画开始时
             */
            @Override
            public void onAnimationStart(Animation animation) {
                KLog.e("动画开始..");
            }

            /**
             * 重复动画时
             */
            @Override
            public void onAnimationRepeat(Animation animation) {
                KLog.e("动画重复...");
            }

            /**
             * 动画结束时
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                KLog.e("动画结束...");
                getOperation().startActivity(Activity_Main.class);
                finish();
            }
        });
    }
}