package com.liushengjie.smartbutler.entity;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.entity
 * 文件名：ChatListData
 * Created by liushengjie on 2017/10/8.
 * 描述：对话列表实体
 */

public class ChatListData {

    //type
    private int type;
    //文本
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
