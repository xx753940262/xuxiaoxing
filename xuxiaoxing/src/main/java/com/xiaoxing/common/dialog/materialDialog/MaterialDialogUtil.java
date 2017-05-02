package com.xiaoxing.common.dialog.materialDialog;

import android.content.Context;
import android.view.View;

import com.xiaoxing.common.util.ScreenUtils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * 描述：MaterialDialogUtil
 * 作者：徐小星 on 2016/9/12 0012 13:20
 * 邮箱：xx@yougudongli.com
 */
public class MaterialDialogUtil {
    static MaterialDialog mMaterialDialog;

    private static DialogPlus mDialogPlus;

    /**
     * 一个按钮的提示dialog
     *
     * @param context
     * @param Title
     * @param view
     */
    public static void showDialog_OneButton(Context context, String Title, View view) {
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

    public static void showDialog_NoButton(Context context, String Title, View view) {
        mMaterialDialog = new MaterialDialog(context);
        mMaterialDialog.setTitle(Title);
        mMaterialDialog.setContentView(view);
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.show();
    }

    /**
     * 两个按钮的提示dialog
     *
     * @param context
     * @param Title
     * @param view
     * @param positiveListener
     */
    public static void showDialogTwoButton(Context context, String Title, View view, final View.OnClickListener positiveListener) {
        mMaterialDialog = new MaterialDialog(context);
        mMaterialDialog.setTitle(Title);
        mMaterialDialog.setContentView(view);
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.setNegativeButton("关闭", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setPositiveButton("确定", positiveListener);
        mMaterialDialog.show();
    }

    /**
     */
    public static void dismissDialogButton() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
        }

    }

    /**
     * @param context
     * @param viewContent
     */
    public static void shwoDialogPlus(Context context, int gravity, View viewContent) {

        mDialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(new ViewHolder(viewContent))
                .setCancelable(true)
                .setContentHeight(ScreenUtils.getScreenHeight(context))
                .setGravity(gravity)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        mDialogPlus.show();
    }

    public static void dismissDialogPlus() {
        if (mDialogPlus != null) {
            mDialogPlus.dismiss();
        }

    }

}
