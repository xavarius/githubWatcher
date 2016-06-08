package com.maciejmalak.githubstatistics.fragments;


import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.DisplayMetrics;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.activities.HomeView;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.presenters.GridItemPresenter;
import com.maciejmalak.githubstatistics.presenters.Presenter;
import com.maciejmalak.githubstatistics.presenters.PresenterImpl;

public class MainTvFragment extends BrowseFragment implements HomeView {
    private Logger logger = new Logger(this.getClass().getSimpleName());

    private BackgroundManager mBackgroundManager;
    private DisplayMetrics mMetrics;
    private Presenter mPresenter;
    private ArrayObjectAdapter mRowsAdapter;
    private ArrayObjectAdapter mFirstRowAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new PresenterImpl(this);
        prepareBackgroundManager();
        setupUIElements();
        loadRows();
    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());

        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        // set headers background color
        setBrandColor(getResources().getColor(android.R.color.holo_blue_light));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(android.R.color.white));
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        final GridItemPresenter mGridItemPresenter = new GridItemPresenter();
        mFirstRowAdapter = new ArrayObjectAdapter(mGridItemPresenter);

        final HeaderItem headerItem = new HeaderItem(0, getResources().getString(R.string.header_users));
        final ListRow usersCategory = new ListRow(headerItem, mFirstRowAdapter);
        mRowsAdapter.add(usersCategory);

        setAdapter(mRowsAdapter);

//        simulate adding new user by app user
        mPresenter.onNewUserAdded("xavarius");
        mPresenter.onNewUserAdded("xavarius");
        mPresenter.onNewUserAdded("xavarius");
        mPresenter.onNewUserAdded("xavarius");
    }

    @Override
    public void addNewUserToGrid(final GithubUser user) {
        mFirstRowAdapter.add(user);
        mFirstRowAdapter.notifyArrayItemRangeChanged(mFirstRowAdapter.size() - 1, 1);
    }

    @Override
    public void startUserDetailedActivity() {}
}