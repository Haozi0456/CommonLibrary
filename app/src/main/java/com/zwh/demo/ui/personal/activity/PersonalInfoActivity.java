package com.zwh.demo.ui.personal.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zwh.demo.R;
import com.zwh.common.widget.NormalTitleBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Zhaohao
 * @Description: 用户资料界面
 * @Date 2016/11/24 10:45
 */
public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.headImgView)
    RelativeLayout headImgView;
    @BindView(R.id.niceName)
    TextView niceName;
    @BindView(R.id.niceNameView)
    RelativeLayout niceNameView;
    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;

    private Context context;
    // 选择图片
    private Dialog dialog_choose_img_way;
    private String path = "";
    private Bitmap head;//头像Bitmap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        this.context = this;
        initUI();
    }

    private void initUI() {
        titleBar.setTitleText("编辑个人资料");
        titleBar.setOnBackListener(this);

//        //初始化头像
                path = context.getExternalFilesDir(null).getPath() + "/head/";
        String headImgUrl = path + "head.jpg";
        File file = new File(headImgUrl);
        Bitmap bitmap = null;
        if (!file.exists()) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_default);
        } else {
            bitmap = BitmapFactory.decodeFile(headImgUrl);
        }
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        circularBitmapDrawable.setCircular(true);
        headImg.setImageDrawable(circularBitmapDrawable);
//        Glide.with(context).load(headImgUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImg) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                headImg.setImageDrawable(circularBitmapDrawable);
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                super.onLoadFailed(e, errorDrawable);
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_user_default);
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
//                circularBitmapDrawable.setCircular(true);
//                headImg.setImageDrawable(circularBitmapDrawable);
//            }
//        });

    }

    @OnClick({ R.id.headImgView, R.id.niceNameView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionBack:
                finish();
                break;
            case R.id.headImgView:

                break;
            case R.id.niceNameView:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(context.getExternalFilesDir(null).getPath() + "/head/" + "head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//
                        String fileName = path + "head.jpg";//图片名字
                        File file = new File(fileName);
                        if (file.exists()) {
//                            toModifyHeadImage(head);
                            showMsg("头像修改成功!");
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), head);
                            circularBitmapDrawable.setCircular(true);
                            headImg.setImageDrawable(circularBitmapDrawable);
                        }

                    }
                }
                break;
        }
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
////            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
////            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}
