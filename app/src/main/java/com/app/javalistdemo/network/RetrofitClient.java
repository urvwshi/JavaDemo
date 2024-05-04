package com.app.javalistdemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    String SERVICE_BASE_URL="https://gorest.co.in/public/v2/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private  RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null){ //retrofit instance not yet created
            mInstance = new RetrofitClient();
        }

        return mInstance;

    }
    public Api_Interface get_Ers_API(){
        return retrofit.create(Api_Interface.class);
    }

}
