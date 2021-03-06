package com.zwh.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.zwh.demo.MainActivity;
import com.zwh.demo.R;
import com.zwh.demo.ui.login.activity.LoginActivity;


/**
 * @Description:  启动界面
 * @author Zhaohao
 * @Date 2016/11/24 11:44
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntemt = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntemt);
                SplashActivity.this.finish();
//                Intent mainIntemt = new Intent(SplashActivity.this,LoginActivity.class);
//                SplashActivity.this.startActivity(mainIntemt);
//                SplashActivity.this.finish();
                //实现淡入浅出的效果
//                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }

        },2000);
    }
}
