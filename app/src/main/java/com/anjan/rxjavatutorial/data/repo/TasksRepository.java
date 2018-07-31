package com.anjan.rxjavatutorial.data.repo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.anjan.rxjavatutorial.data.model.TaskEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class TasksRepository implements TasksDataSource{

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksLocalDataSource;

    // Prevent direct instantiation.
    private TasksRepository(Context context) {
        mTasksLocalDataSource = new TasksLocalDataSource(context);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     * @return the {@link TasksRepository} instance
     */
    public static TasksRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(context);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
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
        return mTasksLocalDataSource.saveTask(task);
    }
}
