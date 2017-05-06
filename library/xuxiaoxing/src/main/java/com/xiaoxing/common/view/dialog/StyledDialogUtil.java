package com.xiaoxing.common.view.dialog;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/23 22:19
 * 邮箱：2235445233@qq.com
 */
public class StyledDialogUtil {


    /**
     * 登录
     */
    public static void loginLoading() {
        StyledDialog.buildLoading("登录中...").show();
    }

    /**
     * 数据加载中...
     */
    public static void dataLoading() {
        StyledDialog.buildLoading("数据加载中...").show();
    }
    public static void dataSubmit() {
        StyledDialog.buildLoading("数据提交中...").show();
    }


}
