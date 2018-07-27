package com.anjan.rxjavatutorial.observable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anjan.rxjavatutorial.AppConstant;
import com.anjan.rxjavatutorial.R;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Anjan Debnath on 7/27/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class FlowableActivity extends AppCompatActivity {

    private static final String TAG = FlowableActivity.class.getSimpleName();

    Button btnClick;
    TextView textDisplay;

    //region Flowable documentation
    /**
     * Observables produce stream to be observed by an Observer when it subscribes.
     * There are various types and ways this stream can be emitted
     * Items are to be emitted by controlling the producers emission(with backpressure).
     */
    //endregion Flowable documentation

    //region Flowable creation

        //region Simple Flowable
         Flowable<Integer> integerFlowable = Flowable.just(1,2,3,4);
         //endregion Simple Flowable

        //region Flowable from Observable
        Observable<Integer> integerObservable = Observable.just(1,2,3,4);
        Flowable<Integer> integerFlowableFromObservable = integerObservable.toFlowable(BackpressureStrategy.BUFFER);
        //endregion Flowable from Observable

        //region Flowable from FlowableOnSubscribe
        /**
         * Flowable that starts emitting events after the consumer subscribes to it.
         */
         Flowable<Integer> integerFlowableOnSubscriber
                = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER);
        //endregion Flowable from FlowableOnSubscribe

    //endregion Flowable creation

    //region Observer creation
    private SingleObserver<Integer> getObserver() {

        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                textDisplay.append(" onSuccess : value : " + value);
                textDisplay.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textDisplay.append(" onError : " + e.getMessage());
                textDisplay.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }
    //endregion Observer creation


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

    private void doSomeWork(){

        integerFlowable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(getObserver());
    }
}
