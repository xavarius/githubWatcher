package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.model.GithubUserModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserDetailedView extends AppCompatActivity {

    final GithubUserModel mModel = GithubUserModel.getInstance();

    @Bind(R.id.detailed_user_name) protected TextView mTvUserName;
    @Bind(R.id.detailed_nbr_repos) protected TextView mTvNbrRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed_view);

        ButterKnife.bind(this);

        loadDetailedUserData(getIntent().getExtras().getString(UserDetailedView.class.getName()));
    }

    private void loadDetailedUserData(final String userName) {
        final GithubUser user = mModel.getUser(userName);
        mTvUserName.setText(user.getLogin() == null ? "its null" : user.getLogin());
        mTvNbrRepos.setText(String.format("%d",user.getPublicRepos()));
    }
}
