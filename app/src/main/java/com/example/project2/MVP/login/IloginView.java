package com.example.project2.MVP.login;

import com.example.project2.RetrofitAPI.models.response.LoginResponse;

import retrofit2.http.Field;
import retrofit2.http.Header;

public interface IloginView {

    void setResponse(LoginResponse loginResponse);
    void setError(Throwable error);
    String getClientVersion();
     String getPlatformType();
    String getClientType();
     String getFirebaseToken();
     String getLoginId();
     String getPassword();
}
