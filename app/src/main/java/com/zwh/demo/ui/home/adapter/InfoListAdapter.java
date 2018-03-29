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

import com.zwh.demo.ui.home.bean.InfoBean;

import java.util.List;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/11/25 11:35
 */

public class InfoListAdapter extends BaseQuickAdapter<InfoBean,BaseViewHolder>{

    public InfoListAdapter(int layoutResId, List<InfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, InfoBean bean) {
        baseViewHolder.setText(R.id.titleView, bean.getName());//
        ImageView imageView = baseViewHolder.getView(R.id.contextImage);
//        Glide.with(BaseApplication.getAppContext()).load(bean.getImgsrc()).placeholder(R.drawable.icon_preload).error(R.drawable.icon_preload).into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.icon_preload)
                .error(R.drawable.icon_preload)
                .centerCrop();
        Glide.with(BaseApplication.getAppContext()).load(bean.getImgurl())
                .apply(options)
//                .override(1090, 1090*3/4)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


}
