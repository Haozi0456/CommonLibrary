package com.zwh.demo.ui.photo.bean;

import java.io.Serializable;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/12/08 15:11
 */

public class PhotoBean implements Serializable{

    /**
     * _id : 5823bc03421aa91369f95a1d
     * createdAt : 2016-11-10T08:14:59.395Z
     * desc : 11-10
     * publishedAt : 2016-11-10T11:40:42.4Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg
     * used : true
     * who : daimajia
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
