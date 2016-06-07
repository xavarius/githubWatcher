package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.api.Requester;
import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.helpers.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Logger logger = new Logger(this.getClass().getSimpleName());

    private Requester requester;

    /* Bingind views with ButterKnife*/
    @Bind(R.id.textview) protected TextView textView;
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.fab) protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        requester = new Requester(this.getApplicationContext());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.logd("REQUEST SEND");
                requester.requestRepositoryForUser("xavarius"); // for now dummy user == I'm the dummy user.
            }
        });
    }
}
