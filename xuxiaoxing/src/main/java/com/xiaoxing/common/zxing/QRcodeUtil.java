package com.xiaoxing.common.zxing;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xiaoxing.common.zxing.encoding.EncodingUtils;

/**
 * 作者：徐小星 on 2016/11/16 11:19
 * 邮箱：xx@yougudongli.com
 */
public class QRcodeUtil {
    /**
     * 生成二维码
     *
     * @param input
     * @param resources
     * @param logo
     * @return
     */
    public static Bitmap createQRcode(String input, Resources resources, int logo) {
        Bitmap qrCode = EncodingUtils.createQRCode(input, 500, 500,
                BitmapFactory.decodeResource(resources, logo));
        return qrCode;
    }
}
