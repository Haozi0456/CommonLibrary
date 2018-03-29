//package com.zwh.demo.ui.demo.activity;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.support.v4.content.ContextCompat;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.lzy.imagepicker.loader.ImageLoader;
//import com.zwh.common.appUtils.ImageLoaderUtils;
//import com.zwh.common.base.BaseActivity;
//import com.zwh.common.commonAdapter.NinePicturesAdapter;
//import com.zwh.common.utils.ImageLoaderUtils;
//import com.zwh.common.widget.NoScrollGridView;
//import com.zwh.common.widget.NormalTitleBar;
//import com.zwh.demo.R;
//
//import java.util.List;
//
//
//public class ImageSelectorDemoActivity extends BaseActivity {
//
//
//    NormalTitleBar titleBar;
//    EditText etContent;
//    NoScrollGridView gridview;
//    TextView tvSave;
//
//    private NinePicturesAdapter ninePicturesAdapter;
//    private int REQUEST_CODE=120;
//
//    /**
//     * 启动入口
//     * @param context
//     */
//    public static void startAction(Context context) {
//        Intent intent = new Intent(context, ImageSelectorDemoActivity.class);
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int initLayoutId() {
//        return R.layout.activity_image_selector_demo;
//    }
//
//    @Override
//    public void initView() {
//        titleBar = (NormalTitleBar) findViewById(R.id.titleBar);
//        titleBar.setTvLeftVisiable(true);
//        titleBar.setOnBackListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        gridview = (NoScrollGridView) findViewById(R.id.gridview);
//        ninePicturesAdapter = new NinePicturesAdapter(this,9, new NinePicturesAdapter.OnClickAddListener() {
//            @Override
//            public void onClickAdd(int positin) {
//                choosePhoto();
//            }
//        });
//        gridview.setAdapter(ninePicturesAdapter);
//    }
//
//    /**
//     * 开启图片选择器
//     */
//    private void choosePhoto() {
//        ImgSelConfig config = new ImgSelConfig.Builder(loader)
//                // 是否多选
//                .multiSelect(true)
//                // 确定按钮背景色
//                .btnBgColor(Color.TRANSPARENT)
//                .titleBgColor(ContextCompat.getColor(this,R.color.main_color))
//                // 使用沉浸式状态栏
//                .statusBarColor(ContextCompat.getColor(this,R.color.main_color))
//                // 返回图标ResId
//                .backResId(R.drawable.icon_arrow_back)
//                .title("图片")
//                // 第一个是否显示相机
//                .needCamera(true)
//                // 最大选择图片数量
//                .maxNum(9)
//                .selectedImages(ninePicturesAdapter.getData())
//                .build();
//        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
//    }
//    private ImageLoader loader = new ImageLoader() {
//        @Override
//        public void displayImage(Context context, String path, ImageView imageView) {
//            ImageLoaderUtils.display(context,imageView,path);
//        }
//
//    };
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
//            if(ninePicturesAdapter!=null){
////                ninePicturesAdapter.clearAll();
//                ninePicturesAdapter.setData(pathList);
//                ninePicturesAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//
//}
