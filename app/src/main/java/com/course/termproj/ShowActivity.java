package com.course.termproj;

import static com.course.termproj.DietDBManager.DIET_DB;
import static com.course.termproj.DietDBManager.DIET_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    int calories;
    ListView listView;
    EditText cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);
        calories = 0;
        String[] columns = new String[]{"_id", "IMAGE", "FOOD_NAME",
                "FOOD_QUANTITY", "FOOD_DATE", "FOOD_REVIEW", "LOCATION", "CALORIES"};
        c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null,
                null, null, null);

        MyAdapter adapter = new MyAdapter(this, c);

        listView = findViewById(R.id.listView);
        cal = (EditText) findViewById(R.id.cal);
        listView.setAdapter(adapter);

        if(c!=null) {
            try {
                if (c.moveToFirst()) {
                    do {
                        calories += Integer.parseInt(c.getString(7));
                    } while (c.moveToNext());
                }
            } finally {
                cal.setText("총 칼로리: "+ String.valueOf(calories));
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                c.moveToPosition(position);
                Intent intent = new Intent(ShowActivity.this, ContentActivity.class);
                intent.putExtra("IMAGE", c.getString(1));
                intent.putExtra("FOOD_NAME", c.getString(2));
                intent.putExtra("FOOD_QUANTITY", c.getString(3));
                intent.putExtra("FOOD_DATE", c.getString(4));
                intent.putExtra("FOOD_REVIEW", c.getString(5));
                intent.putExtra("LOCATION", c.getString(6));
                intent.putExtra("CALORIES", c.getString(7));
                startActivity(intent);
            }
        });
    }
}

