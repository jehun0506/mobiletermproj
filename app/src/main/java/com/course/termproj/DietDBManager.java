package com.course.termproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DietDBManager extends SQLiteOpenHelper {
    static final String DIET_DB = "TEMP1234.db";
    static final String DIET_TABLE = "TEMP1234";
    Context context = null;
    private static DietDBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + DIET_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, IMAGE TEXT NOT NULL,"
            + " FOOD_NAME TEXT NOT NULL, FOOD_QUANTITY TEXT NOT NULL, FOOD_DATE TEXT NOT NULL, FOOD_REVIEW TEXT NOT NULL, LOCATION TEXT NOT NULL);";

    public DietDBManager(Context context, String dbName,
                         SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    public static DietDBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new DietDBManager(context, DIET_DB, null, 1);
        }
        return dbManager;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }
    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(DIET_TABLE, null, addValue);
    }
    public Cursor query(String [] columns, String selection, String[]
            selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(DIET_TABLE, columns,
                selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(DIET_TABLE, whereClause,
                whereArgs);
    }
}
