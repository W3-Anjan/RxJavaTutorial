package com.anjan.rxjavatutorial.data.localstorage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anjan.rxjavatutorial.data.model.TaskEntity;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "tasks_db";

    public abstract TaskDao taskDao();
}
