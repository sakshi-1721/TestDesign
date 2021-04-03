package com.example.testdesign;

import com.google.gson.annotations.SerializedName;

public class ProductData {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("image")
    String thumbnail;
    @SerializedName("old_price")
    int old_price;
    @SerializedName("new_price")
    int new_price;
    @SerializedName("discount_percentage")
    String discount_percentage;
    @SerializedName("description")
    String description;
    @SerializedName("category")
    String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getNew_price() {
        return new_price;
    }

    public void setNew_price(int new_price) {
        this.new_price = new_price;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(String discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
