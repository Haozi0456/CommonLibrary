package com.zwh.demo.ui.home.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zwh.common.widget.CommentDialog;
import com.zwh.common.widget.NormalTitleBar;
import com.zwh.demo.R;
import com.zwh.demo.ui.home.adapter.CommentListAdapter;
import com.zwh.demo.ui.home.bean.CommentBean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class CommentListActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toCommentView)
    TextView toCommentView;
    @BindView(R.id.toDetailButton)
    Button toDetailButton;
    @BindView(R.id.collectBox)
    CheckBox collectBox;
    @BindView(R.id.shareButton)
    ImageButton shareButton;
    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;

    private Context context;
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        this.context = this;
        ButterKnife.bind(this);
        initView();
        refreshLayout.setRefreshing(true);
        onRefresh();
    }


    private void initView() {
        titleBar.setTitleText("全部评论");
        titleBar.setOnBackListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        adapter = new CommentListAdapter(R.layout.view_comment_list_item, null);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.isFirstOnly(true);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

//                showToast("点击了 " + i + "dd");
//                NewsBean newsBean =  adapter.getItem(i);
//                if(newsBean != null){
//                    Intent intent = new Intent(context, NewsDetailsActivity.class);
//                    intent.putExtra("newsbean",newsBean);
//                    startActivity(intent);
//                }
            }

        });

    }


    @OnClick({R.id.toCommentView, R.id.toDetailButton, R.id.shareButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionBack:
                finish();
                break;
            case R.id.toCommentView:
                showCommentView();
                break;
            case R.id.toDetailButton:
                finish();
                break;
            case R.id.shareButton:
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

        //分享结果处理返回
//        oks.setCallback(new OneKeyShareCallBack(handler));

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onRefresh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<CommentBean> beanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CommentBean bean = new CommentBean();
            bean.name = "zhangsan" + i;
            bean.conext = "纵死侠骨香，不惭世上英。谁能书阁下，白首太玄经。";
            bean.dateTime = sdf.format(new Date());
            bean.headUrl = "http://v1.qzone.cc/avatar/201407/19/14/53/53ca15e92008c204.jpg%21200x200.jpg";
            beanList.add(bean);
        }
        adapter.setNewData(beanList);
    }

    @Override
    public void onLoadMoreRequested() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<CommentBean> beanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CommentBean bean = new CommentBean();
            bean.name = "zhangsan" + i;
            bean.conext = "纵死侠骨香，不惭世上英。谁能书阁下，白首太玄经。";
            bean.dateTime = sdf.format(new Date());
            bean.headUrl = "http://v1.qzone.cc/avatar/201407/19/14/53/53ca15e92008c204.jpg%21200x200.jpg";
            beanList.add(bean);
        }
        adapter.addData(beanList);
    }


}
