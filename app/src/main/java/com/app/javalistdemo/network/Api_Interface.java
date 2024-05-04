package com.app.javalistdemo.network;

import com.app.javalistdemo.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Interface {
    @GET("users")
    Call<List<Users>> getUsers();

}
