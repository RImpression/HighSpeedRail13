package com.rail.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rail.activity.R;
import com.rail.https.StationSearch;
import com.rail.model.UpdataInfo;
import com.rail.updataversion.UpdataInfoParser;
import com.rail.updataversion.UpdataVersionManager;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FragmentFourth extends Fragment{
    private final String TAG = this.getClass().getName();
    Context mContext;
    Button btnUpdataVersion,btnUpdataStation;
    private final int UPDATA_NONEED = 0;
    private final int UPDATA_CLIENT = 1;
    private final int GET_UNDATAINFO_ERROR = 2;
    private final int SDCARD_NOMOUNTED = 3;
    private final int DOWN_ERROR = 4;
    private UpdataInfo info;
    private String localVersion;
    private FragmentFourth fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        fragment = FragmentFourth.this;
        btnUpdataVersion = (Button) getView().findViewById(R.id.btnUpdataVersion);
        btnUpdataVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    localVersion = getVersionName();
                    CheckVersionTask cv = new CheckVersionTask();
                    new Thread(cv).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnUpdataStation = (Button) getView().findViewById(R.id.btnUpdataStation);
        btnUpdataStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StationSearch(mContext).updateStation();
            }
        });
    }

    private String getVersionName() throws Exception {
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(getActivity().getPackageName(),
                0);
        return packInfo.versionName;
    }

    public class CheckVersionTask implements Runnable {
        InputStream is;
        public void run() {
            try {
                String path = getResources().getString(R.string.url_server);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    // 从服务器获得一个输入流
                    is = conn.getInputStream();
                }
                info = UpdataInfoParser.getUpdataInfo(is);
                if (info.getVersion().equals(localVersion)) {
                    Log.i(TAG, "版本号相同");
                    Message msg = new Message();
                    msg.what = UPDATA_NONEED;
                    handler.sendMessage(msg);
                    // LoginMain();
                } else {
                    Log.i(TAG, "版本号不相同 ");
                    Message msg = new Message();
                    msg.what = UPDATA_CLIENT;
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                Message msg = new Message();
                msg.what = GET_UNDATAINFO_ERROR;
                handler.sendMessage(msg);
                e.printStackTrace();
            }
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATA_NONEED:
                    Toast.makeText(getActivity().getApplicationContext(), "不需要更新",
                            Toast.LENGTH_SHORT).show();
                    break;
                case UPDATA_CLIENT:
                    //对话框通知用户升级程序
                    new UpdataVersionManager(info,mContext,fragment);
                    break;
                case GET_UNDATAINFO_ERROR:
                    //服务器超时
                    Toast.makeText(getActivity().getApplicationContext(), "获取服务器更新信息失败", Toast.LENGTH_LONG).show();
                    break;
                case DOWN_ERROR:
                    //下载apk失败
                    Toast.makeText(getActivity().getApplicationContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };



}