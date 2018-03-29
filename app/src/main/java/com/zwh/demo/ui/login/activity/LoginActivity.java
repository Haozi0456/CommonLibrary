package com.zwh.demo.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zwh.common.widget.ClearnEditText;
import com.zwh.common.widget.PasswordEditText;
import com.zwh.demo.MainActivity;
import com.zwh.demo.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.platform.Platform;


/**
 * @author Zhaohao
 * @Description: 登录界面
 * @Date 2016/11/30 10:40
 */
public class LoginActivity extends AppCompatActivity {

    /*@BindView(R.id.loginLogoImg)
    ImageView loginLogoImg;*/
    @BindView(R.id.userNameText)
    ClearnEditText userNameText;
    @BindView(R.id.passwordText)
    PasswordEditText passwordText;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginQQ)
    Button loginQQ;
    @BindView(R.id.loginWX)
    Button loginWX;
    @BindView(R.id.loginWB)
    Button loginWB;
    @BindView(R.id.forgetButton)
    TextView forgetButton;
    @BindView(R.id.registButton)
    TextView registButton;

    private Context context;
    private String TAG = "登录模块:";
    private Handler handler;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        ButterKnife.bind(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        showMsg("成功!");
                        break;
                    case 1:
                        showMsg("错误!");
                        break;
                    case 2:
                        showMsg("取消!");
                        break;
                }
            }
        };

        initUI();
    }

    private void initUI() {
//        passwordText.setDrawableRightListener(new XEditText.DrawableRightListener() {
//            @Override
//            public void onDrawableRightClick(View view) {
//                Drawable leftDrawable = getResources().getDrawable(R.drawable.icon_password);
//                if(!isPasswordVisible){
//                    Drawable rightDrawable = getResources().getDrawable(R.drawable.icon_password_eyes_invisible);
//                    passwordText.setCompoundDrawables(leftDrawable,null,rightDrawable,null);
//                    passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    isPasswordVisible = true;
//                }else{
//                    Drawable rightDrawable = getResources().getDrawable(R.drawable.icon_password_eyes_visible);
//                    passwordText.setCompoundDrawables(leftDrawable,null,rightDrawable,null);
//                    passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    isPasswordVisible = false;
//                }
//            }
//        });
    }

    @OnClick({R.id.loginButton, R.id.loginQQ, R.id.loginWX, R.id.loginWB,R.id.forgetButton, R.id.registButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK); //退出防止重启
                startActivity(intent);
                break;
//            case R.id.loginQQ:
////                showShare();
//                loginThirdMethod(QQ.NAME);
//                break;
//            case R.id.loginWX:
//                loginThirdMethod(Wechat.NAME);
//                break;
//            case R.id.loginWB:
//                loginThirdMethod(SinaWeibo.NAME);
//                break;
            case R.id.forgetButton:
                break;
            case R.id.registButton:
                Intent registIntent = new Intent(context, RegistActivity.class);
                startActivity(registIntent);
                break;
        }
    }


//    private void loginThirdMethod(String type) {
//        ShareSDK.initSDK(this);
//        Platform weibo = ShareSDK.getPlatform(type);
////回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
//        weibo.setPlatformActionListener(new PlatformActionListener() {
//
//            @Override
//            public void onError(Platform arg0, int arg1, Throwable arg2) {
//                // TODO Auto-generated method stub
//                arg2.printStackTrace();
//            }
//
//            @Override
//            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//                // TODO Auto-generated method stub
//                //输出所有授权信息
//                arg0.getDb().exportData();
//            }
//
//            @Override
//            public void onCancel(Platform arg0, int arg1) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//        //authorize与showUser单独调用一个即可
//        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
//        weibo.showUser(null);//授权并获取用户信息
//        //移除授权
//        //weibo.removeAccount(true);
//    }


//    private void showShare() {
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//        oks.setCallback(new OneKeyShareCallBack(handler));
//
//        // 启动分享GUI
//        oks.show(this);
//    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ){
                android.os.Process.killProcess(android.os.Process.myPid());
//                finish();
//                System.exit(0);
        }
        return false;
    }
}
