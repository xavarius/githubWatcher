package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.GithubStatisticModel;
import com.maciejmalak.githubstatistics.model.Owner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailedView extends AppCompatActivity {

    final GithubStatisticModel mModel = GithubStatisticModel.getInstance();

    @BindView(R.id.detailed_user_name) protected TextView mUserName;
//    @BindView(R.id.followers_on_github) protected TextView mFollowers;
//    @BindView(R.id.starred) protected TextView mStarred;
//    @BindView(R.id.following) protected TextView mFollowing;
    @BindView(R.id.detailed_nbr_repos) protected TextView mNbrRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed_view);

        ButterKnife.bind(this);

        loadDetailedUserData((Owner) getIntent().getExtras().getSerializable(UserDetailedView.class.getName()));
    }

    private void loadDetailedUserData(final Owner user) {
        mUserName.setText(user.getLogin());
//        mFollowers.setText(user.getFollowers());
//        mStarred.setText(user.getFollowing());
//        mFollowing.setText(user.getFollowing());
//        mNbrRepos.setText(user.getPublicRepos());
    }
}
