package com.ab.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.util.SharedPreferencesHelper;

/**
 * 公用的方法
 * Created by xuxing on 2016/4/14 0014.
 */
public class Operation {

    private Intent mIntent = new Intent();
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
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent);
    }

    /**
     * 跳转到另一个界面，带参数
     *
     * @param bundle 参数
     */
    public void startBundleActivity(Bundle bundle, Class<? extends Activity> activity) {
        mIntent.setClass(mContext, activity);
        mIntent.putExtras(bundle);
        mContext.startActivity(mIntent);
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
