package com.ab.util.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.ab.R;
import com.ab.util.AbLogUtil;

import java.util.logging.Logger;

/**
 * 描述：
 * 作者：徐小星 on 2016/8/8 0008 16:04
 * 邮箱：xx@yougudongli.com
 */
public class Web {
    private WebView mWebView;
    private LinearLayout mLoadingLayout;

    public Web(WebView webView, LinearLayout ll_loading_dialog) {
        super();
        this.mWebView = webView;
        this.mLoadingLayout = ll_loading_dialog;
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
