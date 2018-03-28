package com.zwh.demo.ui.business.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwh.demo.R;
import com.zwh.demo.app.GApp;
import com.zwh.demo.ui.message.bean.NewsBean;

import java.util.List;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/11/25 11:35
 */

public class APPListAdapter extends BaseQuickAdapter<NewsBean,BaseViewHolder> {

    public APPListAdapter(int layoutResId, List<NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean bean) {
        baseViewHolder.setText(R.id.nameView, bean.getTitle())//
                .setText(R.id.versionView, "版本:"+bean.getVersion())//
                .setText(R.id.timeView, "上线时间:"+bean.getTime());//
        ImageView imageView = baseViewHolder.getView(R.id.iconImage);
//        Glide.with(GApp.getAppContext()).load(bean.getImgsrc()).placeholder(R.drawable.icon_preload).error(R.drawable.icon_preload).into(imageView);
//        Glide.with(GApp.getAppContext()).load("file:///android_asset"+bean.getIcon())
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.drawable.icon_preload)
//                .error(R.drawable.icon_preload)
//                .crossFade()
//                .into(imageView);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.icon_preload)
                .error(R.drawable.icon_preload);
        Glide.with(GApp.getAppContext()).load("file:///android_asset"+bean.getIcon()).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }


}
