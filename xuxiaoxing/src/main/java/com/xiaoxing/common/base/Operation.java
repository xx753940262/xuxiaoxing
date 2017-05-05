package com.xiaoxing.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaoxing.common.util.SharedPreferencesHelper;
import com.xiaoxing.common.third.zxing.activity.CaptureActivity;

/**
 * 公用的方法
 * Created by xuxing on 2016/4/14 0014.
 */
public class Operation {

    /***
     * 上下文
     **/
    private Activity mContext = null;

    public Operation(Activity mContext) {
        this.mContext = mContext;
    }

    /**
     * 跳转到另一个界面
     */
    public void startActivity(Class<? extends Activity> activity) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent);
    }

    /**
     * 跳转到另一个界面,带返回值
     */
    public void startActivityForResult(Bundle bundle, int requestCode, Class<? extends Activity> activity) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, activity);
        mIntent.putExtras(bundle);
        mContext.startActivityForResult(mIntent, requestCode);
    }

    /**
     * 跳转到另一个界面，带参数
     *
     * @param bundle 参数
     */
    public void startBundleActivity(Bundle bundle, Class<? extends Activity> activity) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, activity);
        mIntent.putExtras(bundle);
        mContext.startActivity(mIntent);
    }

    /**
     * 跳转到扫描二维码界面
     */
    public void startScanActivity() {
        Intent intent = new Intent();
        intent.setClass(mContext, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivityForResult(intent, BaseConstant.SCANNIN_GREQUEST_CODE);
    }

    /**
     * 获取EditText的值
     *
     * @param editText
     * @return
     */
    public String getEidtTextValue(EditText editText) {
        return editText.getText().toString();
    }

    /**
     * 获取TextView的值
     *
     * @param textView
     * @return
     */
    public String getTextViewValue(TextView textView) {
        return textView.getText().toString();
    }

    /**
     * 初始化SharedPreferencesHelper
     */
    public SharedPreferencesHelper getSharedPreferencesHelper(Context context) {
        return SharedPreferencesHelper.getInstance(context);
    }



}
