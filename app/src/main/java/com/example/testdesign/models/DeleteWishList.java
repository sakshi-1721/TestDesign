package com.example.testdesign.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteWishList {

    @Expose
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}