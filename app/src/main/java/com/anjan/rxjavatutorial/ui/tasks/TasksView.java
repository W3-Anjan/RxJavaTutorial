package com.anjan.rxjavatutorial.ui.tasks;

import com.anjan.rxjavatutorial.data.model.TaskEntity;
import com.anjan.rxjavatutorial.ui.base.MvpView;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/2/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface TasksView extends MvpView {

    void showSuccessfullySavedMessage();

    void getTaskList(List<TaskEntity> tasks);
}
