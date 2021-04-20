package com.anjan.rxjavatutorial.ui.getremotecoupon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anjan.rxjavatutorial.R;
import com.anjan.rxjavatutorial.data.model.StoreCoupons;
import com.anjan.rxjavatutorial.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anjan Debnath on 8/1/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class GetRemoteCouponActivity extends BaseActivity<GetRemoteCouponView, GetRemoteCouponPresenter> implements GetRemoteCouponView {

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.coupon_count)
    TextView couponCount;
    @BindView(R.id.max_cashback)
    TextView maxCashback;
    @BindView(R.id.coupon_recycler_view)
    RecyclerView couponRecyclerView;

    @BindView(R.id.show_top_store)
    Button buttonTopScore;
    @BindView(R.id.show_cupon)
    Button buttonShowCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cupon);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set layout manager for recyclerView
        RecyclerView.LayoutManager couponLayoutManager = new LinearLayoutManager(this);
        couponRecyclerView.setLayoutManager(couponLayoutManager);

        buttonShowCoupon.setOnClickListener(v -> presenter.getCouponData());

        buttonTopScore.setOnClickListener(v -> presenter.getStoreCouponData());

        /**
         * GIt breaking change related
         * major release
         * breaking change
         * breaking change
         *
         * commit for patch
         * commit for feature release
         * commit for breaking change
         */

    }

    @Override
    protected GetRemoteCouponPresenter initPresenter() {
        return new GetRemoteCouponPresenter(this);
    }

    @Override
    public void showCoupons(StoreCoupons storeCoupons) {

        if(storeCoupons.getCoupons() != null){
            GetRemoteCouponsAdapter ca = new GetRemoteCouponsAdapter(storeCoupons.getCoupons(), GetRemoteCouponActivity.this);
            couponRecyclerView.setAdapter(ca);
        }else{
            storeName.setText(storeCoupons.getStore());
            couponCount.setText(storeCoupons.getTotalCoupons());
            maxCashback.setText(storeCoupons.getMaxCashback());
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, "ERROR IN GETTING COUPONS",
                Toast.LENGTH_LONG).show();
    }
}
