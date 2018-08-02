package com.anjan.rxjavatutorial.ui.addtask;

import android.content.Context;
import android.util.Log;

import com.anjan.rxjavatutorial.data.model.TaskEntity;
import com.anjan.rxjavatutorial.data.repo.TasksRepository;
import com.anjan.rxjavatutorial.ui.base.BasePresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anjan Debnath on 7/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class AddTaskPresenter extends BasePresenter<AddTaskView> {

    private TasksRepository mTasksRepository;
    private CompositeDisposable mCompositeDisposable;

    public AddTaskPresenter(Context context){
        mTasksRepository = TasksRepository.getInstance(context);
        mCompositeDisposable = new CompositeDisposable();
    }


    public void subscribe(){

    }

    public void unSubscribe(){
        mCompositeDisposable.clear();
    }

    public void saveTask(String title, String description){

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskTitle(title);
        taskEntity.setTaskDescription(description);

        //add Observable here
        mTasksRepository.saveTask(taskEntity)
            // Run on a background thread
            .subscribeOn(Schedulers.io())
            // Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::getInsertId, this::errorCode);
    }

    public void getInsertId(Long integer){
        Log.d("AddTask", "id::"+integer);
        if(getMvpView()!= null){
            getMvpView().backToPrevious();
        }
    }

    public void errorCode(Throwable e){
        Log.d("AddTask", "e"+ e.toString());
    }


}
