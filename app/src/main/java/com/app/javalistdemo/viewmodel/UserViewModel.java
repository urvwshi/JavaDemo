package com.app.javalistdemo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.annotation.NonNull;

import com.app.javalistdemo.model.UserResponse;
import com.app.javalistdemo.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private MediatorLiveData<UserResponse> userResponseMediatorLiveData;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userResponseMediatorLiveData = new MediatorLiveData<>();
        userRepository = new UserRepository();
    }

    public LiveData<UserResponse> getListUsers() {
        userResponseMediatorLiveData.addSource(userRepository.getUsers(), userResponse -> userResponseMediatorLiveData.setValue(userResponse));
        return userResponseMediatorLiveData;
    }
}
