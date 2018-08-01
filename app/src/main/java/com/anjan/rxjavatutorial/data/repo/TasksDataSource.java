package com.anjan.rxjavatutorial.data.repo;


import android.support.annotation.NonNull;

import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.data.model.TaskEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface TasksDataSource {

    //region local storage
    Flowable<List<TaskEntity>> getTaskList();
    Maybe<TaskEntity> getTaskById(String taskId);
    Single<TaskEntity> getSingleTask();
    Single<Long> saveTask(@NonNull TaskEntity task);
    //endregion local storage


    //region remote service
    Observable<StoreCoupons> getCoupons(String status);
    Observable<StoreCoupons> getStoreInfo();
    //endregion remote service




}
