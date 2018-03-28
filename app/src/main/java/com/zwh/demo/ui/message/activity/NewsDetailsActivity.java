package com.zwh.demo.ui.message.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zwh.common.widget.CommentDialog;
import com.zwh.demo.R;
import com.zwh.demo.app.GApp;
import com.zwh.demo.ui.home.activity.CommentListActivity;
import com.zwh.demo.ui.message.bean.NewsBean;
import com.zwh.demo.widget.ToastUtil;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OneKeyShareCallBack;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * @author Zhaohao
 * @Description: 详情页面样式
 * @Date 2016/12/05 14:29
 */
public class NewsDetailsActivity extends AppCompatActivity {
    private String TAG = "详情分享";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout toolbarlayout;
    @BindView(R.id.detailImg)
    ImageView detailImg;
    @BindView(R.id.detailText)
    TextView detailText;
    @BindView(R.id.toCommentView)
    TextView toCommentView;

    @BindView(R.id.shareButton)
    ImageButton shareButton;
    @BindView(R.id.toCommentListBox)
    CheckBox toCommentListBox;
    @BindView(R.id.collectBox)
    CheckBox collectBox;

    private Context context;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        this.context = this;
        ButterKnife.bind(this);
        initToolbar();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        ToastUtil.showToast("成功!");
                        break;
                    case 1:
                        ToastUtil.showToast("错误!");
                        break;
                    case 2:
                        ToastUtil.showToast("取消!");
                        break;
                }
            }
        };
    }

    private void initData() {
        NewsBean bean = (NewsBean) getIntent().getSerializableExtra("bean");
        toolbarlayout.setTitle(bean.getTitle());
//        Glide.with(GApp.getAppContext()).load(bean.getIcon()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.icon_preload).crossFade().into(detailImg);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_preload)
                .error(R.drawable.icon_preload);
        Glide.with(GApp.getAppContext()).load(bean.getIcon()).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(detailImg);
        detailText.setText(bean.getDes());

    }

    private void initToolbar() {
//        toolbar.setNavigationIcon(R.drawable.back_button_style);
//        toolbar.setTitle("标题");
        setSupportActionBar(toolbar);
        //显示返回按钮图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//        if (menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
//                }
//            }
//        }
//        return super.onPrepareOptionsPanel(view, menu);
//    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged({R.id.toCommentListBox, R.id.collectBox})
    public void onCheckedChanged(CheckBox checkBox,boolean isChecked) {
        switch (checkBox.getId()) {
            case R.id.toCommentListBox: //评论列表页面
                Intent intent = new Intent(context,CommentListActivity.class);
                startActivity(intent);
                break;
            case R.id.collectBox:
                if(isChecked){
                    ToastUtil.showToast("收藏成功!");
                }else{
                    ToastUtil.showToast("取消收藏!");
                }
                break;
        }
    }

    @OnClick({R.id.toCommentView, R.id.shareButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toCommentView://打开评论界面
                showCommentView();
                break;
            case R.id.shareButton: //打开分享
                showShare();
                break;
        }
    }

    private void showCommentView() {
        CommentDialog commentDialog = new CommentDialog(this);
        commentDialog.show();
        commentDialog.setOnSendListener(new CommentDialog.OnSendListener() {
            @Override
            public void sendComment(String content) {

            }
        });
    }


    /**
     * 分享
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        oks.setCallback(new OneKeyShareCallBack(handler));

        // 启动分享GUI
        oks.show(this);
    }


//    public class OneKeyShareCallBack implements PlatformActionListener {
//
//
//
//        @Override
//        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//            Log.d(TAG, "onComplete: "+i);
//            handler.sendEmptyMessage(0);
//        }
//
//        @Override
//        public void onError(Platform platform, int i, Throwable throwable) {
//            Log.d(TAG, "onError: "+i);
//            handler.sendEmptyMessage(1);
//        }
//
//        @Override
//        public void onCancel(Platform platform, int i) {
//            Log.d(TAG, "onCancel: "+i);
//            handler.sendEmptyMessage(2);
//        }
//    }

}
