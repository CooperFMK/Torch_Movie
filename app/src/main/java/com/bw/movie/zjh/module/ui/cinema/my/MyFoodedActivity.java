package com.bw.movie.zjh.module.ui.cinema.my;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bw.movie.R;
<<<<<<< HEAD
import com.bw.movie.fmk.base.BasefActivity;
=======
import com.bw.movie.zjh.module.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/13 19:44
 * desc   :  购票记录
 * version: 1.0
 */
<<<<<<< HEAD
public class MyFoodedActivity extends BasefActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_fooded;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

=======
public class MyFoodedActivity  extends BaseActivity {

    @BindView(R.id.cinema_group)
    RadioGroup cinema_group;
    @BindView(R.id.cinema_pager)
    ViewPager cinema_pager;
    private Unbinder bind;
    private List<Fragment> fragments;
    private MyFoodedPayFragment payFragment;
    private MyFoodedFinishFragment finishFragment;

    @Override
    public int bindLayout() {
        return R.layout.activity_my_fooded;
    }

    @Override
    protected void initView() {
        bind = ButterKnife.bind(this);
        fragments = new ArrayList<>();
        payFragment = new MyFoodedPayFragment();
        finishFragment = new MyFoodedFinishFragment();
        fragments.add(payFragment);
        fragments.add(finishFragment);
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
    }

    @Override
    protected void initData() {
        //适配fragment
        cinema_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //viewpager滑动监听
        cinema_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //滑动时
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //页面选中时
            @Override
            public void onPageSelected(int position) {
                cinema_group.check(cinema_group.getChildAt(position).getId());
            }

            //滑动状态改变时
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //选择监听事件
        cinema_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cinema_rb1:
                        cinema_pager.setCurrentItem(0);
                        break;
                    case R.id.cinema_rb2:
                        cinema_pager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });

        /*
         *   点击返回
         * */
        findViewById(R.id.preson_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
