package com.zwh.demo.ui.demo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zwh.common.base.BaseAppFragment;
import com.zwh.common.tools.divideItemDecoration.DividerItemDecoration;
import com.zwh.common.widget.NormalTitleBar;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;
import com.zwh.demo.ui.demo.activity.RefreshLayoutDemoActivity;
import com.zwh.demo.ui.demo.adapter.CustomListAdapter;
import com.zwh.demo.ui.demo.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DemoListFragment extends BaseAppFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;
//    private Context context;
    private CustomListAdapter adapter;
    private View loadingView;
    private List<TestBean> demoList;

    public DemoListFragment() {

    }

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        this.context = getActivity().getApplicationContext();
//        ButterKnife.bind(this, view);
//        initView();
//        initData();
//        //开启loading,获取数据
////        setRefreshing(true);
////        refreshLayout.post(new Runnable() {
////            @Override
////            public void run() {
////                refreshLayout.setRefreshing(true);
////                onRefresh();
////            }
////        });
//        return view;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {
        demoList = new ArrayList<>();
        demoList.add(new TestBean("照片选择"));
        demoList.add(new TestBean("下拉刷新上拉加载更多"));
        demoList.add(new TestBean("GreenDao功能测试"));
        adapter.setNewData(demoList);
    }

    private void initView() {
        titleBar.setTvLeftVisiable(false);
        titleBar.setTitleText("示例列表");

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //加载页面
        loadingView = LayoutInflater.from(context).inflate(R.layout.loading_view,(ViewGroup) recyclerView.getParent(), false);

        adapter = new CustomListAdapter(R.layout.item_list_layout, null);
        adapter.setEnableLoadMore(false);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(true);
        adapter.setEmptyView(loadingView);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
               gotoDemo(position);
            }
        });

    }

    private void gotoDemo(int position) {
        Intent intent = null;
        switch (position) {
            case 0://图片选择
//                intent = new Intent(context, ImageSelectorDemoActivity.class);
                break;
            case 1://下拉刷新
                intent = new Intent(context, RefreshLayoutDemoActivity.class);
                break;
            case 2://下拉刷新

                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ApiService.distory(context);
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        setRefreshing(false);
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
