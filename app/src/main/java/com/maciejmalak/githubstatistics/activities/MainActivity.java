package com.maciejmalak.githubstatistics.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.adapters.UsersRecycleAdapter;
import com.maciejmalak.githubstatistics.model.Owner;
import com.maciejmalak.githubstatistics.presenters.PresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {
    private static final int NUMBER_OF_COLUMS = 2;

    private PresenterImpl mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private UsersRecycleAdapter mAdapter;

    @BindView(R.id.toolbar) protected Toolbar mToolbar;
    @BindView(R.id.fab) protected FloatingActionButton mFab;
    @BindView(R.id.user_recycler_view) protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLayoutManager = new GridLayoutManager(this, NUMBER_OF_COLUMS);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new UsersRecycleAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new PresenterImpl(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onNewUserAdded("MaciejSzaflik");
            }
        });
    }

    @Override
    public void addNewUserToGrid(final Owner user) {
        mAdapter.add(user);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void startUserDetailedActivity() {
//        TODO start activity based on previously choosed elemetn of list
        /*final Intent startActivity = new Intent(this.getApplicationContext(), UserDetailedView.class);
        startActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra(UserDetailedView.class.getName(), userName);
        this.getApplicationContext().startActivity(startActivity);*/
    }
}