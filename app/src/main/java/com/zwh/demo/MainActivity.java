package com.zwh.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zwh.demo.ui.business.fragement.BusinessMainFragment;
import com.zwh.demo.ui.home.fragment.HomeFragment;
import com.zwh.demo.ui.message.fragment.NewsFragment;
import com.zwh.demo.ui.personal.activity.PersonalActivity;
import com.zwh.demo.ui.personal.fragment.PersonalFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @Description: 主界面
 * @author Zhaohao
 * @Date 2016/12/05 15:19
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;

    private Context context;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;
//    private MapFragment mapFragment;
    private NewsFragment newsFragment;
    private PersonalFragment personalFragment;
    private BusinessMainFragment productFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.context = this;
        initBottomBar();
        initFragment();

        copyAsstes();
    }

    private void copyAsstes() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(context.getExternalFilesDir(null).getPath() + "/appicons/");
                if(!file.exists()){
                    file.mkdirs();
                }

                CopyAssets(context,"appicons",file.getPath());
            }
        }).start();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    private void initBottomBar() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.smartbar_home_sele, "简介")).setActiveColor(R.color.main_color)
                .addItem(new BottomNavigationItem(R.drawable.smartbar_cate_sele, "产品")).setActiveColor(R.color.main_color)
                .addItem(new BottomNavigationItem(R.drawable.icon_news_trends, "动态")).setActiveColor(R.color.main_color)
                .addItem(new BottomNavigationItem(R.drawable.smartbar_my_sele, "关于")).setActiveColor(R.color.main_color)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }


    /* 根据传入的index参数来设置选中的tab页。
            *
            * @param index
    *
            */
    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        //自定义动画
//        transaction.setCustomAnimations(
//         R.animator.fragment_slide_left_enter,
//         R.animator.fragment_slide_left_exit,
//         R.animator.fragment_slide_right_enter,
//         R.animator.fragment_slide_right_exit);
        switch (index) {
            case 0:
                if ( homeFragment== null) {
                    homeFragment = new HomeFragment();
                    // 加入到BackStack中
                    transaction.add(R.id.content, homeFragment);
//                    transaction.addToBackStack(null);
                } else {
                    transaction.show(homeFragment);
                }
                break;
//            case 1:
//                if (mapFragment == null) {
//                    mapFragment = new MapFragment();
//                    transaction.add(com.dct.dct.R.id.content, mapFragment);
////                    transaction.addToBackStack(null);
//                } else {
//                    transaction.show(mapFragment);
//                }
//                break;
            case 1:
                if (productFragment == null) {
                    productFragment = new BusinessMainFragment();
                    transaction.add(R.id.content, productFragment);
//                    transaction.addToBackStack(null);
                } else {
                    transaction.show(productFragment);
                }
                break;
            case 2:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment);
//                    transaction.addToBackStack(null);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 3:
//                if (personalFragment == null) {
//                    personalFragment = new PersonalFragment();
//                    transaction.add(R.id.content, personalFragment);
////                    transaction.addToBackStack(null);
//                } else {
//                    transaction.show(personalFragment);
//                }
                Intent personalIntent = new Intent(this, PersonalActivity.class);
                startActivityForResult(personalIntent,index);
                break;
        }


        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null&&homeFragment.isVisible()) {
            transaction.hide(homeFragment);
//            homeFragment =null;
        }
        if (productFragment != null&&productFragment.isVisible()) {
            transaction.hide(productFragment);
//            mapFragment = null;
        }
        if (newsFragment != null&& newsFragment.isVisible()) {
            transaction.hide(newsFragment);
//            messageFragment = null;
        }
        if (personalFragment != null&& personalFragment.isVisible()) {
            transaction.hide(personalFragment);
//            personalFragment = null;
        }
    }

    @Override
    public void onTabSelected(int position) {
        setTabSelection(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            if((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
//                System.exit(0);
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:

                break;
            case 1:
                break;
            case 2:

                break;
            case 3:
                bottomNavigationBar.selectTab(0);
                break;
        }
    }


    /**
     * 复制asset文件到指定目录
     * @param oldPath  asset下的路径
     * @param newPath  SD卡下保存路径
     */
    public static void CopyAssets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(newPath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    CopyAssets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
