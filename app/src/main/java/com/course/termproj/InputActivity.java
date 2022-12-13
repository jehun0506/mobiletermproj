package com.course.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputActivity extends AppCompatActivity {

    ImageView imageView;
    Uri uri;
    Button btnMap;
    EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        Intent receiveIntent = getIntent();
        String receiveAddress = receiveIntent.getStringExtra("address");
        addressText = (EditText) findViewById(R.id.addressText);
        addressText.setText(receiveAddress);
    }

    public void onClickButton1(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 1);
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1) {
            uri = data.getData();
        }
    }
    public void addDiet(View view) {
        ContentValues addValues = new ContentValues();

        addValues.put(MyContentProvider.IMAGE, uri.toString());
        addValues.put(MyContentProvider.FOOD_NAME,
                ((EditText)findViewById(R.id.editText1)).getText().toString());

        addValues.put(MyContentProvider.FOOD_QUANTITY,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        addValues.put(MyContentProvider.FOOD_DATE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        addValues.put(MyContentProvider.FOOD_REVIEW,
                ((EditText)findViewById(R.id.editText4)).getText().toString());
        addValues.put(MyContentProvider.CALORIES,
                ((EditText)findViewById(R.id.editText5)).getText().toString());
        addValues.put(MyContentProvider.LOCATION,
                ((EditText)findViewById(R.id.addressText)).getText().toString());
        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}