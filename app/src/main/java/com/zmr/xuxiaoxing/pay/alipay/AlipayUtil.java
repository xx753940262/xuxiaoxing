package com.zmr.xuxiaoxing.pay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.socks.library.KLog;
import com.xiaoxing.common.util.SharedPreferencesHelper;
import com.zmr.xuxiaoxing.pay.alipay.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/22 15:04
 * 邮箱：2235445233@qq.com
 */
public class AlipayUtil {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016051901420381";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMWHI/sKNDSxzjav\n" +
            "FKfi+05VHSabw5ZrLzn0ce0csilMf2/AesiGTLLlQ0sheUzUqughcHm8IiK01xQr\n" +
            "hn/3rB8GtcW/SuS4qU17vhwe3oiobFMorvAl1AEgvssjug7IkneZXbSTLG0zQBBE\n" +
            "EuFiwYlZtWYjiizTVISpDAlVOJjBAgMBAAECgYAFF/kLjjXObg9dRaKIuce1O2in\n" +
            "CDlA9b6gBfeyH51Qh1J59UK1TRFJQ7Q6icBxBMLrfYFDAmD2+CbbtEuhnSdpEbzP\n" +
            "B4P9lUnANEmFoOv8IgOBo/JPoNv+7us03GhPiOC4bkafkel8U8Wf6UIkcW+f0An1\n" +
            "VePLJFVCcneb7/V+AQJBAPwZeX51o5aZqz/SX57N4wVNFAqMV6laF4LPcnzNxD+r\n" +
            "ICPWHxabKY2Qa+QiwPC55mhiSS4W9joTZDIavJaIUrECQQDIlYRAYCVewPD/Xt+T\n" +
            "g079wuWGXDjggm1D72ExgCTJsrtI1cVF3/9KgLtEDsVRmmHbi9xznN2CeMDFZnvE\n" +
            "1YsRAkEAkHkw7U4RoB8UyxCPB/1J2yHfGGRxYHZm/upP3EI385zoYGE3k8b8O0rG\n" +
            "6gkYtVx0NOIOukEifwnZ4/T6mvIpIQJBAIGeDN5EwyFd8N41AqkiWzMkdInEooSC\n" +
            "A/W4hMgmFJ+6PcgtS9OBkkkkst6h5OR8rOvgj06ueX3MZlWpiiWCEOECQGgQ6Hh7\n" +
            "TooOaydLjxFcy/dFYwkbRqTXMI8imEyKx2Alm0yCoVPheXO6xDrguqdb+mKtRDil\n" +
            "KX2tbAwz4nX76u0=";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                        toSuccessTip();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = mActivity.getIntent();
                    Bundle bundle = new Bundle();
                    intent.putExtra("data", bundle);
                    Intent mIntent = new Intent();
                    // 设置结果，并进行传送
//                mOperation.startActivityForResult(bundle, 100, QueRenDingDanActivity.class);
                    //设置当前Activity的结果码
                    mActivity.setResult(200, intent);
                    mActivity.finish();
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(mActivity,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mActivity,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    private static Activity mActivity;
    private static String mPrice, mGoodsName, mPayLogId;
    private SharedPreferencesHelper sHelper;

    public AlipayUtil(Activity activity, String price, String goodsName, String payLogId, SharedPreferencesHelper sharedPreferencesHelper) {
        this.mActivity = activity;
        this.mPrice = price;
        this.mGoodsName = goodsName;
        this.mPayLogId = payLogId;
        this.sHelper = sharedPreferencesHelper;
    }

    /**
     * 支付宝支付业务
     *
     * @param
     */
    public void alipay() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(mActivity).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            mActivity.finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, mPrice, mGoodsName);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        KLog.e(orderParam.toString());
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务
     *
     * @param
     */
    public void authV2() {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(mActivity).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(mActivity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(mActivity);
        String version = payTask.getVersion();
        Toast.makeText(mActivity, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(mActivity, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        mActivity.startActivity(intent);
    }

    private void toSuccessTip() {
        Intent intent = new Intent(mActivity, PaySuccessActivity.class);
        mActivity.startActivity(intent);
    }
}
