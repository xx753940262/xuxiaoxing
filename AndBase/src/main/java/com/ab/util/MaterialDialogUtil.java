package com.ab.util;

import android.content.Context;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * 描述：MaterialDialogUtil
 * 作者：徐小星 on 2016/9/12 0012 13:20
 * 邮箱：xx@yougudongli.com
 */
public class MaterialDialogUtil {
    static MaterialDialog mMaterialDialog;

    public static void showDialog_OneButton(Context context, String Title, int view) {
        mMaterialDialog = new MaterialDialog(context);
        mMaterialDialog.setTitle(Title);
        mMaterialDialog.setContentView(view);
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.setPositiveButton("关闭", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });

        mMaterialDialog.show();
    }
}
