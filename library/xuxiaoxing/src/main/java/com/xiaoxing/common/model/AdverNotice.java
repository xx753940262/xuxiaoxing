package com.xiaoxing.common.model;

/**
 * 描述：
 * 作者：徐小星 on 2016/9/9 0009 13:33
 * 邮箱：xx@yougudongli.com
 */
public class AdverNotice {
    public String title;
    public String url;
    public String time;
    public String description;

    public AdverNotice(String title, String url,String time,String description) {
        this.title = title;
        this.url = url;
        this.time = time;
        this.description = description;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
