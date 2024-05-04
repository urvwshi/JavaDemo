package com.app.javalistdemo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.javalistdemo.model.UserResponse;
import com.app.javalistdemo.model.Users;
import com.app.javalistdemo.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public LiveData<UserResponse> getUsers() {
        final MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();
        Call<List<Users>> call = RetrofitClient.getInstance().get_Ers_API().getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                if (response.isSuccessful()) {

                    userResponseMutableLiveData.postValue(new UserResponse(response.body()));
                } else if (response.code() == 404) {
                    userResponseMutableLiveData.postValue(new UserResponse(response.code(), "Results not found"));

                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                userResponseMutableLiveData.postValue(new UserResponse(t));


            }
        });

        return userResponseMutableLiveData;
    }


}
