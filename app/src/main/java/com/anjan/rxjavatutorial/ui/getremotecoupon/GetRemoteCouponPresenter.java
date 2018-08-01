package com.anjan.rxjavatutorial.ui.getremotecoupon;

import android.content.Context;

import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.data.remoteservice.CouponApi;
import com.anjan.rxjavatutorial.data.remoteservice.StoreService;
import com.anjan.rxjavatutorial.data.repo.TasksRepository;
import com.anjan.rxjavatutorial.ui.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class GetRemoteCouponPresenter extends BasePresenter<GetRemoteCouponView> {


    private TasksRepository mTasksRepository;
    private CompositeDisposable mCompositeDisposable;

    public GetRemoteCouponPresenter(Context context){
        mTasksRepository = TasksRepository.getInstance(context);
        mCompositeDisposable = new CompositeDisposable();
    }

    //single api call using retrofit and rxjava
    public void getCouponData(){

        mTasksRepository.getCoupons("topcoupons")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);

    }

    //two Retrofit service calls execute parallel using RxJava
    public void getStoreCouponData(){

        Observable.just(StoreService.getCouponClient().create(CouponApi.class))
                .subscribeOn(Schedulers.computation())
                .flatMap(s -> {

                    Observable<StoreCoupons> couponsObservable
                            = s.getCoupons("topcoupons")
                            .subscribeOn(Schedulers.io());

                    Observable<StoreCoupons> storeInfoObservable
                            = s.getStoreInfo()
                            .subscribeOn(Schedulers.io());

                    return Observable.concatArray(couponsObservable, storeInfoObservable);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);


    }

    private void handleResults(StoreCoupons storeCoupons){
        if(getMvpView()!= null){
            getMvpView().showCoupons(storeCoupons);
        }
    }

    private void handleError(Throwable t){
        if(getMvpView()!= null){
            getMvpView().showError();
        }
    }
}
