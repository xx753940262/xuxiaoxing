package com.xiaoxing.common.update;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述：
 * 作者：徐小星 on 2016/8/12 0012 19:25
 * 邮箱：xx@yougudongli.com
 */
public class UpdateManager {

    private Context mContext;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    private static ProgressDialog mProgressDialog;

    public void downApk(final String url) {
        // 声明对话框是你activity的成员字段
        // 举例说明在onCreate中的方法
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("下载中...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCanceledOnTouchOutside(false);
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.execute(url);
    }

    // 通常，AsyncTask子类在activity类中进行声明
    // 这样，你可以很容易在这里修改UI线程
    private class DownloadFile extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... sUrl) {
            try {
                URL url = new URL(sUrl[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                //这将是有用的，这样你可以显示一个典型的0-100%的进度条
                int fileLength = connection.getContentLength();

                // 下载文件
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream("/sdcard/file_name.apk");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressDialog.dismiss();
            // 核心是下面几句代码
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File("/sdcard/file_name.apk")),
                    "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            mProgressDialog.setProgress(progress[0]);
        }
    }
}
