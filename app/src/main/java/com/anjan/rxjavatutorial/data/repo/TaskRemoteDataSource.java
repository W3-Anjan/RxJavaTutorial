package com.anjan.rxjavatutorial.data.repo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.data.model.TaskEntity;
import com.anjan.rxjavatutorial.data.remoteservice.CouponApi;
import com.anjan.rxjavatutorial.data.remoteservice.StoreService;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class TaskRemoteDataSource implements TasksDataSource {


    Retrofit retrofit;

    TaskRemoteDataSource(Context context){
        retrofit = StoreService.getCouponClient();
    }

    @Override
    public Flowable<List<TaskEntity>> getTaskList() {
        return null;
    }

    @Override
    public Maybe<TaskEntity> getTaskById(String taskId) {
        return null;
    }

    @Override
    public Single<TaskEntity> getSingleTask() {
        return null;
    }

    @Override
    public Single<Long> saveTask(@NonNull TaskEntity task) {
        return null;
    }

    @Override
    public Observable<StoreCoupons> getCoupons(String status) {
        return retrofit.create(CouponApi.class).getCoupons(status);
    }

    @Override
    public Observable<StoreCoupons> getStoreInfo() {
        return null;
    }
}
