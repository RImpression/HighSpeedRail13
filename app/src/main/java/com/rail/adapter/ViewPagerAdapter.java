package com.rail.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by RImpression on 2015/11/17.
 */
public class ViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<View> lists;

    public ViewPagerAdapter(Context context,List<View>lists) {
        this.context=context;
        this.lists=lists;
        // TODO Auto-generated constructor stub
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager)container).removeView(lists.get(position));
    }
    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        ((ViewPager)container).addView(lists.get(position));
        return lists.get(position);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return (arg0==arg1);
    }


}
