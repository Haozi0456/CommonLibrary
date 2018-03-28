package com.zwh.demo.api;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.zwh.common.callback.JsonCallback;
import com.zwh.demo.app.GApp;

import java.util.List;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/10/21 12:59
 */

public class ApiService {

//    public static void getNewsByStartPage(int startPage,NewsCallback dataCallBack) {
//        String url ="http://c.m.163.com/nc/article/headline/T1348647909107/"+startPage+"-10.html";
//        OkGo.get(url)
//            .tag(GApp.getAppContext())                       // 请求的 tag, 主要用于取消对应的请求
//            .cacheKey("getNewsByStartPage")
//            .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//缓存模式
//            .execute(dataCallBack);
//    }
//
    public static void getPhotosPage(int startPage,JsonCallback dataCallBack) {
        //http://gank.io/api/data/福利/20/1
        String url ="http://gank.io/api/data/福利/20/"+startPage;
        OkGo.get(url)
            .tag(GApp.getAppContext())                       // 请求的 tag, 主要用于取消对应的请求
            .cacheKey("getPhotosPage")
            .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//缓存模式
            .execute(dataCallBack);
    }
//
//    public static void getAllDataList(HttpCallBack dataCallBack) {
//        String url ="http://221.234.44.121:8190/CommonService.asmx/GetCRSListAll";
//        OkGo.get(url)
//            .tag(GApp.getAppContext())                       // 请求的 tag, 主要用于取消对应的请求
//            .cacheKey("getAllDataList")
//            .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//缓存模式
//            .execute(dataCallBack);
//    }
//
//    public static void getNewsByPage(int pageNo,HttpCallBack dataCallBack) {
//        OkGo.post(URLs.QUERY_BY_PAGE)
//                .tag(GApp.getAppContext())                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("getNewsByPage")
//                .params("page", pageNo)//参数
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//缓存模式
//                .execute(dataCallBack);
//    }

    /**
     * 取消请求
     * @param context
     */
    public static void distory(Context context){
        OkGo.getInstance().cancelTag(context);
    }

}
