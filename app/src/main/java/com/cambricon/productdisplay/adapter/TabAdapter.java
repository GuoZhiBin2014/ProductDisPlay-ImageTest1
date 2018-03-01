package com.cambricon.productdisplay.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.activity.ClassificationData;
import com.cambricon.productdisplay.activity.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 18-2-6.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    public TabAdapter(FragmentManager fm,Context context) {
        super(fm);
        tabs.add(context.getString(R.string.gv_text_item1));
        tabs.add(context.getString(R.string.gv_text_item2));
        fragments.add(new ClassificationData(context,tabs.get(0)));
        fragments.add(new TabFragment(context,tabs.get(1)));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
