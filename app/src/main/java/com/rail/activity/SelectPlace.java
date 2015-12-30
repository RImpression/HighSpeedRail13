package com.rail.activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rail.adapter.City_gridAdapter;
import com.rail.adapter.City_listAdapter;
import com.rail.dao.DBManager;
import com.rail.myview.SideBar;
import com.rail.utils.PingyinComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class SelectPlace extends AppCompatActivity {

    private static final String PACKAGE_NAME = "com.rail.activity";
    private File citFile = new File("/data/data/" + PACKAGE_NAME + "/citys.db");
    private File staFile = new File("/data/data/" + PACKAGE_NAME + "/databases/Stations.db");

    private ArrayList<String> citset = new ArrayList<String>();
    private ArrayList<String> staset = new ArrayList<String>();

    private GridView gvSreachStation;
    private ListView lvCityChar;
    private RelativeLayout Relative_cityname;

    int CITYS = R.raw.citys;
    public DBManager dbManager;
    private EditText etSearch;
    private ArrayAdapter<String> sta_adapter;
    private City_gridAdapter cityGridAdapter;
    public String dataWhrer;
    private PingyinComparator pingyinComparator = new PingyinComparator();
    private SideBar sidebar;
    public static String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpalce);
        dataWhrer = getIntent().getStringExtra("dataWhere");
        System.out.println(dataWhrer);
        initToolBar();
        importDBFile();
        setSelectTextChange();
        initView();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 根据搜索栏得到车站
     */
    private void setSelectTextChange() {
        etSearch = (EditText) findViewById(R.id.etSearch);
        Relative_cityname = (RelativeLayout) findViewById(R.id.Relative_cityname);

        gvSreachStation = (GridView) findViewById(R.id.gvSearchStation);
        gvSreachStation.setOnItemClickListener(new SelectSearch());

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    gvSreachStation.setVisibility(View.GONE);
                    Relative_cityname.setVisibility(View.VISIBLE);
                } else {
                    Relative_cityname.setVisibility(View.GONE);
                    gvSreachStation.setVisibility(View.VISIBLE);
                    refreshShow(s);
                }
            }
        });
    }

    /**
     * 根据搜索栏的内容刷新下面屏幕显示的内容
     * @param s
     */
    private void refreshShow(Editable s) {
//        sta_adapter = new ArrayAdapter<String>(this,android.R.layout
//                .simple_spinner_item,getStaset(s));
        cityGridAdapter = new City_gridAdapter((ArrayList<String>) getStaset(s),SelectPlace.this);
        gvSreachStation.setAdapter(cityGridAdapter);
        cityGridAdapter.notifyDataSetChanged();

    }

    private void initView() {
        lvCityChar = (ListView) this.findViewById(R.id.lv_cityname);
        City_listAdapter city_listAdapter = new City_listAdapter(getCitSet(), this, dataWhrer);
        lvCityChar.setAdapter(city_listAdapter);
        setSideBar();

    }

    private void importDBFile() {
        dbManager = new DBManager(this);
        dbManager.openDatabase(CITYS, "/data/data/" + PACKAGE_NAME + "/citys.db");
    }

    /**
     * 侧边栏的设置
     */
    private void setSideBar(){
        sidebar = (SideBar) this.findViewById(R.id.cityiew_sidebar);
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                char[] a = s.toCharArray();
                int position = a[0] - 63;
                lvCityChar.setSelection(position);
            }
        });
    }


    //选择改变城市状态
    public class SelectCity implements OnItemClickListener {
        public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
            String stationName = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent();
            if (dataWhrer.equals("from")) {
                intent.putExtra("dataFrom", stationName);
            }else if (dataWhrer.equals("to"))
                setResult(RESULT_OK,intent);{
                intent.putExtra("dataTo",stationName);
            }
            finish();
        }

    }

    //选择改变车站状态
    public class SelectSearch implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String stationName = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent();
            if (dataWhrer.equals("from")) {
                intent.putExtra("dataFrom",stationName);
            }else if (dataWhrer.equals("to")) {
                intent.putExtra("dataTo",stationName);
            }
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    //城市集合
    public ArrayList<String> getCitSet() {
        //清空城市集合
        citset.clear();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(citFile, null);
        Cursor cursor = db.query("citys", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            String pro = cursor.getString(cursor.getColumnIndexOrThrow("city_name"));
            citset.add(pro);
        }

        //城市集合排序
        Collections.sort(citset, pingyinComparator);
        for(int i = 0; i < citset.size(); i++){
            System.out.println(citset.get(i));
        }
        cursor.close();
        db.close();
        return citset;
    }

    //车站集合
    public List<String> getStaset(Editable etEnter) {
        staset.clear();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(staFile, null);
        Cursor cursor = db.query("Stations", null, "staName LIKE '" + etEnter + "%'"
                + " OR staCname LIKE '" + etEnter + "%'", null, null, null, null);
        while (cursor.moveToNext()) {
            String sta = cursor.getString(cursor.getColumnIndexOrThrow("staName"));
            staset.add(sta);
        }
        cursor.close();
        db.close();
        return staset;
    }


    //重写返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(Relative_cityname.getVisibility() == View.GONE) {
            Relative_cityname.setVisibility(View.VISIBLE);
            gvSreachStation.setVisibility(View.GONE);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

}
