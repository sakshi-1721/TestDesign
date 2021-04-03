package com.example.testdesign.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ShowProduct {

    @Expose
    @SerializedName("response")
    private String response;
    @Expose
    @SerializedName("data")
    private List<SignInData> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<SignInData> getData() {
        return data;
    }

    public void setData(List<SignInData> data) {
        this.data = data;
    }
}
