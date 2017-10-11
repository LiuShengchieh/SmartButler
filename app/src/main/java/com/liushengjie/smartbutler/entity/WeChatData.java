package com.liushengjie.smartbutler.entity;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.entity
 * 文件名：WeChatData
 * Created by liushengjie on 2017/10/11.
 * 描述：微信精选的数据类
 */

public class WeChatData {

    //标题
    private String title;

    //出处
    private String source;

    //图片Url
    private String imgUrl;

    //内容Url
    private String newsUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
