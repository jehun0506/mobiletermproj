package com.course.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class ContentActivity extends AppCompatActivity {
    ImageView img;
    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edt4;
    EditText edt5;
    EditText edt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        img = (ImageView) findViewById(R.id.img);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt4 = (EditText) findViewById(R.id.edt4);
        edt5 = (EditText) findViewById(R.id.edt5);
        edt6 = (EditText) findViewById(R.id.edt6);

        Intent intent = getIntent();
        img.setImageURI(Uri.parse(intent.getExtras().getString("IMAGE")));
        edt1.setText("이름: "+intent.getExtras().getString("FOOD_NAME"));
        edt2.setText("수량: "+intent.getExtras().getString("FOOD_QUANTITY"));
        edt3.setText("식사날짜: "+intent.getExtras().getString("FOOD_DATE"));
        edt4.setText("후기: "+intent.getExtras().getString("FOOD_REVIEW"));
        edt5.setText("식사장소: "+intent.getExtras().getString("LOCATION"));
        edt6.setText("칼로리: "+intent.getExtras().getString("CALORIES"));

    }
}