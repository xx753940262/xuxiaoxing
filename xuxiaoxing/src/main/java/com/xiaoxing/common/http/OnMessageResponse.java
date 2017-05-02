package com.xiaoxing.common.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuxing on 2015/12/9.
 */
public interface OnMessageResponse {
    //处理JSONObject数据
    void onMessageResponse(String url, JSONObject jo) throws JSONException;

    //处理JSONArray数据
    void onMessageResponse(String url, JSONArray jo) throws JSONException;

    //处理失败时的数据
    void onMessageResponse(String url, String str) throws Exception;
}
