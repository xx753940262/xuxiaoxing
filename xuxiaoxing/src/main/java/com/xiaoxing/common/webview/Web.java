package com.xiaoxing.common.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoxing.common.util.AbLogUtil;
import com.xiaoxing.common.util.ToastUtil;

/**
 * 描述：
 * 作者：徐小星 on 2016/8/8 0008 16:04
 * 邮箱：xx@yougudongli.com
 */
public class Web {
    private WebView mWebView;
    private View mLoadingLayout;
    private Context mContext;

    long firstTime = 0;

    public Web(Context context, WebView webView, View loading_dialog) {
        super();
        this.mContext = context;
        this.mWebView = webView;
        this.mLoadingLayout = loading_dialog;
        initWebView();
    }


    /**
     * 加载详情
     */

    private void initWebView() {
        mWebView.setBackgroundColor(0);
        WebSettings webSettings = mWebView.getSettings();
        mWebView.requestFocus();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setFocusable(true);
        webSettings.setBuiltInZoomControls(true);

        mWebView.setScrollBarStyle(0);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                AbLogUtil.e("-----web------", newProgress + "");
                if (newProgress >= 100) {
                    mWebView.loadUrl(method);
                    AbLogUtil.e("-----web load finish ------", newProgress + "");
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setMessage(message).setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        result.confirm();
                    }
                });
                builder.setOnKeyListener(new OnKeyListener() {
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }

        });

        mWebView.setWebViewClient(new WebViewClient() {
            // 重写父类方法，让新打开的网页在当前的WebView中显示
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(view, url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mLoadingLayout.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mLoadingLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });

        //监听返回事件
        mWebView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {  //表示按返回键时的操作
                        mWebView.goBack();   //后退
                        return true;    //已处理
                    }
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        long secondTime = System.currentTimeMillis();
                        if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于1000毫秒，则不退出
                            ToastUtil.showMessage(mContext, "再按一次退出程序");
                            firstTime = secondTime;//更新firstTime
                            return true;
                        } else {
                            System.exit(0);//否则退出程序
                        }
                    }
                }
                return false;
            }
        });

    }

    public void loadUrl(String url) {
        loadUrl(mWebView, url);
    }

    private void loadUrl(final WebView webView, final String url) {
        webView.loadUrl(url);
    }

    /**
     * 加载方法，网页加载完毕时加载方法，防止加载不到方法
     *
     * @param method
     */
    public void loadMethod(String method) {
        AbLogUtil.e("-----web load method ------", method + "");
        this.method = method;
    }

    private String method = null;
}
