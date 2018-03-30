package com.zwh.demo.ui.personal.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baoyz.actionsheet.ActionSheet;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.zwh.common.appUtils.ImageLoaderUtils;
import com.zwh.common.base.BaseAppFragment;
import com.zwh.common.tools.glideModule.ImageCatchUtil;
import com.zwh.demo.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;

import static android.app.Activity.RESULT_OK;


public class PersonalFragment extends BaseAppFragment implements View.OnClickListener, ActionSheet.ActionSheetListener {


    @BindView(R.id.loginIcon)
    ImageView loginIcon;
    @BindView(R.id.facoriteView)
    RelativeLayout facoriteView;
    @BindView(R.id.settingView)
    RelativeLayout settingView;
    @BindView(R.id.aboutView)
    RelativeLayout aboutView;
    @BindView(R.id.cacheText)
    TextView cacheText;
    @BindView(R.id.clearnCacheView)
    RelativeLayout clearnCacheView;


    private String path = "";
    private Bitmap head;//头像Bitmap

    private static int RC_CHOOSE_PHOTO = 100;
    private static int REQUEST_CODE_TAKE_PHOTO = 101;
    private static int REQUEST_CODE_CROP = 102;

    private BGAPhotoHelper mPhotoHelper;

    private ImageCatchUtil catchUtil;

    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setTitle("我的");
        isShowBack(false);

        catchUtil = ImageCatchUtil.getInstance(context);

        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), AppUtils.getAppName());
        mPhotoHelper = new BGAPhotoHelper(takePhotoDir);

        initUI();
    }

    private void initUI() {

        ImageLoaderUtils.displayRound(context, loginIcon, path);
        cacheText.setText(catchUtil.getCacheFormatSize());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.loginIcon, R.id.facoriteView, R.id.settingView, R.id.aboutView, R.id.clearnCacheView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginIcon:
                showChooseActionSheet();
                break;
            case R.id.facoriteView:
                break;
            case R.id.settingView:
                break;
            case R.id.clearnCacheView://清除缓存
                showClearnCache();
                break;
            case R.id.aboutView:
                break;
        }
    }

    private void showClearnCache() {
        new MaterialDialog.Builder(context)
                .title("提示:")
                .content("是否确定清除缓存?")
                .canceledOnTouchOutside(true)
                .positiveText("确定").negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                catchUtil.clearImageDiskCache();
                            }
                        }).start();
                        cacheText.setText(catchUtil.getFormatSize(0.0d));
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                }).show();
    }


    private void showChooseActionSheet() {
        ActionSheet.createBuilder(context, getFragmentManager()).setCancelButtonTitle("取消").setOtherButtonTitles("从相册获取", "拍照").setCancelableOnTouchOutside(true).setListener(this).show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        switch (index) {
            case 0:
                choicePhotoWrapper();
                break;
            case 1: //拍照
                try {
                    startActivityForResult(mPhotoHelper.getTakePhotoIntent(), REQUEST_CODE_TAKE_PHOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    private void choicePhotoWrapper() {
        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), AppUtils.getAppName());

        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(context).cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                .maxChooseCount(9) // 图片选择张数的最大值
                .selectedPhotos(null) // 当前已选中的图片路径集合
                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                .build();
        startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            List<String> selectedPhotos = BGAPhotoPickerActivity.getSelectedPhotos(data);
            if (selectedPhotos.size() > 0) {
                try {
                    startActivityForResult(mPhotoHelper.getCropIntent(selectedPhotos.get(0), 200, 200), REQUEST_CODE_CROP);
                } catch (IOException e) {
                    BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {//拍照返回
            try {
                startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getCameraFilePath(), 200, 200), REQUEST_CODE_CROP);
            } catch (Exception e) {
                mPhotoHelper.deleteCameraFile();
                mPhotoHelper.deleteCropFile();
                BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_CODE_CROP) {//裁剪照片返回
            ImageLoaderUtils.displayRound(context, loginIcon, mPhotoHelper.getCropFilePath());
        }
    }


}
