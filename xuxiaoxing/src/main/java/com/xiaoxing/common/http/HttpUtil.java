package com.xiaoxing.common.http;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.util.CheckJsonTypeUtil;
import com.xiaoxing.common.util.AbLogUtil;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by xuxing on 2016/3/29 0029.
 */
public class HttpUtil {
    static Context context = null;

    private static String SERVICE_URL = "";

    public static String getServiceUrl() {
        return SERVICE_URL;
    }

    public static void setServiceUrl(String serviceUrl) {
        SERVICE_URL = serviceUrl;
    }

    /**
     * activity中post请求
     *
     * @param response
     * @param params
     * @param url
     */
    public static void httpClientPostSend(final Context response, AbRequestParams params, final String url) {
        httpPostClientSend((OnMessageResponse) response, params, url);
    }

    /**
     * fragment中get请求
     *
     * @param response
     * @param params
     * @param url
     */
    public static void httpClientFragmentPostSend(final Fragment response, AbRequestParams params, final String url) {
        httpPostClientSend((OnMessageResponse) response, params, url);
    }

    /**
     * activity中get请求
     *
     * @param response
     * @param params
     * @param url
     */
    public static void httpClientGetSend(final Context response, AbRequestParams params, final String url) {
        httpGetClientSend((OnMessageResponse) response, params, url);
    }

    /**
     * fragment中get请求
     *
     * @param response
     * @param params
     * @param url
     */
    public static void httpClientFragmentGetSend(final Fragment response, AbRequestParams params, final String url) {
        httpGetClientSend((OnMessageResponse) response, params, url);
    }

    /**
     * post请求
     *
     * @param response
     * @param params
     * @param url
     */
    private static void httpPostClientSend(final OnMessageResponse response, AbRequestParams params, final String url) {
        initContext(response, url, "SERVICE_Post_URL==");
        AbHttpUtil.getInstance(context).post(SERVICE_URL + url, params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        doSuccessResult(content, url, response);
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFinish() {
//                        StyledDialog.dismissLoading();
                    }

                    @Override
                    public void onFailure(int statusCode, String content, Throwable error) {
                        doFailureResult(statusCode, content, error, response, url);
                    }

                }

        );
    }

    /**
     * get请求
     *
     * @param response
     * @param params
     * @param url
     */
    private static void httpGetClientSend(final OnMessageResponse response, AbRequestParams params, final String url) {
        initContext(response, url, "SERVICE_GET_URL==");
        AbHttpUtil.getInstance(context).get(SERVICE_URL + url, params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {

                        doSuccessResult(content, url, response);
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFinish() {
                        //StyledDialog.dismissLoading();

                    }

                    @Override
                    public void onFailure(int statusCode, String content, Throwable error) {

                        doFailureResult(statusCode, content, error, response, url);
                    }

                }

        );
    }

    /**
     * 初始化context，activity或fragment
     *
     * @param response
     * @param url
     * @param tag
     */
    private static void initContext(OnMessageResponse response, String url, String tag) {
        AbLogUtil.e(tag, SERVICE_URL + url);

        if (response instanceof Fragment) {
            context = ((Fragment) response).getActivity();
        } else {
            context = (Context) response;
        }
    }

    /**
     * 处理返回成功数据
     *
     * @param content
     * @param url
     * @param response
     */
    private static void doSuccessResult(String content, String url, OnMessageResponse response) {

        LoadingDialogUtil.dismissDialog();
        if (!AbStrUtil.isEmpty(content)) {

//            content = AESHelper.decrypt(content, BaseConstant.AES_KEY);
            AbLogUtil.e("接口返回的数据=====" + url + "======", content);
            try {
                if (!AbStrUtil.isEmpty(content)) {

                    CheckJsonTypeUtil.JSON_TYPE mJsonType = CheckJsonTypeUtil.getJSONType(content);

                    if (mJsonType == CheckJsonTypeUtil.JSON_TYPE.JSON_TYPE_OBJECT) {

                        Log.e("JSONObject", "JSONObject");

                        JSONObject jo = new JSONObject(content);

                        Log.e("JSONObject", "JSONObject11111");
                        response.onMessageResponse(url, jo);

                    } else if (mJsonType == CheckJsonTypeUtil.JSON_TYPE.JSON_TYPE_ARRAY) {

                        Log.e("JSONArray", "JSONArray");
                        JSONArray jsonArray = new JSONArray(content);
                        response.onMessageResponse(url, jsonArray);

                    } else {
                        Log.e("S", "S");
                        response.onMessageResponse(url, "");
                    }

                } else {
                    ToastUtil.showMessage(context, "没有数据");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

    }

    /**
     * 处理返回失败数据
     *
     * @param statusCode
     * @param content
     * @param error
     * @param response
     * @param url
     */
    private static void doFailureResult(int statusCode, String content, Throwable error, OnMessageResponse response, String url) {
        try {
            LoadingDialogUtil.dismissDialog();
            AbLogUtil.e("statusCode==", statusCode + "");
            AbLogUtil.e("content==", content + "");
            AbLogUtil.e("error==", error + "");
            if (statusCode == 900) {
                ToastUtil.showMessage(context, "连接服务器超时，请重试！");
            } else {
                ToastUtil.showMessage(context, error + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }


}
