package com.course.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
        //imageView = (ImageView)findViewById(R.id.iv);
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
            Toast.makeText(getBaseContext(),
                    uri_path(uri), Toast.LENGTH_LONG).show();
        }
    }
    public void addDiet(View view) {
        ContentValues addValues = new ContentValues();

        addValues.put(MyContentProvider.IMAGE, uri_path(uri));
        addValues.put(MyContentProvider.FOOD_NAME,
                ((EditText)findViewById(R.id.editText1)).getText().toString());

        addValues.put(MyContentProvider.FOOD_QUANTITY,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        addValues.put(MyContentProvider.FOOD_DATE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        addValues.put(MyContentProvider.FOOD_REVIEW,
                ((EditText)findViewById(R.id.editText4)).getText().toString());
        addValues.put(MyContentProvider.LOCATION,
                ((EditText)findViewById(R.id.addressText)).getText().toString());
        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        Toast.makeText(getBaseContext(),
                uri_path(uri), Toast.LENGTH_LONG).show();
    }

    public void getDiets(View view) {
        String[] columns = new String[]{"_id", "IMAGE", "FOOD_NAME",
                "FOOD_QUANTITY", "FOOD_DATE", "FOOD_REVIEW", "LOCATION"};
        Cursor c =
                getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null,
                        null, null, null);
        if (c != null) {
            EditText editMultipleText = findViewById(R.id.editText5);
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
            editMultipleText.append("\n\n Total : " + c.getCount());
            c.close();
        }
    }
    private String uri_path(Uri uri){
        String res = null;
        String[] image_data = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri,image_data,null, null, null);
        if(cursor.moveToFirst()){
            int col = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(col);
        }
        cursor.close();
        return res;
    }
}