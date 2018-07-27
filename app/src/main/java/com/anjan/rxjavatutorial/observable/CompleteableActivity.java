package com.anjan.rxjavatutorial.observable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anjan.rxjavatutorial.AppConstant;
import com.anjan.rxjavatutorial.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anjan Debnath on 7/27/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class CompleteableActivity extends AppCompatActivity {

    private static final String TAG = FlowableActivity.class.getSimpleName();

    Button btnClick;
    TextView textDisplay;

    //region Create CompletableObservable
    Completable completable = Completable.timer(1000, TimeUnit.MILLISECONDS);
    //endregion Create CompletableObservable


    //region create CompletableObserver
    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onComplete() {
                textDisplay.append(" onComplete");
                textDisplay.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }

            @Override
            public void onError(Throwable e) {
                textDisplay.append(" onError : " + e.getMessage());
                textDisplay.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }
    //endregion completable observer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.buttonClick);
        textDisplay = findViewById(R.id.textSubscriber);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
    }

    /*
     * simple example using CompletableObserver
     */
    private void doSomeWork() {
        completable
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());
    }
}
