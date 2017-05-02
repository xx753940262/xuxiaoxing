package com.xiaoxing.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by xuxing on 2016/4/14 0014.
 */
public interface IBaseFragment {

    /**
     * 绑定渲染视图的布局文件
     *
     * @return 布局文件资源id
     */
    public int bindLayout();

    /**
     * 初始化控件
     */
    public void initView(final View view, LayoutInflater inflater);

    /**
     * 业务处理操作（onCreateView方法中调用）
     *
     * @param mContext 当前Activity对象
     */
    public void doBusiness(Context mContext);

    /**
     * 初始化数据
     */

    public abstract void initData();

    /**
     * 获取更多数据
     */
    public abstract void getMoreData();


}
