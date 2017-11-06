package com.example.viewpagerswipeleftright;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CustomPagerAdapter customPagerAdapter;
    private static int currentPageID=0;
    private boolean isSwipeRightToLeft=false;
    private int lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),getListDefaultFragment());
        mViewPager.setAdapter(customPagerAdapter);
        mViewPager.addOnPageChangeListener(new CircularViewPagerHandler(mViewPager) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if((lastPage-position == 1) || (lastPage == 0 && position == customPagerAdapter.getCount()-1)) {
                    currentPageID--;
                }else if ((position-lastPage == 1) || (lastPage==customPagerAdapter.getCount()-1 && position == 0))
                {
                    currentPageID++;
                }
                lastPage = position;
                ((FragmentPager)customPagerAdapter.getItem(position)).setPagerID(currentPageID);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private List<Fragment> getListDefaultFragment() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentPager.newInstance(1,getResources().getColor(R.color.colorAccent)));
        fragments.add(FragmentPager.newInstance(2,getResources().getColor(R.color.colorPrimary)));
        fragments.add(FragmentPager.newInstance(3, Color.GREEN));
        return fragments;
    }
}
