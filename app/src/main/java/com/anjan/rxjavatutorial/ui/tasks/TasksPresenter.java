package com.anjan.rxjavatutorial.ui.tasks;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.anjan.rxjavatutorial.data.model.TaskEntity;
import com.anjan.rxjavatutorial.data.repo.TasksRepository;
import com.anjan.rxjavatutorial.ui.addtask.AddTaskActivity;
import com.anjan.rxjavatutorial.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anjan Debnath on 8/2/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class TasksPresenter extends BasePresenter<TasksView> {

    private CompositeDisposable mCompositeDisposable;
    private TasksRepository mTasksRepository;


    public TasksPresenter(Context context){

        mTasksRepository = TasksRepository.getInstance(context);
        mCompositeDisposable = new CompositeDisposable();
    }


    public void result(int requestCode, int resultCode) {
        // If a task was successfully added, show snackbar
        if (AddTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            getMvpView().showSuccessfullySavedMessage();
        }
    }

    public void subscribe(){
        loadTasks();
    }


    public void unSubscribe(){
        mCompositeDisposable.clear();
    }


    public void loadTasks() {

        mCompositeDisposable.clear();
        Disposable disposable = mTasksRepository
                .getTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::processTasks, this::showLoadingTasksError);

        mCompositeDisposable.add(disposable);
    }

    private void showLoadingTasksError(Throwable throwable) {

    }

    private void processTasks(@NonNull List<TaskEntity> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            //processEmptyTasks();
        } else {
            // Show the list of tasks
            getMvpView().getTaskList(tasks);
        }
    }


}
