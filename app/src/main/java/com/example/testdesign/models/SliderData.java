package com.example.testdesign.models;

import com.google.gson.annotations.SerializedName;

public class SliderData {
    @SerializedName("id")
    String id;

    @SerializedName("subtitle_text")
    String subtitle_text;

    @SerializedName("photo")
    String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubtitle_text() {
        return subtitle_text;
    }

    public void setSubtitle_text(String subtitle_text) {
        this.subtitle_text = subtitle_text;
    }
}
