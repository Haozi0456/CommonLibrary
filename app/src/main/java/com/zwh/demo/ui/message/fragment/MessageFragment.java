package com.zwh.demo.ui.message.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.model.Response;
import com.zwh.common.base.BaseAppFragment;
import com.zwh.common.callback.JsonCallback;
import com.zwh.common.tools.divideItemDecoration.SpacesItemDecoration;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;
import com.zwh.demo.callback.DataBackResult;
import com.zwh.demo.ui.message.activity.ImageBrowserActivity;
import com.zwh.demo.ui.message.adapter.PhotoListAdapter;
import com.zwh.demo.ui.message.bean.PhotoBean;

import java.util.List;

import butterknife.BindView;


public class MessageFragment extends BaseAppFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private PhotoListAdapter adapter;


    private int page = 1;
    public boolean isInitCache = false;

    public MessageFragment() {
        // Required empty public constructor
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_message, container, false);
//        this.context = getActivity().getApplicationContext();
//        ButterKnife.bind(this, view);
//        initView();
//        refreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                refreshLayout.setRefreshing(true);
//                onRefresh();
//            }
//        });
//        return view;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setTitle("美图");
        isShowBack(false);

        initView();
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    private void initView() {

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
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                PhotoBean bean =  adapter.getItem(position);
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
        ApiService.getPhotosPage(page, new JsonCallback<DataBackResult<List<PhotoBean>>>() {
            @Override
            public void onSuccess(Response<DataBackResult<List<PhotoBean>>> response) {
                page = page + 1;
                adapter.setNewData(response.body().results);
                //停止刷新图标的显示
                setRefreshing(false);
                adapter.setEnableLoadMore(true);
            }

            @Override
            public void onCacheSuccess(Response<DataBackResult<List<PhotoBean>>> response) {
                super.onCacheSuccess(response);
                if (!isInitCache) {
//                    //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                    onSuccess(response);
                    isInitCache = true;
                }
            }

            @Override
            public void onError(Response<DataBackResult<List<PhotoBean>>> response) {
                super.onError(response);
                //网络请求失败的回调,一般会弹个Toast
                ToastUtils.showShort(response.message());
                //停止刷新图标的显示
                setRefreshing(false);
                //设置下拉刷新可用
                refreshLayout.setEnabled(true);
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
        ApiService.getPhotosPage(page, new JsonCallback<DataBackResult<List<PhotoBean>>>() {
            @Override
            public void onSuccess(Response<DataBackResult<List<PhotoBean>>> response) {
                page = page + 1;
                if (page >= 60) {
                    refreshLayout.setEnabled(true);
                    adapter.loadMoreEnd();    //加载完成
                    ToastUtils.showShort("没有更多数据");
                    return;
                }
                adapter.addData(response.body().results);
                adapter.loadMoreComplete();
                refreshLayout.setEnabled(true);
            }

            @Override
            public void onError(Response<DataBackResult<List<PhotoBean>>> response) {
                super.onError(response);

                //显示数据加载失败,点击重试
                adapter.loadMoreFail();
                //网络请求失败的回调,一般会弹个Toast
                ToastUtils.showShort(response.message());
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
