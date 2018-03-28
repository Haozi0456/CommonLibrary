package com.zwh.demo.ui.personal.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zwh.demo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static android.app.Activity.RESULT_OK;


public class PersonalFragment extends Fragment implements View.OnClickListener{


    private Context context;

    @BindView(R.id.loginIcon)
    ImageView loginIcon;
    @BindView(R.id.facoriteView)
    RelativeLayout facoriteView;
    @BindView(R.id.settingView)
    RelativeLayout settingView;
    @BindView(R.id.aboutView)
    RelativeLayout aboutView;

    // 选择图片
    private Dialog dialog_choose_img_way;
    private String path = "";
    private Bitmap head;//头像Bitmap

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        this.context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        RequestOptions options = new RequestOptions()
                .centerCrop();
        //初始化头像
        Glide.with(context).asBitmap().load(R.drawable.ic_user_default).apply(options).into(new BitmapImageViewTarget(loginIcon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                loginIcon.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_user_default);
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),bitmap);
                circularBitmapDrawable.setCircular(true);
                loginIcon.setImageDrawable(circularBitmapDrawable);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.loginIcon, R.id.facoriteView, R.id.settingView, R.id.aboutView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginIcon:
                showSelectPhotoWindow();
                break;
            case R.id.choose_by_local://从相册里面取照片
                dialog_choose_img_way.cancel();
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            case R.id.choose_by_camera://调用相机拍照
                dialog_choose_img_way.cancel();
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(context.getExternalFilesDir(null).getPath()+"/head/", "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
                break;
            case R.id.pop_layout:
                dialog_choose_img_way.cancel();
                break;
            case R.id.dialog_cancel:
                dialog_choose_img_way.cancel();
                break;
            case R.id.facoriteView:
                break;
            case R.id.settingView:
                break;
            case R.id.aboutView:
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
                        if(file.exists()){
//                            toModifyHeadImage(head);
                            showMsg("头像修改成功!");
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), head);
                            circularBitmapDrawable.setCircular(true);
                            loginIcon.setImageDrawable(circularBitmapDrawable);
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

    public void showSelectPhotoWindow() {
        dialog_choose_img_way = new Dialog(context, R.style.MyDialogStyle);
        dialog_choose_img_way.setContentView(R.layout.photo_select_view);
        dialog_choose_img_way.setCanceledOnTouchOutside(true);
        dialog_choose_img_way.findViewById(R.id.pop_layout).setOnClickListener(this);
        dialog_choose_img_way.findViewById(R.id.dialog_cancel).setOnClickListener(this);
        // 拍照上传
        dialog_choose_img_way.findViewById(R.id.choose_by_camera).setOnClickListener(this);
        // 本地上传
        dialog_choose_img_way.findViewById(R.id.choose_by_local).setOnClickListener(this);
        dialog_choose_img_way.show();
    }

    private void showMsg(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
