package com.xiaoxing.common.download.down02.listener;


import com.xiaoxing.common.download.down02.pojo.UpdateInfo;


public interface OnUpdateListener {
    /**
     * on start check
     */
    public void onStartCheck();

    /**
     * on finish check
     */
    public void onFinishCheck(UpdateInfo info);

    public void onStartDownload();
    
    public void onDownloading(int progress);
    
    public void onFinshDownload();

}
