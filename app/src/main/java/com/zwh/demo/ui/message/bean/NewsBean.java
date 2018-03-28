package com.zwh.demo.ui.message.bean;

import java.io.Serializable;

/**
 * @author Zhaohao
 * @Description: 网易新闻头条数据对象
 * @Date 2016/11/25 10:23
 */

public class NewsBean implements Serializable {


    /**
     * title : 苏州博物馆
     * time : 2016-10-20
     * version : V2.1.20161019
     * icon : /appicons/suzhoubowuguan.png
     * imgBg : /appbg/szbwg_bg.png
     * url : http://a.app.qq.com/o/simple.jsp?pkgtitle=com.dct.suzhou
     * des : 新版智能导览系统在原有苏州博物馆数字化移动应用所提供馆内展品、藏品的多媒体信息展示、查询等基本功能的基础上，基于GIS技术、WIFI室内定位技术、ibeacon定位技术，观众行为分析技术，结合智能导览设备中所包含的方向感应器、重力感应器、陀螺仪、加速度感应器、GPS等多种传感器，提供针对观众个体的个性化、智能化、具备多种互动方式的数字化展陈展示。
     */

    private String title;
    private String time;
    private String version;
    private String icon;
    private String imgBg;
    private String url;
    private String des;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImgBg() {
        return imgBg;
    }

    public void setImgBg(String imgBg) {
        this.imgBg = imgBg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
