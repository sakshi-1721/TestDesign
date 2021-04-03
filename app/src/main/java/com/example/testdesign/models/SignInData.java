package com.example.testdesign.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInData {
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    @SerializedName("contact_number")
    private String contactNumber;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("email_id")
    private String emailId;
    @Expose
    @SerializedName("id")
    private String id;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
