package com.rail.dao;

/**
 * Created by RImpression on 2015/11/13.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
    private final int BUFFER_SIZE = 4000;
    private SQLiteDatabase database;
    private Context  context;

    public DBManager(Context context) {
        this.context = context;
    }

    public void openDatabase(int soruces,String dbfile) {
        this.database = this.openDatabase(dbfile,soruces);
    }

    private SQLiteDatabase openDatabase(String dbfile,int soruces) {
        try {
            if(!(new File(dbfile).exists())) {
                InputStream is = this.context.getResources().openRawResource(soruces);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while((count = is.read(buffer)) > 0) {
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return db;
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        }catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }
    public void closeDatabase() {
        this.database.close();
    }
}