package com.example.testdesign.models;

import com.example.testdesign.ProductData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {

   @SerializedName("data")
   List<ProductData> data;

   @SerializedName("response")
    String response;

    public List<ProductData> getData() {
        return data;
    }

    public void setData(List<ProductData> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}




