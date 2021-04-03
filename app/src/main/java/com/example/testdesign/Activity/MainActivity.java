package com.example.testdesign.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testdesign.HomePage;
import com.example.testdesign.utilities.MyPreferences;
import com.example.testdesign.R;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT=1000;
private MyPreferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        myPreferences = new MyPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int SPLASH_DISPLAY_LENGTH=1000;
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (myPreferences.readLoginStatus() == true ){
                    Intent i = new Intent(MainActivity.this, HomePage.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}