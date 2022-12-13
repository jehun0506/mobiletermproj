package com.course.termproj;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyAdapter extends CursorAdapter {

    public MyAdapter(Context context, Cursor cursor) {
        super(context,cursor, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        ImageView imageView = (ImageView)view.findViewById(R.id.thumbNail);
        TextView textView = (TextView)view.findViewById(R.id.date);

        imageView.setImageURI(Uri.parse(cursor.getString(1)));
        textView.setText(cursor.getString(4));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listview_custom, parent, false);
        return v;
    }

}
