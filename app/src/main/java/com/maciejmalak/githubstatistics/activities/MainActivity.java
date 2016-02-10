package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.maciejmalak.githubstatistics.API.Requester;
import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.helpers.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Logger logger = new Logger(this.getClass().getSimpleName());

    private static Requester requester;

    @Bind(R.id.main_user_login) protected EditText tvUserLogin;
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
                final String userName = tvUserLogin.getText().toString();
                logger.logd("REQUEST SEND");
                if (userName != null && !userName.equals(""))
                requester.fetchUserDescription(userName);
            }
        });
    }
}
