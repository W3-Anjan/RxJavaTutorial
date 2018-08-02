package com.anjan.rxjavatutorial.ui.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anjan.rxjavatutorial.R;
import com.anjan.rxjavatutorial.data.model.TaskEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/2/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder>{

    private List<TaskEntity> taskLIst;
    private Context context;

    public TasksAdapter(List<TaskEntity> cpnList, Context ctx) {
        taskLIst = cpnList;
        context = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);

        TasksAdapter.ViewHolder viewHolder = new TasksAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TaskEntity taskEntity = taskLIst.get(position);
        holder.store.setText(taskEntity.getTaskTitle());
        holder.coupon.setText(taskEntity.getTaskDescription());

    }

    @Override
    public int getItemCount() {
        return taskLIst.size();
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
