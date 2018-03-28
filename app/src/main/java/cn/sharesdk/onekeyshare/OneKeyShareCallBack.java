package cn.sharesdk.onekeyshare;


import android.os.Handler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * @author Zhaohao
 * @Description: 分享状态返回
 * @Date 2016/12/06 19:21
 */

public class OneKeyShareCallBack implements PlatformActionListener {

    private Handler handler;

    public OneKeyShareCallBack(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if(handler != null){
            handler.sendEmptyMessage(0);
        }

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if(handler != null){
            handler.sendEmptyMessage(1);
        }

    }

    @Override
    public void onCancel(Platform platform, int i) {
        if(handler != null){
            handler.sendEmptyMessage(2);
        }

    }
}
