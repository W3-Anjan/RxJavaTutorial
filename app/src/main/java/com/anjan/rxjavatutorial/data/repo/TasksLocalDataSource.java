package com.anjan.rxjavatutorial.data.repo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.anjan.rxjavatutorial.data.localstorage.AppDatabase;
import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.data.model.TaskEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.anjan.rxjavatutorial.data.localstorage.AppDatabase.DATABASE_NAME;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class TasksLocalDataSource implements TasksDataSource {

    private AppDatabase taskDb;

    TasksLocalDataSource(Context context){
        taskDb = Room.databaseBuilder(context,
                AppDatabase.class, DATABASE_NAME)
                .build();

    }

    @Override
    public Flowable<List<TaskEntity>> getTaskList() {
        return taskDb.taskDao().getTaskList();
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
    public Single<Long> saveTask(@NonNull final TaskEntity task) {

        /**
         * this will existing functionality to Rx
         */
        return Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return taskDb.taskDao().insertTask(task);
            }
        });
    }

    @Override
    public Observable<StoreCoupons> getCoupons(String status) {
        return null;
    }

    @Override
    public Observable<StoreCoupons> getStoreInfo() {
        return null;
    }
}
