package com.xiaoxing.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 描述：app中跳转到qq
 * 作者：徐小星 on 2016/12/10 22:10
 * 邮箱：xx@youdugongli.com
 */
public class QQUtil {

    public static void openQQ(Context context, String qq) {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "";
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
