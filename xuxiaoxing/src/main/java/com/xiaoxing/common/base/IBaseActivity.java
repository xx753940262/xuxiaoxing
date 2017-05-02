package com.xiaoxing.common.base;

import android.content.Context;
import android.view.View;

/**
 * Created by xuxing on 2016/3/31 0031.
 */
public interface IBaseActivity {
    /**
     * onCreate之前执行的方法
     */
    public void doBussinessBeforeOnCreate();

    /**
     * 绑定渲染视图的布局文件
     *
     * @return 布局文件资源id
     */
    public int bindLayout();

    /**
     * 初始化控件
     */
    public void initView(final View view);

    /**
     * 业务处理操作（onCreate方法中调用）
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
