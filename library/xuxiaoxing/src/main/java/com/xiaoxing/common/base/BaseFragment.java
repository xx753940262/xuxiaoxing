package com.xiaoxing.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.fragment.AbDialogFragment;
import com.xiaoxing.common.fragment.AbFragment;
import com.xiaoxing.common.fragment.AbLoadDialogFragment;
import com.xiaoxing.common.util.AbDialogUtil;
import com.xiaoxing.common.util.ProgressDialogUtils;
import com.xiaoxing.common.util.SharedPreferencesHelper;

import butterknife.ButterKnife;

/**
 * Created by xuxing on 2016/3/30 0030.
 */
public class BaseFragment extends AbFragment implements AbDialogFragment.AbDialogOnLoadListener, IBaseFragment {

    /**
     * 共通操作
     **/
    private Operation mBaseOperation = null;

    protected SharedPreferencesHelper sHelper;
    private AbLoadDialogFragment mDialogFragment = null; //加载动画

    protected boolean isVisible;
    protected boolean isPrepared;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //渲染视图View(防止切换时重绘View)
        if (null != mContentView) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeView(mContentView);
            }
        } else {
            mContentView = inflater.inflate(bindLayout(), container, false);
            ButterKnife.bind(this, mContentView);
            //实例化共通操作
            mBaseOperation = new Operation(getActivity());
            //初始化SharedPreferences
            initSharedPreferencesHelper();
            // 控件初始化
            initView(mContentView, inflater);

        }
        //业务处理
        doBusiness(getActivity());

        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isPrepared = true;
        lazyLoad();
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        initData();
    }

    // 不可见
    private void onInvisible() {

    }

    @Override
    public void initData() {

    }

    protected void loading() {
        ProgressDialogUtils.showProgressDialog(getActivity(), BaseConstant.DATA_LOADING);
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView(View view, LayoutInflater inflater) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mBaseOperation;
    }

    /**
     * 初始化SharedPreferencesHelper
     */
    private void initSharedPreferencesHelper() {
        sHelper = getOperation().getSharedPreferencesHelper(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 显示进度框
     *
     * @param strTip
     */
    public void showLoadDialog(String strTip) {
        mDialogFragment = AbDialogUtil.showLoadDialog(getActivity(), R.drawable.ic_loading, strTip);
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

    /**
     * 判断是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return sHelper.getBoolean(BaseConstant.IS_LOGIN);
    }
}
