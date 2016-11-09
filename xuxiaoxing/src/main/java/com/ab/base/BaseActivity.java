package com.ab.base;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.R;
import com.ab.activity.AbActivity;
import com.ab.fragment.AbDialogFragment;
import com.ab.fragment.AbLoadDialogFragment;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.SharedPreferencesHelper;
import com.ab.view.titlebar.AbTitleBar;

import butterknife.ButterKnife;

/**
 * Created by xuxing on 2015/11/25.
 */
public class BaseActivity extends AbActivity implements AbDialogFragment.AbDialogOnLoadListener, IBaseActivity {

    private Operation mBaseOperation = null;  //共通操作
    private View mContextView = null; //当前Activity渲染的视图View
    private AbLoadDialogFragment mDialogFragment = null; //加载动画
    public long mExitTime; //按两次退出时间
    public AbTitleBar mAbTitleBar = null;  //标题
    public SharedPreferencesHelper sHelper; //SharedPreferencesHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate之前的业务操作
        doBussinessBeforeOnCreate();
        super.onCreate(savedInstanceState);

        //设置渲染视图View
        mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setAbContentView(mContextView);
        ButterKnife.bind(this);
        //实例化共通操作
        mBaseOperation = new Operation(this);
        //初始化SharedPreferences
        initSharedPreferencesHelper();
        //初始化控件
        initView(mContextView);
        //业务操作
        doBusiness(this);
    }


    /**
     * 跳转到另一个界面
     *
     * @param tClass 目标页面
     */
    public void startActivity(Class tClass) {
        getOperation().startActivity(tClass);
    }

    /**
     * 跳转到另一个界面，带参数
     *
     * @param bundle 参数
     * @param tClass 目标页面
     */
    public void startBundleActivity(Bundle bundle, Class tClass) {
        getOperation().startBundleActivity(bundle, tClass);
    }

    /**
     * 获取EditText的值
     *
     * @param editText
     * @return
     */
    public String getEidtTextValue(EditText editText) {
        return getOperation().getEidtTextValue(editText);
    }

    /**
     * 获取TextView的值
     *
     * @param textView
     * @return
     */
    public String getTextViewValue(TextView textView) {
        return getOperation().getTextViewValue(textView);
    }

    @Override
    public void doBussinessBeforeOnCreate() {

    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void doBusiness(Context mContext) {
        //设置标题
        setTitle();
        initData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void getMoreData() {

    }

    /**
     * 点击两次返回退出app
     *
     * @param keyCode
     * @param event
     * @return
     */
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Object mHelperUtils;
//                AbToastUtil.showToast(this, "再按一次退出程序");
//                mExitTime = System.currentTimeMillis();
//
//            } else {
//                finish();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mBaseOperation;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setHeaderTitle(int title) {
        mAbTitleBar.setTitleText(AbStrUtil.rIntToStr(this, title));
        mAbTitleBar.setTitleBarGravity(Gravity.CENTER, Gravity.CENTER);
    }

    /**
     * 设置返回按钮
     */
    public void setHeaderBack() {
        mAbTitleBar.setLogo(R.drawable.btn_back_header);
    }

    /**
     * 设置标题
     */
    public void setTitle() {
        mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleBarBackground(R.drawable.head_title_bg_red);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
    }

    /**
     * 隐藏标题
     */
    public void setTitleHidden() {
        mAbTitleBar.setVisibility(View.GONE);
    }

    /**
     * 显示标题
     */
    public void setTitleShow() {
        mAbTitleBar.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化SharedPreferencesHelper
     */
    private void initSharedPreferencesHelper() {
        sHelper = getOperation().getSharedPreferencesHelper(this);
    }

    /**
     * 显示进度框
     *
     * @param strTip
     */
    public void showLoadDialog(String strTip) {
        mDialogFragment = AbDialogUtil.showLoadDialog(this, R.drawable.ic_loading, strTip);
        mDialogFragment.setAbDialogOnLoadListener(this);
    }

    /**
     * 结束显示进度框
     */
    public void stopLoadDialog() {

        mDialogFragment.loadFinish();
    }

    /**
     * 进度框显示时执行的方法
     */
    @Override
    public void onLoad() {

    }
}
