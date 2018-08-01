package com.anjan.rxjavatutorial.data.remoteservice;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public abstract class StoreService {

    public static final String BASE_URL = "http://www.zoftino.com/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getCouponClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
