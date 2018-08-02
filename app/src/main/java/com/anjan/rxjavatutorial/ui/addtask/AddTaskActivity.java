package com.anjan.rxjavatutorial.ui.addtask;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.anjan.rxjavatutorial.R;
import com.anjan.rxjavatutorial.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView{

    public static final int REQUEST_ADD_TASK = 1;

    @BindView(R.id.add_task_title)
    EditText addTask;

    @BindView(R.id.add_task_description)
    EditText addDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> presenter.saveTask(addTask.getEditableText().toString(), addDetail.getEditableText().toString()));
    }

    @Override
    protected AddTaskPresenter initPresenter() {
        return new AddTaskPresenter(AddTaskActivity.this);
    }

    @Override
    protected void onDestroy() {
        //dispose subscriptions
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void backToPrevious() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
