package com.example.viewpagerswipeleftright;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 06/11/2017.
 */

public class FragmentPager extends Fragment {
    public static final String KEY_COLOR_FRAGMENT = "KEY_COLOR_FRAGMENT";
    private static final String KEY_PAGE_NUMBER = "KEY_PAGE_NUMBER";
    private int pagerID=0;
    private TextView tvPageNumber;
    private boolean isShowing=true;

    public static Fragment newInstance(int pageNumber, int fragmentColor){
        FragmentPager fragmentPager =new FragmentPager();
        Bundle argument = new Bundle();
        argument.putInt(KEY_COLOR_FRAGMENT,fragmentColor);
        argument.putInt(KEY_PAGE_NUMBER,pageNumber);
        fragmentPager.setArguments(argument);
        return fragmentPager;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.frgm_pager_demo,container,false);
        tvPageNumber = (TextView) layout.findViewById(R.id.tv_pager_number);
        return layout;
    }

    public void makeRequest(){
        isShowing=true;
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isShowing) {
                    Log.d("FragmentPager", "MAKE_REQUEST in page "+pagerID);
                }
            }
        },500);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isShowing=true;
        Bundle argument = getArguments();
        tvPageNumber.setText(argument.getInt(KEY_PAGE_NUMBER)+"");
        getView().setBackgroundColor(argument.getInt(KEY_COLOR_FRAGMENT));

    }

    public void setPagerID(int pagerID) {
        this.pagerID=pagerID;
        if(tvPageNumber!=null) {
            tvPageNumber.setText("Pager " + pagerID);
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}
