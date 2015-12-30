package com.rail.https;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.rail.dao.StationsDatabaseHelper;
import com.rail.interfaces.InterfaceToUseData;

import java.io.File;

/**
 * Created by RImpression on 2015/11/13.
 */
public class StationSearch implements InterfaceToUseData{

    private static Context mContext;

    public StationSearch(Context context) {
        this.mContext = context;
    }

    public void HttpToGetStationData() {

        String str=new String("https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?");
        HttpDoPostUtils.getHttpPost().execute(str,this);
    }

    private static void paraseJSONWithJSONObject(String respones, SQLiteDatabase db) {
        String respons = respones.substring(21, respones.length() - 1);
        String[] parsefirst = respons.split("@");
        //开启一个事务
        db.beginTransaction();
        for (int i = 0; i < parsefirst.length; i++) {
            String str = parsefirst[i];
            String[] parsesecond = str.split("\\|");
            insertToDatabase(parsesecond, db);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private static void insertToDatabase(String[] strings, SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put("staName", strings[1]);
        values.put("staEname", strings[2]);
        values.put("staCname", strings[3]);
        values.put("staJname", strings[4]);
        //db.insert("Stations", null, values);
        Cursor cursor = db.query("Stations", null, "staName='" + strings[1] + "'", null, null, null, null);
        if (cursor.getCount() == 0){
            db.insert("Stations",null,values);
            Log.i("getCount",cursor.getColumnCount()+strings[1]);
        }
        cursor.close();

//        db.delete("Stations","staName=?",new String[]{"北京北"});
//        db.delete("Stations","staName=?",new String[]{"上海"});

    }

    public void updateStation(){

    }

    @Override
    public void useData( Object s) {
        System.out.println(s+" i commit");
        StationsDatabaseHelper stationsDatabaseHelper=StationsDatabaseHelper.getStationDatabaseHelper(mContext,"Stations.db",null,1 );
       SQLiteDatabase sqLiteDatabase=stationsDatabaseHelper.getWritableDatabase();
        paraseJSONWithJSONObject(s.toString(), sqLiteDatabase);

    }
}
