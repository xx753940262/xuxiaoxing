package com.xiaoxing.module.recycler.swipe.model;

/**
 * 描述：
 * 作者：xiaoxing on 17/5/5 11:37
 * 邮箱：2235445233@qq.com
 */
public class ListItemInfo {

    private String title;
    private String subTitle;

    public ListItemInfo() {
    }

    public ListItemInfo(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
