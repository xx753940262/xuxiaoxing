package com.ab.model;

/**
 * 描述：
 * 作者：徐小星 on 2016/9/9 0009 13:33
 * 邮箱：xx@yougudongli.com
 */
public class AdverNotice {
    public String title;
    public String url;

    public AdverNotice(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
