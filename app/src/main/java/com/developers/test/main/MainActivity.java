package com.developers.test.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.developers.test.R;
import com.developers.test.classroom.ClassroomFragment;
import com.developers.test.home.HomeFragment;
import com.developers.test.mine.MineFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.supermax.base.mvp.QsViewPagerActivity;
import com.supermax.base.mvp.model.QsModelPager;

public class MainActivity extends QsViewPagerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImmersionBar();
    }

    @Override
    public QsModelPager[] getModelPagers() {
        QsModelPager modelPager1 = new QsModelPager();
        modelPager1.fragment = new HomeFragment();
        modelPager1.title = "首页";
        modelPager1.position = 0;

        QsModelPager modelPager2 = new QsModelPager();
        modelPager2.fragment = new ClassroomFragment();
        modelPager2.title = "班级";
        modelPager2.position = 1;

        QsModelPager modelPager3 = new QsModelPager();
        modelPager3.fragment = new MineFragment();
        modelPager3.title = "我的";
        modelPager3.position = 2;


        return new QsModelPager[]{modelPager1, modelPager2, modelPager3};    }

    @Override
    public int getTabItemLayout() {
        return 0;
    }

    @Override
    public void initTab(View tabItem, QsModelPager modelPager) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.es_bg_white)
                .statusBarDarkFont(true, 0.2f)//解决 状态栏字体颜色
                .addTag("default")
                .init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
