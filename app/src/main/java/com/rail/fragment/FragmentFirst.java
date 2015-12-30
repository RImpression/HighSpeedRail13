package com.rail.fragment;


import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rail.activity.R;
import com.rail.activity.ResultData;
import com.rail.activity.SelectCalendar;
import com.rail.activity.SelectPlace;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public class FragmentFirst extends Fragment implements OnClickListener {

    Context mContext;
    private LinearLayout lyDateSelect;		//日期选择
    private LinearLayout lyCarModel;	//车型选择
    private TextView tvFromWhere;
    private TextView tvToWhere;

    private TextView tvCar;				//车型显示
    private TextView tvMonth;			//显示月日
    private TextView tvWeek;			//显示周
    private Button btnChangeText;			//交换按钮
    private Button btnCheckTwo;
    private Boolean TAG_LAYOUT = true;

    //显示月日周
    private String Year,Month,Day,Week,dateString;
    //记录初始地与目的地
    private String fromWhere,toWhere;

    //用于记录车型dialog多项选择项
    private boolean[] checkItems = new boolean[]{true,true,true,true,true};
    private static final String PACKAGE_NAME = "com.rail.activity";
    private File staFile = new File("/data/data/" + PACKAGE_NAME + "/databases/Stations.db");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initView();
        //日期设定
        checkSettingDate();
    }

    /**
     * 判断Month,Day,Week的值是否为空，是则设置成当前时间
     */
    private void checkSettingDate() {
        if(Year == null && Month == null && Day == null && Week == null) {
            StringGetData();
        }
        tvMonth.setText(Month + "月" + Day + "日");
        tvWeek.setText("周" + Week);
        dateString = Year+"-"+Month+"-"+Day;
        Log.i("seechange", "i am activityCreat");
        fromWhere = tvFromWhere.getText().toString();
        toWhere = tvToWhere.getText().toString();
    }

    private void initView() {
        lyCarModel = (LinearLayout) getView().findViewById(R.id.lyCarModel);
        tvCar = (TextView) getView().findViewById(R.id.tvCar);
        lyDateSelect = (LinearLayout) getView().findViewById(R.id.lyDateSelect);
        tvMonth = (TextView) getView().findViewById(R.id.tvMonth);
        tvWeek = (TextView) getView().findViewById(R.id.tvWeek);
        btnChangeText = (Button) getView().findViewById(R.id.btnChangeText);
        tvFromWhere = (TextView) getView().findViewById(R.id.tvFromWhere);
        tvToWhere = (TextView) getView().findViewById(R.id.tvToWhere);
        btnCheckTwo = (Button) getView().findViewById(R.id.btnCheckTwo);


        lyCarModel.setOnClickListener(this);
        lyDateSelect.setOnClickListener(this);
        btnChangeText.setOnClickListener(this);
        tvFromWhere.setOnClickListener(this);
        tvToWhere.setOnClickListener(this);
        btnCheckTwo.setOnClickListener(this);
    }


    @Override
    public void onClick(View arg0) {
        Intent intent;
        switch (arg0.getId()) {
            case R.id.lyCarModel:
                showCarModelSelected();
                break;

            case R.id.lyDateSelect:
                intent = new Intent();
                intent.setClass(mContext, SelectCalendar.class);
                startActivityForResult(intent,3);
                break;

            case R.id.btnChangeText:
                changeWhere();
                break;

            case R.id.tvFromWhere:
                intent = new Intent();
                intent.setClass(mContext, SelectPlace.class);
                intent.putExtra("dataWhere","from");
                startActivityForResult(intent, 1);
                break;

            case R.id.tvToWhere:
                intent = new Intent();
                intent.setClass(mContext,SelectPlace.class);
                intent.putExtra("dataWhere","to");
                startActivityForResult(intent, 2);
                break;

            case R.id.btnCheckTwo:
                String fromStaEname = getStaEname(fromWhere);
                String toStaEname = getStaEname(toWhere);
                intent=new Intent();
                intent.setClass(this.getActivity(),ResultData.class);
                intent.putExtra("from_station", "SHH");
                intent.putExtra("to_station", "BJP");
                intent.putExtra("dateString", "2016-01-12");
                startActivity(intent);
                Log.i("seechange", fromWhere + fromStaEname + " " + toWhere + toStaEname + " " + dateString);
                break;

            default:
                break;
        }
    }

    private void changeWhere() {
        if (TAG_LAYOUT) {
            int[] location1 = new int[2];
            int[] location2 = new int[2];
            tvFromWhere.getLocationOnScreen(location1);
            tvToWhere.getLocationOnScreen(location2);
            int y1 = location1[1];
            int y2 = location2[1];

            ObjectAnimator.ofFloat(tvToWhere,"TranslationY",-(y2-y1)).setDuration(500).start();
            ObjectAnimator.ofFloat(tvFromWhere,"TranslationY",(y2-y1)).setDuration(500).start();
            TAG_LAYOUT = false;
            tvFromWhere.setId(R.id.tvToWhere);
            tvToWhere.setId(R.id.tvFromWhere);
            fromWhere = tvToWhere.getText().toString();
            toWhere = tvFromWhere.getText().toString();
            Log.i("seechange","fromWhere="+fromWhere+" toWhere="+toWhere);
        } else {
            ObjectAnimator.ofFloat(tvToWhere,"TranslationY",0).setDuration(500).start();
            ObjectAnimator.ofFloat(tvFromWhere,"TranslationY",0).setDuration(500).start();
            TAG_LAYOUT = true;
            tvFromWhere.setId(R.id.tvFromWhere);
            tvToWhere.setId(R.id.tvToWhere);
            fromWhere = tvFromWhere.getText().toString();
            toWhere = tvToWhere.getText().toString();
            Log.i("seechange", "fromWhere=" + fromWhere + " toWhere=" + toWhere);
        }
    }



    /**
     * 弹出dialog选择车型项
     */
    private void showCarModelSelected() {
        final String[] menu = new String[]{"高铁","动车/城际","Z直达","T特快","其他"};
        AlertDialog.Builder carModelDialog = new AlertDialog.Builder(mContext);
        carModelDialog.setTitle("请选择车型...").setMultiChoiceItems(menu, checkItems,
                new OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int which, boolean isChecked) {
                        checkItems[which] = isChecked;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String carResult = "";
                for(int i=0;i<checkItems.length;i++){
                    if(checkItems[i]){
                        carResult += menu[i] + ",";
                    }
                }
                tvCar.setText(carResult);
                Log.i("carResult", carResult+"");
                if(carResult.equals("高铁,动车/城际,Z直达,T特快,其他,")){
                    tvCar.setText("所有车型");
                }
//				Toast.makeText(mContext, "你选择了："+ result, Toast.LENGTH_LONG).show();
            }
        }).create();
        carModelDialog.show();
    }

    /**
     * 获取当前月日周
     */
    public void StringGetData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Year = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        Month = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        Day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        Week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(Week)){
            Week ="日";
        }else if("2".equals(Week)){
            Week ="一";
        }else if("3".equals(Week)){
            Week ="二";
        }else if("4".equals(Week)){
            Week ="三";
        }else if("5".equals(Week)){
            Week ="四";
        }else if("6".equals(Week)){
            Week ="五";
        }else if("7".equals(Week)){
            Week ="六";
        }
    }

    /**
     * 根据staName从数据库查询获取staEname
     * @param staName
     * @return staEname
     */
    private String getStaEname(String staName){
        String staEname = null;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(staFile,null);
        Cursor cursor = db.query("Stations",null,"staName='" +staName+ "'",null,null,null,null);
        while (cursor.moveToNext()){
            staEname = cursor.getString(cursor.getColumnIndexOrThrow("staEname"));
            Log.i("staEname",staEname+" getStaEname");
        }
        cursor.close();
        db.close();
        return staEname;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == getActivity().RESULT_OK ) {
                    if(TAG_LAYOUT){
                        tvFromWhere.setText(data.getStringExtra("dataFrom"));
                    }else{
                        tvToWhere.setText(data.getStringExtra("dataFrom"));
                    }
                    fromWhere = data.getStringExtra("dataFrom");
                    Log.i("seechange","dataFrom="+data.getStringExtra("dataFrom")+tvFromWhere.getId()+fromWhere);
                }
                break;

            case 2:
                if (resultCode == getActivity().RESULT_OK) {
                    if(TAG_LAYOUT){
                        tvToWhere.setText(data.getStringExtra("dataTo"));
                    }else{
                        tvFromWhere.setText(data.getStringExtra("dataTo"));
                    }
                    toWhere = data.getStringExtra("dataTo");
                    Log.i("seechange","dataTo="+data.getStringExtra("dataTo")+tvToWhere.getId()+toWhere);
                }
                break;

            case 3:
                if (resultCode == getActivity().RESULT_OK){
                    Month = data.getStringExtra("showMonth");
                    Day = data.getStringExtra("showDay");
                    Year = data.getStringExtra("showYear");
                    Week = data.getStringExtra("showWeek");
                    tvMonth.setText(Month + "月" + Day + "日");
                    tvWeek.setText("周" + Week);
                    dateString = Year+"-"+Month+"-"+Day;
                }
        }
    }
}
