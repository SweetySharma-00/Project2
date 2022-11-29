package com.example.project2.RetrofitAPI.models.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
