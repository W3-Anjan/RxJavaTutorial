package com.anjan.rxjavatutorial.data.remoteservice;

import com.anjan.rxjavatutorial.data.model.StoreCoupons;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface CouponApi {
    @GET("coupons/")
    Observable<StoreCoupons> getCoupons(@Query("status") String status);
    @GET("storeOffers/")
    Observable<StoreCoupons> getStoreInfo();
    @GET("storeOffersNew/")
    Observable<StoreCoupons> getStoreInfoNew();

}
