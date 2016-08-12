package com.ab.http;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.ab.util.AbLogUtil;
import com.ab.util.AbToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuxing on 2016/3/29 0029.
 */
public class HttpUtil {
    static Context context = null;
    //    private static String SERVICE_URL = "http://" + Config.domain;
    private static String SERVICE_URL = "";

    public static String getServiceUrl() {
        return SERVICE_URL;
    }

    public static void setServiceUrl(String serviceUrl) {
        SERVICE_URL = serviceUrl;
    }


    public static void httpClientPostSend(final Context response, AbRequestParams params, final String url) {
        httpPostClientSend((OnMessageResponse) response, params, url);
    }

    public static void httpClientFragmentPostSend(final Fragment response, AbRequestParams params, final String url) {
        httpPostClientSend((OnMessageResponse) response, params, url);
    }

    public static void httpClientGetSend(final Context response, AbRequestParams params, final String url) {
        httpGetClientSend((OnMessageResponse) response, params, url);
    }

    public static void httpClientFragmentGetSend(final Fragment response, AbRequestParams params, final String url) {
        httpGetClientSend((OnMessageResponse) response, params, url);
    }

    private static void httpPostClientSend(final OnMessageResponse response, AbRequestParams params, final String url) {
        AbLogUtil.e("SERVICE_Post_URL==", SERVICE_URL + url);

        if (response instanceof Fragment) {
            context = ((Fragment) response).getActivity();
        } else {
            context = (Context) response;
        }

        final Context finalContext = context;
        AbHttpUtil.getInstance(context).post(SERVICE_URL + url, params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {

//                        content = AbBase64.decode(content, "UTF-8");
                        Log.e("接口返回的数据=", content);
                        try {
                            JSONObject jo = new JSONObject(content);
                            response.onMessageResponse(url, jo);
                        } catch (JSONException e) {
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(content);
                                response.onMessageResponse(url, jsonArray);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                                try {
                                    response.onMessageResponse(url, jsonArray);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }

                        }
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        try {
                            AbLogUtil.e("statusCode==", statusCode + "");
                            AbLogUtil.e("content==", content + "");
                            AbLogUtil.e("error==", error + "");
                            if (statusCode == 600) {
                                AbToastUtil.showToast(context, content);
                            } else if (statusCode == 900) {
                                AbToastUtil.showToast(context, "连接服务器失败，请检查服务器是否正常.");
                            }
                            response.onMessageResponse(url, "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

        );
    }

    private static void httpGetClientSend(final OnMessageResponse response, AbRequestParams params, final String url) {
        AbLogUtil.e("SERVICE_GET_URL==", SERVICE_URL + url);

        if (response instanceof Fragment) {
            context = ((Fragment) response).getActivity();
        } else {
            context = (Context) response;
        }

        final Context finalContext = context;
        AbHttpUtil.getInstance(context).get(SERVICE_URL + url, params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {

//                        content = AbBase64.decode(content, "UTF-8");
                        Log.e("接口返回的数据=", content);
                        try {
                            JSONObject jo = new JSONObject(content);
                            response.onMessageResponse(url, jo);
                        } catch (JSONException e) {
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(content);
                                response.onMessageResponse(url, jsonArray);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                                try {
                                    response.onMessageResponse(url, jsonArray);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }

                        }
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        try {
                            AbLogUtil.e("statusCode==", statusCode + "");
                            AbLogUtil.e("content==", content + "");
                            AbLogUtil.e("error==", error + "");
                            if (statusCode == 600) {
                                AbToastUtil.showToast(context, content);
                            } else if (statusCode == 900) {
                                AbToastUtil.showToast(context, "连接服务器失败，请检查服务器是否正常.");
                            }
                            response.onMessageResponse(url, "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

        );
    }
}
