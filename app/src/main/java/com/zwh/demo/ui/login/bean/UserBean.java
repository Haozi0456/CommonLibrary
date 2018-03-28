package com.zwh.demo.ui.login.bean;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/12/21 11:12
 */

public class UserBean {


    /**
     * ret : 0
     * msg :
     * is_lost : 0
     * nickname : 耗子
     * gender : 男
     * province : 湖北
     * city : 武汉
     * figureurl : http://qzapp.qlogo.cn/qzapp/1105857588/5E548ECD29749B9A4DC8B40CF6F36406/30
     * figureurl_1 : http://qzapp.qlogo.cn/qzapp/1105857588/5E548ECD29749B9A4DC8B40CF6F36406/50
     * figureurl_2 : http://qzapp.qlogo.cn/qzapp/1105857588/5E548ECD29749B9A4DC8B40CF6F36406/100
     * figureurl_qq_1 : http://q.qlogo.cn/qqapp/1105857588/5E548ECD29749B9A4DC8B40CF6F36406/40
     * figureurl_qq_2 : http://q.qlogo.cn/qqapp/1105857588/5E548ECD29749B9A4DC8B40CF6F36406/100
     * is_yellow_vip : 0
     * vip : 0
     * yellow_vip_level : 0
     * level : 0
     * is_yellow_year_vip : 0
     */

    private String nickname;
    private String gender;
//    private String province;
//    private String city;
    private String headImgurl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgurl() {
        return headImgurl;
    }

    public void setHeadImgurl(String headImgurl) {
        this.headImgurl = headImgurl;
    }

//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
}
