package com.app.javalistdemo.model;


import java.util.List;

public class UserResponse {


    public List<Users> userList;
    private Throwable error;
    private int status;
    private String message;

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserResponse(List<Users> userList) {
        this.userList = userList;
        this.error = null;
        this.status = 0;
        this.message = null;
    }
    public UserResponse(Throwable error){
        this.userList = null;
        this.error = error;
        this.status = 0;
        this.message = null;
    }

    public UserResponse(int status,String message){
        this.userList = null;
        this.error = null;
        this.status = status;
        this.message = message;
    }

    }
