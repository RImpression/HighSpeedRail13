package com.rail.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.rail.dao.StationsDatabaseHelper;
import com.rail.fragment.FragmentFirst;
import com.rail.fragment.FragmentFourth;
import com.rail.fragment.FragmentSecond;
import com.rail.fragment.FragmentThird;
import com.rail.https.StationSearch;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener{

    private RadioGroup rgTabbar;
    private RadioButton rb_first;
    //4个fragment
    private FragmentFirst fragmentFirst;
    private FragmentSecond fragmentSecond;
    private FragmentThird fragmentThird;
    private FragmentFourth fragmentFourth;
    private FragmentManager fragmentManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        rgTabbar = (RadioGroup) findViewById(R.id.rgTabbar);
        rgTabbar.setOnCheckedChangeListener(this);

        //获取第一个单选按钮，并设置其为选中状态
        rb_first = (RadioButton) findViewById(R.id.tab_menu_first);
        rb_first.setChecked(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("e路通");
        setSupportActionBar(toolbar);

    }


    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (arg1) {
            case R.id.tab_menu_first:
                if(fragmentFirst == null){
                    fragmentFirst = new FragmentFirst();
                    fTransaction.add(R.id.ly_content, fragmentFirst);
                } else {
                    fTransaction.show(fragmentFirst);
                }
                break;

            case R.id.tab_menu_second:
                if(fragmentSecond == null){
                    fragmentSecond = new FragmentSecond();
                    fTransaction.add(R.id.ly_content, fragmentSecond);
                } else {
                    fTransaction.show(fragmentSecond);
                }
                break;

            case R.id.tab_menu_third:
                if(fragmentThird == null){
                    fragmentThird = new FragmentThird();
                    fTransaction.add(R.id.ly_content, fragmentThird);
                } else {
                    fTransaction.show(fragmentThird);
                }
                break;

            case R.id.tab_menu_forth:
                if(fragmentFourth == null){
                    fragmentFourth = new FragmentFourth();
                    fTransaction.add(R.id.ly_content, fragmentFourth);
                } else {
                    fTransaction.show(fragmentFourth);
                }
                break;

        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fTransaction) {
        if(fragmentFirst != null)fTransaction.hide(fragmentFirst);
        if(fragmentSecond != null)fTransaction.hide(fragmentSecond);
        if(fragmentThird != null)fTransaction.hide(fragmentThird);
        if(fragmentFourth != null)fTransaction.hide(fragmentFourth);
    }





}