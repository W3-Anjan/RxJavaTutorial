package com.anjan.rxjavatutorial.data.localstorage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.anjan.rxjavatutorial.data.model.TaskEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Dao
public interface TaskDao {

    @Query("SELECT * FROM TaskEntity")
    Flowable<List<TaskEntity>> getTaskList();

    @Query("SELECT * FROM TaskEntity WHERE taskId = :storeIn ")
    Maybe<TaskEntity> getTaskByStore(String storeIn);

    @Query("SELECT * FROM TaskEntity LIMIT 1")
    Single<TaskEntity> getSingleTask();


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long insertTask(TaskEntity tasks);


}
