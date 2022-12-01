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
        if (c != null) {
            EditText editMultipleText = findViewById(R.id.editTest);
            editMultipleText.setText("");
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String IMAGE = c.getString(1);
                String FOOD_NAME = c.getString(2);
                String FOOD_QUANTITY = c.getString(3);
                String FOOD_DATE = c.getString(4);
                String FOOD_REVIEW = c.getString(5);
                String LOCATION = c.getString(6);

                editMultipleText.append("IMAGE: "+ IMAGE + "\nFOOD_NAME: " + FOOD_NAME + "\n FOOD_QUANTITY: " +
                        FOOD_QUANTITY + "\n FOOD_DATE: " + FOOD_DATE + "\n FOOD_REVIEW: " + FOOD_REVIEW + "\nLOCATION:" + LOCATION);
            }
            editMultipleText.append("\n Total : " + c.getCount());
            c.close();
        }

        ListView listView = findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, c);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                c.moveToPosition(position);
                String str = c.getString(c.getColumnIndexOrThrow("FOOD_NAME"));
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }

        });
        c.close();
    }
}

