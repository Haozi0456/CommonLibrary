package com.zwh.demo.ui.home.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwh.common.app.BaseApplication;
import com.zwh.demo.R;

import com.zwh.demo.ui.home.bean.CommentBean;

import java.util.List;



/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/11/25 11:35
 */

public class CommentListAdapter extends BaseQuickAdapter<CommentBean,BaseViewHolder>{

    public CommentListAdapter(int layoutResId, List<CommentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CommentBean bean) {
        baseViewHolder.setText(R.id.nameTextView, bean.name)//
                .setText(R.id.conextTextView, bean.conext)//
                .setText(R.id.dateTextView, bean.dateTime);//
        final ImageView imageView = baseViewHolder.getView(R.id.headImg);
//        Glide.with(BaseApplication.getAppContext()).load(bean.headUrl)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.drawable.ic_user_default)
//                .error(R.drawable.ic_user_default)
//                .centerCrop()
//                .crossFade()
//                .into(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(BaseApplication.getAppContext()).asBitmap().load(bean.headUrl).apply(options).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(BaseApplication.getAppContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed( Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                Bitmap bitmap = BitmapFactory.decodeResource(BaseApplication.getAppContext().getResources(), R.drawable.ic_user_default);
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(BaseApplication.getAppContext().getResources(), bitmap);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });

    }


}
