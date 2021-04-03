package com.example.testdesign.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sliders {

    @SerializedName("data")
    List<SliderData> data;

    @SerializedName("response")
    String response;

    public List<SliderData> getData() {
        return data;
    }

    public void setData(List<SliderData> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
