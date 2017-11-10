package com.example.viewpagerswipeleftright;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CustomPagerAdapter customPagerAdapter;
    private static int currentPageID=-0;
    private boolean isSwipeRightToLeft=false;
    private int lastPage=-1;

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
                Log.d("MainActivity","onPageSelected");
                if((lastPage-position == 1) || (lastPage == 0 && position == customPagerAdapter.getCount()-1)) {
                    currentPageID--;
                    if(position ==customPagerAdapter.getCount()-1){
                        ((FragmentPager)customPagerAdapter.getItem(lastPage)).setShowing(false);
                    }else {
                        ((FragmentPager)customPagerAdapter.getItem(position+1)).setShowing(false);
                    }
                }else if ((position-lastPage == 1) || (lastPage==customPagerAdapter.getCount()-1 && position == 0))
                {
                    currentPageID++;
                    if(position ==0){
                        ((FragmentPager)customPagerAdapter.getItem(lastPage)).setShowing(false);
                    }else {
                        ((FragmentPager) customPagerAdapter.getItem(position -1)).setShowing(false);
                    }
                }
                lastPage = position;
                ((FragmentPager)customPagerAdapter.getItem(position)).setPagerID(currentPageID);
                ((FragmentPager)customPagerAdapter.getItem(position)).makeRequest();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Log.d("MainActivity","onPageScrollStateChanged");
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.d("MainActivity","onPageScrolled");
            }
        });

        mViewPager.setCurrentItem(customPagerAdapter.getCount()-1);
    }

    private List<Fragment> getListDefaultFragment() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentPager.newInstance(1,getResources().getColor(R.color.colorAccent)));
        fragments.add(FragmentPager.newInstance(2,getResources().getColor(R.color.colorPrimary)));
        fragments.add(FragmentPager.newInstance(3, Color.GREEN));
        return fragments;
    }
}
