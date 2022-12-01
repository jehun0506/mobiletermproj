package com.course.termproj;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.course.termproj";
    static final String URL = "content://" + PROVIDER_NAME + "/TEMP1234";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String _ID = "_id";
    static final String FOOD_NAME = "FOOD_NAME";
    static final String FOOD_QUANTITY = "FOOD_QUANTITY";
    static final String FOOD_DATE = "FOOD_DATE";
    static final String FOOD_REVIEW = "FOOD_REVIEW";
    static final String IMAGE = "IMAGE";
    static final String LOCATION = "LOCATION";

    public DietDBManager dbManager;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return dbManager.delete(selection, selectionArgs);
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowid = dbManager.insert(values);
        return null;
    }
    @Override
    public boolean onCreate() {
        dbManager = DietDBManager.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return dbManager.query(projection, selection, selectionArgs, null,
                null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}