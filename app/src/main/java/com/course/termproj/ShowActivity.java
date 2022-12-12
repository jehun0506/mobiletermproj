package com.course.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {
    Cursor c;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        String[] columns = new String[]{"_id", "IMAGE", "FOOD_NAME",
                "FOOD_QUANTITY", "FOOD_DATE", "FOOD_REVIEW", "LOCATION"};
        c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null,
                null, null, null);

        MyAdapter adapter = new MyAdapter(this, c);

        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                c.moveToPosition(position);
                String str = c.getString(c.getColumnIndexOrThrow("FOOD_NAME"));
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }

        });
    }
}

