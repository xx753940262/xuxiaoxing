package com.xiaoxing.common.view.x_rich_text;

import android.graphics.Bitmap;

import java.io.IOException;



public interface ImageLoader {
    Bitmap getBitmap(String url) throws IOException;
}
