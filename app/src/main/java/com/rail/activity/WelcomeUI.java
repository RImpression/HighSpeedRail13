package com.rail.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.rail.adapter.ViewPagerAdapter;
import com.rail.https.StationSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RImpression on 2015/11/17.
 */
public class WelcomeUI extends Activity {
    private ViewPager viewpager;
    private List<View> viewlists;
    private Button button_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //无父窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcomeui);
        //创建车站数据库表
        new StationSearch(WelcomeUI.this).HttpToGetStationData();

        IniteData();
    }
    private void IniteData() {
        // TODO Auto-generated method stub
        viewlists=new ArrayList<View>();
        int layouts[]={R.layout.welcome_first, R.layout.welcome_two, R.layout.welcome_three, R.layout.welcome_fourth};
        viewpager=(ViewPager) findViewById(R.id.firstopenui);
        LayoutInflater inflater=getLayoutInflater();
        for(int i=0;i<layouts.length;i++){
            viewlists.add(inflater.inflate(layouts[i], null));
        }
        button_finish=(Button)(viewlists.get(3)).findViewById(R.id.button_firststart);
        viewpager.setAdapter(new ViewPagerAdapter(this,viewlists));
        button_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                intent.setClass(WelcomeUI.this, MainActivity.class);
                startActivity(intent);
                WelcomeUI.this.finish();
            }
        });
    }

}
