package com.zwh.demo.ui.message.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwh.common.app.BaseApplication;
import com.zwh.demo.R;
import com.zwh.demo.ui.message.bean.PhotoBean;

import java.util.List;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/11/25 11:35
 */

public class PhotoListAdapter extends BaseQuickAdapter<PhotoBean,BaseViewHolder>{

    public PhotoListAdapter(int layoutResId, List<PhotoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PhotoBean bean) {
//        baseViewHolder.setText(R.id.tv_name, bean.getCreatedAt());//
//                .setText(R.id.conextTextView, bean.conext)//
//                .setText(R.id.dateTextView, bean.dateTime);//
        ImageView imageView = baseViewHolder.getView(R.id.iv_photo);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_preload)
                .error(R.drawable.icon_preload);

        Glide.with(BaseApplication.getAppContext()).load(bean.getUrl())
                .apply(options)
//                .override(1090, 1090*3/4)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
//        Glide.with(BaseApplication.getAppContext()).load(bean.getUrl()).asBitmap().placeholder(R.drawable.icon_preload).centerCrop().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
//                int width = BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels/2;
//                double hwRatio = width /(double )bitmap.getWidth();
//                lp.width = width;
//                int heigh = (int) (hwRatio*bitmap.getHeight());
//                lp.height = heigh ;
//
//                imageView.setLayoutParams(lp);
//                imageView.setImageBitmap(bitmap);
//            }
//        });
    }



}
