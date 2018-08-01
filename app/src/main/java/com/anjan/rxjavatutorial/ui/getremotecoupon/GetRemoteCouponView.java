package com.anjan.rxjavatutorial.ui.getremotecoupon;

import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.ui.base.MvpView;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface GetRemoteCouponView extends MvpView {

    void showCoupons(StoreCoupons storeCoupons);

    void showError();
}
