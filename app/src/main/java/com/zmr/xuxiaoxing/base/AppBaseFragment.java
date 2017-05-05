package com.zmr.xuxiaoxing.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxing.common.base.BaseFragment;


/**
 * 描述：app的Fragment基类
 * 作者：徐小星 on 2016/7/20 17:05
 * 邮箱：2235445233@qq.com
 */

public class AppBaseFragment extends BaseFragment {

    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initView(View view, LayoutInflater inflater) {
        super.initView(view, inflater);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
    }


    /**
     * 给Fragment_Two,Fragment_Tthree,Fragment_Five设置标题
     *
     * @param titleStr
     */
    public void setFragmentTopBar(String titleStr) {
    }


}
