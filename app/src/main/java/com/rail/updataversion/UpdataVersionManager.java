package com.rail.updataversion;




import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AlertDialog;

import com.rail.fragment.FragmentFourth;
import com.rail.model.UpdataInfo;

import java.io.File;

/**
 * Created by RImpression on 2015/11/18.
 */
public class UpdataVersionManager {
    Context mContext;
    private FragmentFourth mfragment;
    private UpdataInfo updataInfo;
    private final int DOWN_ERROR = 4;

    public UpdataVersionManager(UpdataInfo updataInfo,Context context,FragmentFourth fragment) {
        this.updataInfo = updataInfo;
        this.mContext = context;
        this.mfragment = fragment;
    }

    /*
    弹出对话框通知用户更新程序
     */
    public void showUpdataDialog() {
        AlertDialog.Builder builer = new android.support.v7.app.AlertDialog.Builder(mContext);
        builer.setTitle("版本升级");
        builer.setMessage(updataInfo.getDescription());
        //当点确定按钮时从服务器上下载 新的apk 然后安装   װ
        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("下载APK,更新");
                downLoadApk();
            }
        });
        builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //do sth
            }
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    /*
	 * 从服务器中下载APK
	 */
    protected void downLoadApk() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(mContext);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    //File file = DownloadManager.getFileFromServer(updataInfo.getUrl(), pd);
                    //com.coolsnow.screenshot_5.5.2_liqucn.com.apk
                    //以下测试用，自动安装截屏大师
                    File file = DownloadManager.getFileFromServer("http://file.liqucn.com/upload/2014/xitong/com.coolsnow.screenshot_5.5.2_liqucn.com.apk", pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWN_ERROR;
                    ((FragmentFourth)mfragment).handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }}.start();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);

    }

}