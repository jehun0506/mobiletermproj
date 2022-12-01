package com.course.termproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyAdapter extends CursorAdapter {
    private final Context mContext;

    public MyAdapter(Context context, Cursor cursor) {
        super(context,cursor);
        mContext = context;
    }

    @Override

    public void bindView(View view, Context context, Cursor cursor){
        final ImageView imageView = (ImageView)view.findViewById(R.id.thumbNail);
        final TextView textView = (TextView)view.findViewById(R.id.date);
        Toast.makeText(context, "제발", Toast.LENGTH_SHORT).show();
        //imageView.setImageURI(Uri.parse(cursor.getString(1)));
        textView.setText(cursor.getString(3));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        Toast.makeText(context, "되나", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_show, parent, false);
        return v;
    }

}
