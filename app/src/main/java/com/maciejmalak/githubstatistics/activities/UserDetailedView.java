package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.GithubStatisticModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailedView extends AppCompatActivity {

    final GithubStatisticModel mModel = GithubStatisticModel.getInstance();

    @BindView(R.id.detailed_user_name) protected TextView mTvUserName;
    @BindView(R.id.detailed_nbr_repos) protected TextView mTvNbrRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed_view);

        ButterKnife.bind(this);

        loadDetailedUserData(getIntent().getExtras().getString(UserDetailedView.class.getName()));
    }

    private void loadDetailedUserData(final String userName) {
        mTvUserName.setText(userName);
        final int reposAmout = mModel.getRepositoryListForUser(userName).size();
        mTvNbrRepos.setText(String.format("%d",reposAmout));
    }
}
