package com.zmr.xuxiaoxing.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.base.BaseFragment;

/**
 * 描述：app的Fragment基类
 * 作者：徐小星 on 2016/7/20 17:05
 * 邮箱：xx@yougudongli.com
 */

public class AppBaseFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
