//package com.zwh.common.base;
//
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.KeyEvent;
//import android.widget.Toast;
//
//import com.ashokvarma.bottomnavigation.BottomNavigationBar;
//import com.ashokvarma.bottomnavigation.BottomNavigationItem;
//import com.zwh.common.R;
//import com.zwh.demo.ui.home.fragment.InfoFragment;
//import com.zwh.demo.ui.map.fragment.MapFragment;
//import com.zwh.demo.ui.message.fragment.MessageFragment;
//import com.zwh.demo.ui.personal.activity.PersonalActivity;
//import com.zwh.demo.ui.personal.fragment.PersonalFragment;
//
//
//import butterknife.ButterKnife;
//
//import static android.R.attr.layout_alignParentBottom;
//import static android.R.attr.layout_gravity;
//import static android.R.attr.layout_height;
//import static android.R.attr.layout_width;
//
///**
// * @Description: 主界面
// * @author Zhaohao
// * @Date 2016/12/05 15:19
// */
//public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
//
////    <com.ashokvarma.bottomnavigation.BottomNavigationBar
////    android:id="@+id/bottomNavigationBar"
////    android:layout_width="match_parent"
////    android:layout_height="wrap_content"
////    android:layout_alignParentBottom="true"
////    android:layout_gravity="bottom"/>
//
//    @BindViewViewViewViewView(R.id.bottomNavigationBar)
//    BottomNavigationBar bottomNavigationBar;
//
//    private Context context;
//
//    /**
//     * 用于对Fragment进行管理
//     */
//    private FragmentManager fragmentManager;
//
//    private InfoFragment homeFragment;
//    private MapFragment mapFragment;
//    private MessageFragment messageFragment;
//    private PersonalFragment personalFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        this.context = this;
//        initBottomBar();
//        initFragment();
//    }
//
//    private void initFragment() {
//        fragmentManager = getFragmentManager();
//        // 第一次启动时选中第0个tab
//        setTabSelection(0);
//    }
//
//    private void initBottomBar() {
//
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
////        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
//
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//
////        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
//
//        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.smartbar_home_sele, "首页")).setActiveColor(R.color.main_color)
//                .addItem(new BottomNavigationItem(R.drawable.smartbar_cate_sele, "地图")).setActiveColor(R.color.main_color)
//                .addItem(new BottomNavigationItem(R.drawable.pictures_sele, "美图")).setActiveColor(R.color.main_color)
//                .addItem(new BottomNavigationItem(R.drawable.smartbar_my_sele, "我的")).setActiveColor(R.color.main_color)
//                .initialise();
//
//        bottomNavigationBar.setTabSelectedListener(this);
//    }
//
//
//    /* 根据传入的index参数来设置选中的tab页。
//            *
//            * @param index
//    *
//            */
//    private void setTabSelection(int index) {
//        // 开启一个Fragment事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//
//        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        hideFragments(transaction);
//        //自定义动画
////        transaction.setCustomAnimations(
////         R.animator.fragment_slide_left_enter,
////         R.animator.fragment_slide_left_exit,
////         R.animator.fragment_slide_right_enter,
////         R.animator.fragment_slide_right_exit);
//        switch (index) {
//            case 0:
//                if ( homeFragment== null) {
//                    homeFragment = new InfoFragment();
//                    // 加入到BackStack中
//                    transaction.add(R.id.content, homeFragment);
////                    transaction.addToBackStack(null);
//                } else {
//                    transaction.show(homeFragment);
//                }
//                break;
//            case 1:
//                if (mapFragment == null) {
//                    mapFragment = new MapFragment();
//                    transaction.add(R.id.content, mapFragment);
////                    transaction.addToBackStack(null);
//                } else {
//                    transaction.show(mapFragment);
//                }
//                break;
//            case 2:
//                if (messageFragment == null) {
//                    messageFragment = new MessageFragment();
//                    transaction.add(R.id.content, messageFragment);
////                    transaction.addToBackStack(null);
//                } else {
//                    transaction.show(messageFragment);
//                }
//                break;
//            case 3:
////                if (personalFragment == null) {
////                    personalFragment = new PersonalFragment();
////                    transaction.add(R.id.content, personalFragment);
//////                    transaction.addToBackStack(null);
////                } else {
////                    transaction.show(personalFragment);
////                }
//                Intent personalIntent = new Intent(this, PersonalActivity.class);
//                startActivityForResult(personalIntent,index);
//                break;
//        }
//
//
//        transaction.commit();
//    }
//
//    /**
//     * 将所有的Fragment都置为隐藏状态。
//     *
//     * @param transaction
//     *            用于对Fragment执行操作的事务
//     */
//    private void hideFragments(FragmentTransaction transaction) {
//        if (homeFragment != null&&homeFragment.isVisible()) {
//            transaction.hide(homeFragment);
////            homeFragment =null;
//        }
//        if (mapFragment != null&&mapFragment.isVisible()) {
//            transaction.hide(mapFragment);
////            mapFragment = null;
//        }
//        if (messageFragment != null&& messageFragment.isVisible()) {
//            transaction.hide(messageFragment);
////            messageFragment = null;
//        }
//        if (personalFragment != null&& personalFragment.isVisible()) {
//            transaction.hide(personalFragment);
////            personalFragment = null;
//        }
//    }
//
//    @Override
//    public void onTabSelected(int position) {
//        setTabSelection(position);
//    }
//
//    @Override
//    public void onTabUnselected(int position) {
//
//    }
//
//    @Override
//    public void onTabReselected(int position) {
//
//    }
//
//
//    private long exitTime = 0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if (keyCode == KeyEvent.KEYCODE_BACK )
//        {
//            if((System.currentTimeMillis() - exitTime) > 2000)
//            {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            }
//            else
//            {
//                android.os.Process.killProcess(android.os.Process.myPid());
//                finish();
////                System.exit(0);
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case 0:
//
//                break;
//            case 1:
//                break;
//            case 2:
//
//                break;
//            case 3:
//                bottomNavigationBar.selectTab(0);
//                break;
//        }
//    }
//}
