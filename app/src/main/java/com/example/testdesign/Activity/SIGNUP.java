package com.example.testdesign.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdesign.utilities.ApiClient;
import com.example.testdesign.utilities.ApiInterface;
import com.example.testdesign.R;
import com.example.testdesign.models.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SIGNUP extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText username,email,password,phone;
    String str1,str2,str3,str4;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_i_g_n_u_p);

        textView=findViewById(R.id.textView2);

        username=findViewById(R.id.editText);
        email=findViewById(R.id.editText3);
        password=findViewById(R.id.editText2);
        phone=findViewById(R.id.editText4);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(username.getText().toString().isEmpty()||email.getText().toString().isEmpty()||password.getText().toString().isEmpty()||phone.getText().toString().isEmpty())
                {
                    Toast.makeText(SIGNUP.this, "Please fill all the details properly..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    str1=username.getText().toString();
                    str2=email.getText().toString();
                    str3=password.getText().toString();
                    str4=phone.getText().toString();
                    getSignUp(str2,str3,str1,str4);
                }

            }
        });

        String text="Do you have any account?.Sign In";
        SpannableString ss=new SpannableString(text);
        ClickableSpan clickableSpan1=new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View widget)
            {
                Intent i=new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                Toast.makeText(SIGNUP.this,"clicked",Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(clickableSpan1,25,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void getSignUp(String email_id, String password, String name, String contact_number)
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call=apiInterface.getRegister(email_id,password,name,contact_number);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response)
            {
                if(response.isSuccessful())
                {
                    if (response.body().getResponse().equals("false"))
                    {
                        Toast.makeText(SIGNUP.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(SIGNUP.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t)
            {
                Toast.makeText(SIGNUP.this, "Registration Failed due to server error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}