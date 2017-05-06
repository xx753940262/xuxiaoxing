package com.xiaoxing.common.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.ab.R;


/**
 * 描述：
 * 作者：徐小星 on 2016/12/2 16:58
 * 邮箱：xx@youdugongli.com
 */
public class ProgressDialogUtils {

    private static Dialog progressDialog;

    public static void showProgressDialog(Context context, String tipMsg) {
        progressDialog = new Dialog(context, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText(tipMsg);
        progressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
