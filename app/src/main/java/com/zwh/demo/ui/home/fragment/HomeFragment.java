package com.zwh.demo.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.zwh.common.widget.NormalTitleBar;
import com.zwh.demo.R;
import com.zwh.demo.api.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {


    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;
    @BindView(R.id.introductionText)
    TextView introductionText;
    private Context context;


    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleBar.setTvLeftVisiable(false);
        titleBar.setTitleText("公司简介");

        titleBar.setRightTitleVisibility(true);
        titleBar.setRightTitle("主页");
        titleBar.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.cncis.com.cn/index.html");
                intent.setData(content_url);
                startActivity(intent);
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


}
