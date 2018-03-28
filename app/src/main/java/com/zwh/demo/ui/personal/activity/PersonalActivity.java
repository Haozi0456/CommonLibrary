package com.zwh.demo.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zwh.common.tools.glideModule.ImageCatchUtil;
import com.zwh.common.widget.NormalTitleBar;
import com.zwh.demo.R;
import com.zwh.demo.app.GApp;
import com.zwh.demo.base.BaseActivity;
import com.zwh.demo.ui.login.bean.UserBean;
import com.zwh.demo.widget.AlertDialogView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Zhaohao
 * @Description: 个人中主界面
 * @Date 2016年11月22日 17:35:31
 */
public class PersonalActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.loginIcon)
    ImageView loginIcon;
    @BindView(R.id.facoriteView)
    RelativeLayout facoriteView;
    @BindView(R.id.settingView)
    RelativeLayout settingView;
    @BindView(R.id.aboutView)
    RelativeLayout aboutView;
    @BindView(R.id.loginName)
    TextView loginName;
    @BindView(R.id.cacheText)
    TextView cacheText;
    @BindView(R.id.clearnCacheView)
    RelativeLayout clearnCacheView;
    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;

    private Handler handler;
    private Context context;

    private ImageCatchUtil catchUtil;

    // 选择图片
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal);
        this.context = this;
        ButterKnife.bind(this);
        initUI();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        showMsg("缓存清理完成");
                        cacheText.setText("0KB");
                        break;
                }
            }
        };
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {

    }


    private void initUI() {

        titleBar.setTitleText("关于");
        titleBar.setOnBackListener(this);

        if(GApp.userBean != null){
            UserBean bean = GApp.userBean;
            loginName.setText(bean.getNickname());

            RequestOptions options = new RequestOptions()
                    .centerCrop();

            //初始化头像
            Glide.with(context).asBitmap().load(bean.getHeadImgurl()).apply(options).into(new BitmapImageViewTarget(loginIcon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    loginIcon.setImageDrawable(circularBitmapDrawable);
                }

                @Override
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_user_default);
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
                    circularBitmapDrawable.setCircular(true);
                    loginIcon.setImageDrawable(circularBitmapDrawable);
                }
            });

        }else{
            //初始化头像
            path = context.getExternalFilesDir(null).getPath() + "/head/";
            String headImgUrl = path + "head.jpg";

            File file = new File(headImgUrl);
            Bitmap bitmap = null;
            if (!file.exists()) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_default);
            } else {
                bitmap = BitmapFactory.decodeFile(headImgUrl);
            }
            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
            circularBitmapDrawable.setCircular(true);
            loginIcon.setImageDrawable(circularBitmapDrawable);
        }


        //获取缓存大小
        catchUtil = ImageCatchUtil.getInstance(context);
        cacheText.setText(catchUtil.getCacheFormatSize());

//        Glide.with(context).load(headImgUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(loginIcon) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                loginIcon.setImageDrawable(circularBitmapDrawable);
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                super.onLoadFailed(e, errorDrawable);
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_user_default);
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
//                circularBitmapDrawable.setCircular(true);
//                loginIcon.setImageDrawable(circularBitmapDrawable);
//            }
//        });

    }

    @OnClick({R.id.loginIcon, R.id.facoriteView, R.id.settingView, R.id.aboutView, R.id.clearnCacheView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionBack:
                finish();
                break;
//            case R.id.loginIcon: //修改个人资料
//                Intent intent = new Intent(context, PersonalInfoActivity.class);
//                startActivityForResult(intent, 1);
//                break;
            case R.id.facoriteView:
                showMsg("敬请期待!");
                break;
            case R.id.settingView:
                showMsg("敬请期待!");
                break;
            case R.id.aboutView:
                Intent aboutIntent = new Intent(context, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.clearnCacheView: //清除缓存
                String cacheTip = "当前缓存" + catchUtil.getCacheSize();
                AlertDialogView dialogView = AlertDialogView.getInstance(context);
                dialogView.show("清除缓存", cacheTip, "确定清除", "暂不清除", new AlertDialogView.ChooseOptionCallBack() {
                    @Override
                    public void chooseOption(int type) {
                        if (type == 1) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    catchUtil.clearImageDiskCache();
                                    handler.sendEmptyMessage(1);
                                }
                            }).start();
                        }
                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                initUI();
                break;
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

}
