package com.zwh.demo.widget;

import android.widget.Toast;

import com.zwh.demo.app.GApp;


/**
 * @author Zhaohao
 * @Description: 提示工具类
 * @Date 2016/12/06 19:12
 */

public class ToastUtil {

    public static void showToast(String msg){
        Toast.makeText(GApp.getAppContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
