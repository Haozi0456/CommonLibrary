package com.zwh.demo.app;

import com.zwh.common.app.BaseApplication;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2017/03/13 16:30
 */

public class GAPP extends BaseApplication{




    public static GAPP app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static GAPP getInstance(){
        return app;
    }



}
