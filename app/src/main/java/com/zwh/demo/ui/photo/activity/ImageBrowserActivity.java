package com.zwh.demo.ui.photo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.zwh.common.tools.statusBar.StatusBarCompat;
import com.zwh.demo.R;
import com.zwh.demo.ui.photo.bean.PhotoBean;
import com.zwh.demo.utils.SystemUiVisibilityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageBrowserActivity extends AppCompatActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.background)
    RelativeLayout background;
    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;
    private ColorDrawable mBackground;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.activity_image_browser);
        this.context = this;
        ButterKnife.bind(this);
        toolBarFadeIn();
        initToolbar();
//        initBackground();
//        loadPhotoIv();
        initImageView();
        setPhotoViewClickEvent();
    }

    private void initToolbar() {
        toolbar.setTitle("妹子");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initImageView() {
        loadPhotoIv();
    }

    private void setPhotoViewClickEvent() {
        photoView.setOnPhotoTapListener(new OnPhotoTapListener(){
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                hideOrShowToolbar();
            }



//            @Override
//            public void onOutsidePhotoTap() {
//                hideOrShowToolbar();
////                hideOrShowStatusBar();
//            }
        });
    }

    private void loadPhotoIv() {
        Intent intent = getIntent();
        PhotoBean bean = (PhotoBean) intent.getSerializableExtra("bean");
        if (bean != null) {
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.icon_preload)
                    .error(R.drawable.icon_preload)
                    ;
            Glide.with(context)
                    .load(bean.getUrl())
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(photoView);
        }

//        PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);
//        attacher.update();
    }

    protected void hideOrShowToolbar() {
        toolbar.animate().alpha(mIsToolBarHidden ? 1.0f : 0.0f).setInterpolator(new DecelerateInterpolator(2)).start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(ImageBrowserActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(ImageBrowserActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }

    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }
}
