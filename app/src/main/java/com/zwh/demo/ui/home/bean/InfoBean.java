package com.zwh.demo.ui.home.bean;

import java.io.Serializable;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/12/09 17:16
 */

public class InfoBean implements Serializable{


    /**
     * guid : 0481f5af09ce4d96b0230e76cd19a55a
     * name : 洪秀全故居
     * imgurl : http://221.234.44.121:8056/Thumb300/%e6%96%87%e7%89%a9%e5%8f%a4%e8%bf%b9%e7%85%a7%e7%89%87/s_201610261146054xr1vY.jpg
     * abstruct :        洪秀全故居位于花都区新华街官禄村。建于清嘉庆年间，咸丰四年（1854）被清军烧毁，1959年根据考古发掘，在原墙基上参照当地客家民居重建复原，1961年向游客开放。
     故居为一连6间平房，是当地最简朴的民宅建筑形式，叫横屋。坐北朝南，总面阔16.5米，总进深5.5米，建筑占地91平方米。悬山顶，人字山墙，盖瓦面，泥砖墙，灰砂石头墙基，泥地面。每间约13平方米，十五檩，没有窗户；门高2米，宽0.9米。
     右起第一间是洪秀全夫妇早年居住的房间，幼天王洪天贵福在此出生。复原后，门额上悬挂“洪秀全故居”横匾（1961年由郭沫若题写）。室内陈列床、桌椅和文房四宝。清道光二十五年至二十七年（1845—1847），洪秀全在此写下了《原道救世歌》、《原道醒世训》、《原道觉世训》等奠定太平天国理论基础的文献著作。
     1988年1月，国务院公布为全国重点文物保护单位。

     * location : 广州市花都区大布乡官禄布村
     * age : 清代末期
     * x : 113.172697
     * y : 23.417096
     * type : 近现代重要史迹及代表性建筑
     * mediadescribe :
     * viewcount : 122
     */

    private String guid;
    private String name;
    private String imgurl;
    private String abstruct;
    private String location;
    private String age;
    private double x;
    private double y;
    private String type;
    private String mediadescribe;
    private int viewcount;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAbstruct() {
        return abstruct;
    }

    public void setAbstruct(String abstruct) {
        this.abstruct = abstruct;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediadescribe() {
        return mediadescribe;
    }

    public void setMediadescribe(String mediadescribe) {
        this.mediadescribe = mediadescribe;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }
}
