package com.zwh.demo.ui.business.fragement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.name;
import static android.R.attr.type;


/**
 * @author Zhaohao
 * @Description: 业务
 * @Date 2016/12/20 10:22
 */
public class BusinessMainFragment extends Fragment {



//    @BindView(R.id.titleBar)
//    NormalTitleBar titleBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Context context;

    private FragmentPagerAdapter pagerAdapter;

    private String[] items = new String[]{"智能APP","博物馆","数字考古","文化遗产"};
    private Handler handler;

    public BusinessMainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragement_business_main, container, false);
        this.context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {
//        titleBar.setTvLeftVisiable(false);
//        titleBar.setTitleText("产品列表");
        final List<Fragment> fragmentList = new ArrayList<>();
        final List<String> fragmentTitleList = new ArrayList<>();
        fragmentList.add(createAppFragment(0,items[0]));
        fragmentTitleList.add(items[0]);
        for (int i = 1; i < items.length; i++) {
            fragmentList.add(createListFragments(i,items[i]));
            fragmentTitleList.add(items[i]);
        }
        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentTitleList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:


                        break;
                }
         }
        };

    }

    private AppFragment createAppFragment(int type,String name) {
        AppFragment fragment = new AppFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }

    private BusinessFragment createListFragments(int type,String name) {
        BusinessFragment fragment = new BusinessFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ApiService.distory(context);
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onRefresh() {
//        adapter.setEnableLoadMore(false);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String data = getJson(context,"products.json");
//                Message message = new Message();
//                message.what = 1;
//                message.obj = data;
//                handler.sendMessageDelayed(message,1500);
//
//            }
//        }).start();
//
//    }


}
