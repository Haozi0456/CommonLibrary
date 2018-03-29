package com.zwh.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

<<<<<<< HEAD:app/src/main/java/com/zwh/demo/ui/SplashActivity.java
import com.zwh.demo.MainActivity;
import com.zwh.demo.R;
import com.zwh.demo.ui.login.activity.LoginActivity;
=======
>>>>>>> 60d9bddb2cc9ed6858227f99075e671fa73dc08f:app/src/main/java/com/zwh/demo/SplashActivity.java

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
<<<<<<< HEAD:app/src/main/java/com/zwh/demo/ui/SplashActivity.java
                Intent mainIntemt = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntemt);
                SplashActivity.this.finish();
=======
//                Intent mainIntemt = new Intent(SplashActivity.this,LoginActivity.class);
//                SplashActivity.this.startActivity(mainIntemt);
//                SplashActivity.this.finish();
>>>>>>> 60d9bddb2cc9ed6858227f99075e671fa73dc08f:app/src/main/java/com/zwh/demo/SplashActivity.java
                //实现淡入浅出的效果
//                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }

        },2000);
    }
}
