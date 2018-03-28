package com.zwh.demo.ui.business.fragement;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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
import com.google.gson.reflect.TypeToken;
import com.zwh.common.tools.divideItemDecoration.DividerItemDecoration;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;
import com.zwh.demo.ui.business.activity.BusinessDetailsActivity;
import com.zwh.demo.ui.business.adapter.APPListAdapter;
import com.zwh.demo.ui.message.bean.NewsBean;
import com.zwh.demo.utils.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.StringUtils;

/**
 * @Description: 产品列表
 * @author Zhaohao
 * @Date 2016/12/20 10:22
 */
public class AppFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private Context context;
    private APPListAdapter adapter;

    private Handler handler;
    private int type = 0;
    public AppFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        this.context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);
        initView();
        //开启loading,获取数据
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
//        titleBar.setTvLeftVisiable(false);
//        titleBar.setTitleText("产品列表");

        if (getArguments() != null) {
            type  = getArguments().getInt("type");
            String name  = getArguments().getString("name");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        adapter = new APPListAdapter(R.layout.item_product_layout, null);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        adapter.setEnableLoadMore(true);
        adapter.isFirstOnly(true);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsBean bean = (NewsBean) adapter.getItem(position);
                if (bean != null) {
                    Intent intent = new Intent(context, BusinessDetailsActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);
                }
            }

        });

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String data = (String) msg.obj;
                        if(!StringUtils.isEmpty(data)){
                            setRefreshing(false);
                            List<NewsBean> beanList = Convert.fromJson(data,new TypeToken<List<NewsBean>>(){}.getType());
                            adapter.setNewData(beanList);
                        }

                        break;
                }
            }
        };

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
        adapter.setEnableLoadMore(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getJson(context,"products.json");
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                handler.sendMessageDelayed(message,1000);

            }
        }).start();

    }

    /**
     * 从asset路径下读取对应文件转String输出
     * @param mContext
     * @return
     */
    public static String getJson(Context mContext, String fileName) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }


    public void setRefreshing(final boolean refreshing) {
        if(refreshLayout != null){
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(refreshing);
                }
            });
        }

    }
}
