package com.zwh.demo.ui.photo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zwh.common.callback.JsonCallback;
import com.zwh.common.tools.divideItemDecoration.SpacesItemDecoration;
import com.zwh.common.widget.NormalTitleBar;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;
import com.zwh.demo.ui.photo.activity.ImageBrowserActivity;
import com.zwh.demo.ui.photo.adapter.PhotoListAdapter;
import com.zwh.demo.ui.photo.bean.PhotoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

import static com.zwh.demo.widget.ToastUtil.showToast;


public class MessageFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private Context context;
    private PhotoListAdapter adapter;


    private int page = 1;
    public boolean isInitCache = false;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        this.context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);
        initView();
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
        return view;
    }

    private void initView() {
        titleBar.setTvLeftVisiable(false);
        titleBar.setTitleText("美图");

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SpacesItemDecoration decoration=new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        adapter = new PhotoListAdapter(R.layout.view_photo_list_item, null);

        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.isFirstOnly(true);



        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                PhotoBean bean = (PhotoBean) adapter.getItem(position);
                if(bean != null){
                    Intent intent = new Intent(context, ImageBrowserActivity.class);
                    intent.putExtra("bean",bean);
                    startActivity(intent);
                }
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        page = 1;
        ApiService.getPhotosPage(page, new JsonCallback<List<PhotoBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<List<PhotoBean>> response) {
                page = page + 1;
                adapter.setNewData(response.body());
                //停止刷新图标的显示
                setRefreshing(false);
                adapter.setEnableLoadMore(true);
            }

            @Override
            public void onCacheSuccess(com.lzy.okgo.model.Response<List<PhotoBean>> response) {
                super.onCacheSuccess(response);
                //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识

                if (!isInitCache) {
                    //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                    onSuccess(response);
                    isInitCache = true;
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<List<PhotoBean>> response) {
                super.onError(response);
                //获取缓存失败的回调方法,一般很少用到,需要就复写,不需要不用关心
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                //可能需要移除之前添加的布局
                adapter.removeAllFooterView();
            }

        });
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        ApiService.getPhotosPage(page, new JsonCallback<List<PhotoBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<List<PhotoBean>> response) {
                page = page + 1;
                if (page >= 60) {
                    refreshLayout.setEnabled(true);
                    adapter.loadMoreEnd();    //加载完成
                    showToast("没有更多数据");
                    return;
                }
                adapter.addData(response.body());
                adapter.loadMoreComplete();
                refreshLayout.setEnabled(true);
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<List<PhotoBean>> response) {
                super.onError(response);
                //显示数据加载失败,点击重试
                adapter.loadMoreFail();
                //网络请求失败的回调,一般会弹个Toast
//                showToast(e.getMessage());
                refreshLayout.setEnabled(true);
            }


        });
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
