package com.example.testdesign.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdesign.utilities.ApiClient;
import com.example.testdesign.utilities.ApiInterface;
import com.example.testdesign.HomePage;
import com.example.testdesign.utilities.MyPreferences;
import com.example.testdesign.R;
import com.example.testdesign.models.SignIn;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView textView;
    Button login_button;
    EditText email, password;
    String str1, str2;
    String UserId;
    SharedPreferences sp;
    private MyPreferences myPreferences;


    SignInButton signInButton;
    private GoogleSignInClient signInClient;
    private static final int SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myPreferences = new MyPreferences(this);

        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login_button = findViewById(R.id.button);

       // firebaseAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.textView7);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = signInClient.getSignInIntent();
                startActivityForResult(intent, SIGN_IN);
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Please fill the details properly....", Toast.LENGTH_SHORT).show();
                } else {
                    str1 = email.getText().toString();
                    str2 = password.getText().toString();
                    getLogin(str1, str2);
                    myPreferences.writeLoginStatus(true);
                    finish();
                }
            }
        });

        //below code are for "some selective text" are clickable in a textview(i.e comlete string).
        textView = findViewById(R.id.textView2);
        String text = "Please fill E-mail and Pasword to login your app account.Sign Up";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(getApplicationContext(), SIGNUP.class);
                startActivity(i);
                Toast.makeText(Login.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(clickableSpan1, 57, 64, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void getLogin(String email, String pass) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SignIn> call = apiInterface.getSignIn(email, pass);
        call.enqueue(new Callback<SignIn>() {
            private static final String TAG = "abc";

            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("ok")) {
                        myPreferences.writeLoginStatus(true);
                        myPreferences.writeUsername(response.body().getSignInData().getName());

                        Toast.makeText(Login.this, "SignedIn successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i);
                        UserId = response.body().getSignInData().getId();
                        myPreferences.writeUserId(UserId);
                        Log.i(TAG, "aId  " + UserId);

                    } else
                        Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                Toast.makeText(Login.this, "SignIn failed due to server error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                email.setText(account.getEmail());
                password.setText(account.getId());

//                Intent intent=new Intent(getApplicationContext(),HomePage.class);
//                startActivity(intent);

                // Picasso.get().load(account.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile_image);
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        SharedPreferences sp=getSharedPreferences("MySharedPref",MODE_PRIVATE);
//        String s1=sp.getString("email_id","");
//        String s2=sp.getString("pwd_key","");
//
//        email.setText(s1);
//        password.setText(s2);
//    }
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        SharedPreferences sp2=getSharedPreferences("MySharedPref",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sp2.edit();
//        editor.putString("email_id",email.getText().toString());
//        editor.putString("pwd_key",password.getText().toString());
//        editor.apply();
//    }

}