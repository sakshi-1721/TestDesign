package com.example.testdesign.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.example.testdesign.R;

public class MyPreferences {
    private SharedPreferences mSharedPrefrences;
    private Context context;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";


    public MyPreferences(Context context) {
        this.mSharedPrefrences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
        this.context = context;
    }


    public void writeLoginStatus(boolean status) {

        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }
    public boolean readLoginStatus() {

        return mSharedPrefrences.getBoolean(context.getString(R.string.pref_login_status), false);
    }


    public void writeUsername(String uName) {

        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putString(context.getString(R.string.pref_login_uname), uName);
        editor.apply();
    }
    public String readUname() {

        return mSharedPrefrences.getString(context.getString(R.string.pref_login_uname), "uname");
    }


    public void writeUserId(String UserId) {

        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putString(context.getString(R.string.pref_login_UserId), UserId);
        editor.apply();
    }
    public String readUserId() {

        return mSharedPrefrences.getString(context.getString(R.string.pref_login_UserId), "UserId");
    }
}
