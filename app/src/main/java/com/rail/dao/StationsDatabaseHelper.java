package com.rail.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.rail.activity.MainActivity;

import java.io.File;

/**
 * Created by RImpression on 2015/11/13.
 */
public class StationsDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREAT_STATIONS = "CREATE TABLE if not exists Stations("
            + "id integer primary key autoincrement, "
            + "staName text,"
            + "staEname text,"
            + "staCname text,"
            + "staJname text)";
    private static StationsDatabaseHelper stationsDatabaseHelper=null;
    private Context mContext;
    private StationsDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public static StationsDatabaseHelper getStationDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        if(stationsDatabaseHelper==null){
            stationsDatabaseHelper= new StationsDatabaseHelper(context,name,factory,version);
        }
        return stationsDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_STATIONS);
        Toast.makeText(mContext,"creat db success",Toast.LENGTH_SHORT).show();
        Log.i("database","creat db success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
