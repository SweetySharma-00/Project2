package com.example.project2.RetrofitAPI.api;

import com.example.project2.RetrofitAPI.models.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRetrofitContract {

//    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @FormUrlEncoded
    @POST("api/login")
    Observable<LoginResponse> Login(
            @Header("Client-Version") String clientVersion,
            @Header("Platform-Type") String platformType,
            @Header("Client-Type") String clientType,
            @Header("Firebase-Token") String firebaseToken,
            @Field("loginId") String loginId,
            @Field("password") String password
    );

}
