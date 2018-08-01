package com.anjan.rxjavatutorial.ui.getremotecoupon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anjan.rxjavatutorial.R;
import com.anjan.rxjavatutorial.data.model.Coupon;

import java.util.List;

class GetRemoteCouponsAdapter extends RecyclerView.Adapter<GetRemoteCouponsAdapter.ViewHolder> {

    private List<Coupon> couponList;
    private Context context;

    public GetRemoteCouponsAdapter(List<Coupon> cpnList, Context ctx) {
        couponList = cpnList;
        context = ctx;
    }

    @Override
    public GetRemoteCouponsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);

        GetRemoteCouponsAdapter.ViewHolder viewHolder = new GetRemoteCouponsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GetRemoteCouponsAdapter.ViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.store.setText(coupon.getStore());
        holder.coupon.setText(coupon.getCoupon());
        holder.expiry.setText(coupon.getExpiryDate());
        holder.code.setText(coupon.getCouponCode());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView store;
        public TextView coupon;
        public TextView expiry;
        public TextView code;

        public ViewHolder(View view) {
            super(view);

            store = (TextView) view.findViewById(R.id.store);
            coupon = (TextView) view.findViewById(R.id.coupon);
            expiry = (TextView) view.findViewById(R.id.expiry);
            code = (TextView) view.findViewById(R.id.coupon_code);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You chose coupon " + getAdapterPosition(),
                    Toast.LENGTH_LONG).show();
        }
    }
}