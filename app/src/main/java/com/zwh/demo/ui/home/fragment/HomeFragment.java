package com.zwh.demo.ui.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zwh.common.base.BaseAppFragment;
import com.zwh.common.tools.divideItemDecoration.DividerItemDecoration;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;
import com.zwh.demo.ui.home.activity.BusinessDetailsActivity;
import com.zwh.demo.ui.home.adapter.NewsListAdapter;
import com.zwh.demo.ui.home.bean.NewsBean;

import butterknife.BindView;


public class HomeFragment extends BaseAppFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private NewsListAdapter newsAdapter;
    private int page = 0;
    public boolean isInitCache = false;



    public HomeFragment() {

    }


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
//
//        return view;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setTitle("首页");
        isShowBack(false);
        initView();
        //开启loading,获取数据
//        setRefreshing(true);
        refreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        newsAdapter = new NewsListAdapter(R.layout.layout_news_item, null);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        newsAdapter.setOnLoadMoreListener(this);
        newsAdapter.setEnableLoadMore(true);
        newsAdapter.isFirstOnly(true);

        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsBean newsBean =  newsAdapter.getItem(position);
                if(newsBean != null){
                    Intent intent = new Intent(context, BusinessDetailsActivity.class);
                    intent.putExtra("newsbean",newsBean);
                    startActivity(intent);
                }
            }
        });

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
        newsAdapter.setEnableLoadMore(false);
        page = 0;

    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);

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
