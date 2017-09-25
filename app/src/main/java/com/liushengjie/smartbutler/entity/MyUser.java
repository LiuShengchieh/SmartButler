package com.liushengjie.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.entity
 * 文件名：MyUser
 * Created by liushengjie on 2017/9/25.
 * 描述：      用户属性
 */

public class MyUser extends BmobUser {

    private int age;
    private boolean sex;
    private String desc;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }






}
