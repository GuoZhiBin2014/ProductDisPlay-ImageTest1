package com.cambricon.productdisplay.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.adapter.TabAdapter;

/**
 * Created by dell on 18-2-3.
 */

public class DataFragment extends Fragment{
    private ViewPager viewPager;
    private View view;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.data_fragment, null);
        initView();
        return view;
    }

    public void initView(){
        viewPager=view.findViewById(R.id.data_count_viewpager);
        tabLayout=view.findViewById(R.id.tab_layout);
        tabAdapter=new TabAdapter(getActivity().getSupportFragmentManager(),getContext());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //设置分割线
        LinearLayout linear = (LinearLayout)tabLayout.getChildAt(0);
        linear.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linear.setDividerDrawable(ContextCompat.getDrawable(getContext(),R.drawable.divider));
        //设置分割线间隔
        linear.setDividerPadding(dip2px(15));
    }

    //像素单位转换
    public int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }
}
