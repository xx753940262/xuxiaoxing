package com.xiaoxing.common.dialog.loadingDialog;

import android.content.Context;

import com.ab.R;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/23 22:10
 * 邮箱：2235445233@qq.com
 */
public class LoadingDialogUtil {

    private static LoadingDialog dialog;

    /**
     * 显示美团进度对话框
     *
     * @param
     */
    public static void showmeidialog(Context context) {
        dialog = new LoadingDialog(context, "正在加载中", R.drawable.frame, LoadingDialog.Type_IMG);
        dialog.show();
    }

    public static void showGifdialog(Context context) {
        dialog = new LoadingDialog(context, "", R.raw.loading001, LoadingDialog.Type_GIF);
        dialog.show();
    }

    /**
     */
    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }

    }

}
