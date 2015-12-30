package com.rail.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by RImpression on 2015/11/17.
 */
public class WelcomeGuide extends Activity {
    private Handler handler = new Handler();
    private int delaylong = 2000;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //IsFirstStart();//判断是否第一次运行
        //无父窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcomeguide);
        IsFirstStart();
    }

    private void hander() {
        // TODO Auto-generated method stub
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(WelcomeGuide.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                WelcomeGuide.this.finish();
            }
        }, delaylong);
    }

    private void IsFirstStart() {
        System.out.println("ISFIRSTSTART");
        // TODO Auto-generated method stub
        preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
        int count = preferences.getInt("count", 0);
        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
        if (count == 0) {
            Intent intent = new Intent();
            intent.setClass(this,WelcomeUI.class);
            startActivity(intent);
            this.finish();
        } else{
            hander();//首页缓存首页
        }
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putInt("count", ++count);
        //提交修改
        editor.commit();

    }


}
