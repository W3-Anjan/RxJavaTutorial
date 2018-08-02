package com.anjan.rxjavatutorial.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.anjan.rxjavatutorial.R;
import com.anjan.rxjavatutorial.data.model.TaskEntity;
import com.anjan.rxjavatutorial.ui.addtask.AddTaskActivity;
import com.anjan.rxjavatutorial.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksActivity extends BaseActivity<TasksView, TasksPresenter> implements TasksView{

    @BindView(R.id.emptyRecyclerView)
    EmptyRecyclerView emptyRecyclerView;

    @BindView(R.id.empty_todo)
    View todo_list_empty_view;

    TasksAdapter tasksAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(TasksActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, AddTaskActivity.REQUEST_ADD_TASK);
        });


        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyRecyclerView.setEmptyView(todo_list_empty_view);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    protected TasksPresenter initPresenter() {
        return new TasksPresenter(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode, resultCode);
    }

    @Override
    public void showSuccessfullySavedMessage() {
        showMessage("task saved successfully");
    }

    @Override
    public void getTaskList(List<TaskEntity> tasks) {
        tasksAdapter = new TasksAdapter(tasks, this);
        emptyRecyclerView.setAdapter(tasksAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
