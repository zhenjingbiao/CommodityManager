package com.example.jingbiaozhen.commoditymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * 商品数据库
 **/

public class CommodityDBHelper extends SQLiteOpenHelper
{
    private static final String TAG = "CommodityDBHelper";

    // 创建Commodity表的语句
    public static final String CREATE_COMMODITY = "create table Commodity (" + "id integer primary key autoincrement,"
            + "username text," + "password text," + "process1 text," + "process2 text," + "name text," + "date text,"
            + "location text)";

    private Context mContext;

    public CommodityDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_COMMODITY);
        Log.d(TAG, "onCreate: 数据库创建成功");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
