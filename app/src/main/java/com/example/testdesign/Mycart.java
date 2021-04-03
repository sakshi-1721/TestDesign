package com.example.testdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testdesign.utilities.MyPreferences;

public class Mycart extends AppCompatActivity {

    ImageView imageView;
    TextView tv1,tv2;

    MyPreferences myPreferences;
    private static String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);


        myPreferences=new MyPreferences(getApplicationContext());
        uId=myPreferences.readUserId();
    }
}