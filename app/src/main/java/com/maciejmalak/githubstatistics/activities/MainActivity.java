package com.maciejmalak.githubstatistics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.presenters.PresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {
    private Logger logger = new Logger(this.getClass().getSimpleName());

    private PresenterImpl mPresenter;

    /* Bindind views with ButterKnife*/
    @BindView(R.id.textview) protected TextView textView;
    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.fab) protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mPresenter = new PresenterImpl(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onNewUserAdded("xavarius");
            }
        });
    }

    @Override
    public void addNewUserToGrid(final GithubUser user) {
        /*add new list / recycler view element with user, where from fetch data? */
    }

    @Override
    public void startUserDetailedActivity() {
        final Intent startActivity = new Intent(this.getApplicationContext(), UserDetailedView.class);
        startActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity.putExtra(UserDetailedView.class.getName(), userName); TODO start activity based on previously choosed elemetn of list
        this.getApplicationContext().startActivity(startActivity);
    }
}
