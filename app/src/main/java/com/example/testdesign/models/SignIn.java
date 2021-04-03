package com.example.testdesign.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignIn {



    @Expose
    @SerializedName("response")
    private String response;
    @Expose
    @SerializedName("data")
    private SignInData signInData;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public SignInData getSignInData() {
        return signInData;
    }

    public void setSignInData(SignInData signInData) {
        this.signInData = signInData;
    }

}
