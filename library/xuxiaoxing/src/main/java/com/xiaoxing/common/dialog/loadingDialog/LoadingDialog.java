package com.xiaoxing.common.dialog.loadingDialog;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/23 22:01
 * 邮箱：2235445233@qq.com
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.R;

/**
 * 自定义Loading对话框
 */
public class LoadingDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    // 显示的图片
    private ImageView mImageView;
    // 提示的文字
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int mResid;
    private LinearLayout load_layout;
    // GIF动态图片
    private GifView load_gifv;
    // 加载框显示类型
    private String showType;

    public static final String Type_GIF = "GIF";
    public static final String Type_IMG = "IMG";

    /**
     * loading对话框构造方法
     *
     * @param context 上下文
     * @param content 提示信息
     * @param resId   资源
     * @param type    loading图类型 GIF;IMG
     */
    public LoadingDialog(Context context, String content, int resId, String type) {
        super(context);
        this.mContext = context;
        this.mLoadingTip = content;
        this.mResid = resId;
        this.showType = type;
        setCanceledOnTouchOutside(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        initView();
        initData();
        WindowManager.LayoutParams params = getWindow().getAttributes();

        Point size = new Point();

        getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        int height = size.y;
        //params.alpha = 0f;
        params.gravity = Gravity.CENTER;
        // 设置背景层透明度
        params.dimAmount = 0.6f;
        params.height = height/3;
        params.width = 4*width/7;
        getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        getWindow().setAttributes(params);
    }

    /**
     * 页面初始化
     */
    private void initView() {
        mLoadingTv = (TextView) findViewById(R.id.load_tetv);
        mImageView = (ImageView) findViewById(R.id.load_imgv);
        load_layout = (LinearLayout) findViewById(R.id.load_layout);
        load_gifv = (GifView) findViewById(R.id.load_gifv);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        if (showType.equals(Type_GIF)) {
            load_gifv.setMovieResource(mResid);
            load_gifv.setVisibility(View.VISIBLE);
            load_layout.setVisibility(View.GONE);
        } else if (showType.equals(Type_IMG)) {
            load_gifv.setVisibility(View.GONE);
            load_layout.setVisibility(View.VISIBLE);
            mImageView.setBackgroundResource(mResid);
            // 通过ImageView对象拿到背景显示的AnimationDrawable
            mAnimation = (AnimationDrawable) mImageView.getBackground();
            // 为了防止在onCreate方法中只显示第一帧的解决方案之一
            mImageView.post(new Runnable() {
                @Override
                public void run() {
                    mAnimation.start();
                }
            });
            mLoadingTv.setText(mLoadingTip);
        }
    }

    /**
     * 设置提示信息内容
     *
     * @param str
     */
    public void setContent(String str) {
        mLoadingTv.setText(str);
    }
}