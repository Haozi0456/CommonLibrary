package com.zwh.demo.ui.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zwh.common.base.BaseAppActivity;
import com.zwh.common.tools.divideItemDecoration.DividerItemDecoration;
import com.zwh.demo.R;
import com.zwh.demo.ui.demo.adapter.CustomListAdapter;
import com.zwh.demo.ui.demo.bean.TestBean;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


public class RefreshLayoutDemoActivity extends BaseAppActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private CustomListAdapter adapter;
    private List<TestBean> listData = null;
    private Handler handler;
    private View loadingView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_layout_demo;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setTitle("下拉刷新");
        setBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initRefreshLayout();
        initUI();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        // 在这里加载最新数据
                        listData.clear();
                        for (int i = 0; i < 30; i++) {
                            listData.add(new TestBean("数据:" + i));
                        }
                        adapter.setNewData(listData);
                        setRefreshing(false);
                        break;
                    case 2:
                        // 在这里加载更多数据，或者更具产品需求实现上拉刷新也可以
                        for (int i = 0; i < 30; i++) {
                            adapter.addData(new TestBean("数据:" + i));
                        }
                        //加载完成
                        adapter.loadMoreComplete();

//                        //加载失败
//                        adapter.loadMoreFail();
//                        //没有更多数据
//                        adapter.loadMoreEnd();
//                        //打开或关闭加载
//                        adapter.setEnableLoadMore(boolean);

                        break;

                }
            }
        };


        setRefreshing(true);
        onRefresh();
    }

    private void initUI() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.loading_view,null);
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        listData = new ArrayList<>();

        adapter = new CustomListAdapter(R.layout.item_list_layout, listData);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setEmptyView(loadingView);
        adapter.setEnableLoadMore(true);
        adapter.isFirstOnly(true);
        adapter.setOnLoadMoreListener(this,recyclerView);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//        adapter.setAutoLoadMoreSize(int);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


    }

    private void initRefreshLayout() {

    }


    @Override
    public void onLoadMoreRequested() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(2, 2000);

            }
        }).start();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
