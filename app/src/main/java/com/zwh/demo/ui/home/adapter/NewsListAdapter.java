package com.zwh.demo.ui.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwh.common.app.BaseApplication;
import com.zwh.demo.R;

import com.zwh.demo.ui.home.bean.NewsBean;

import java.util.List;



/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/11/25 11:35
 */

public class NewsListAdapter extends BaseQuickAdapter<NewsBean,BaseViewHolder>{

    public NewsListAdapter(int layoutResId, List<NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean bean) {
        baseViewHolder.setText(R.id.titleView, bean.getTitle())//
                .setText(R.id.sourceText, bean.getSource())//
                .setText(R.id.dateView, bean.getPtime());//
        ImageView imageView = baseViewHolder.getView(R.id.newsImg);
//        Glide.with(BaseApplication.getAppContext()).load(bean.getImgsrc()).placeholder(R.drawable.icon_preload).error(R.drawable.icon_preload).into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.icon_preload)
                .error(R.drawable.icon_preload)
                .centerCrop();
        Glide.with(BaseApplication.getAppContext()).load(bean.getImgsrc())
                .apply(options)
//                .override(1090, 1090*3/4)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


}
