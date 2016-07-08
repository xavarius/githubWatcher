package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.DataStorage;
import com.maciejmalak.githubstatistics.model.Account;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountDetailsScreen extends AppCompatActivity {

    final DataStorage mModel = DataStorage.INSTANCE;

    @Bind(R.id.detailed_user_name) protected TextView mTvUserName;
    @Bind(R.id.detailed_nbr_repos) protected TextView mTvNbrRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed_view);

        ButterKnife.bind(this);

        loadDetailedUserData(getIntent().getExtras().getString(AccountDetailsScreen.class.getName()));
    }

    private void loadDetailedUserData(final String userName) {
        mTvUserName.setText(userName);
        final Account user = mModel.findUser(userName);
    }
}
