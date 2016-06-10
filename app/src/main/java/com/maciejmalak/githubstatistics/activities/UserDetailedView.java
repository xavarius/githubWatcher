package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.Owner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailedView extends AppCompatActivity {

    @BindView(R.id.detailed_user_name) protected TextView mUserName;
    @BindView(R.id.detailed_nbr_repos) protected TextView mNbrRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed_view);

        ButterKnife.bind(this);
        final Owner user = (Owner) getIntent().getExtras().getSerializable(UserDetailedView.class.getName());
        if (user != null) loadDetailedUserData(user);
//        else TODO
    }

    private void loadDetailedUserData(final Owner user) {
        mUserName.setText(user.getLogin());
        mNbrRepos.setText(user.getPublicRepos());
    }
}
