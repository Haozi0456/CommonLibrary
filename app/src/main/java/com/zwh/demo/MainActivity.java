package com.zwh.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ToastUtils;
import com.zwh.common.utils.CustomViewPager;
import com.zwh.demo.ui.demo.fragment.DemoListFragment;
import com.zwh.demo.ui.home.fragment.HomeFragment;
import com.zwh.demo.ui.message.fragment.MessageFragment;
import com.zwh.demo.ui.personal.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.zwh.demo.ui.map.fragment.MapFragment;

/**
 * @author Zhaohao
 * @Description: 主界面
 * @Date 2016/12/05 15:19
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;

    private List<Fragment> mFragments;

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private DemoListFragment demoListFragment;
    private PersonalFragment personalFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    public void initView() {
        initBottomBar();
        initFragment();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        viewPager.setOffscreenPageLimit(5);
        viewPager.setScroll(false);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();

        personalFragment = new PersonalFragment();
        demoListFragment = new DemoListFragment();
        mFragments.add(homeFragment);
        mFragments.add(demoListFragment);
        mFragments.add(messageFragment);
        mFragments.add(personalFragment);


    }


    private void initBottomBar() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

//        TextBadgeItem numberBadgeItem2 = new TextBadgeItem();
//        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem();
//        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_HEART);
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_map, "地图").setActiveColorResource(R.color.main_color).setBadgeItem(numberBadgeItem));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.smartbar_home_sele, "首页").setActiveColorResource(R.color.main_colorPrimary));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.smartbar_cate_sele, "示例").setActiveColorResource(R.color.main_colorPrimary));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.pictures_sele, "美图").setActiveColorResource(R.color.main_colorPrimary));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.smartbar_my_sele, "我的").setActiveColorResource(R.color.main_colorPrimary));


        bottomNavigationBar.setFirstSelectedPosition(0).initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }



    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort("再按一次退出程序", Toast.LENGTH_SHORT);
                exitTime = System.currentTimeMillis();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
        }
        return false;
    }



}

