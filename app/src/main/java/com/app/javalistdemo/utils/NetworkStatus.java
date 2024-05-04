package com.app.javalistdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
    public static Boolean isDeviceConnected(Context mContext){
        ConnectivityManager connectivityManager=(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork= connectivityManager.getActiveNetworkInfo();
        boolean isConnected=activeNetwork != null && activeNetwork.isConnectedOrConnecting();


        return  isConnected;

    }
}
