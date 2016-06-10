package com.maciejmalak.githubstatistics.fragments;


import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.util.DisplayMetrics;

import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.activities.HomeView;
import com.maciejmalak.githubstatistics.adapters.UsersArrayObjectAdapter;
import com.maciejmalak.githubstatistics.model.Owner;
import com.maciejmalak.githubstatistics.presenters.GridItemPresenter;
import com.maciejmalak.githubstatistics.presenters.Presenter;
import com.maciejmalak.githubstatistics.presenters.PresenterImpl;

public class MainTvFragment extends BrowseFragment implements HomeView {
    private static int CATEGORY_COUNTER = 0;
    private Presenter mPresenter;
    private ArrayObjectAdapter mRowsAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new PresenterImpl(this);
        prepareBackgroundManager();
        setupUIElements();
        loadRows();
    }

    private void prepareBackgroundManager() {
        final BackgroundManager backgroundManager = BackgroundManager.getInstance(getActivity());
        backgroundManager.attach(getActivity().getWindow());

        final DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    private void setupUIElements() {
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(android.R.color.holo_blue_light));
        setSearchAffordanceColor(getResources().getColor(android.R.color.white));
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        addOneRow();
        setAdapter(mRowsAdapter);

//        simulate adding new user by app user
        mPresenter.onNewUserAdded("MaciejSzaflik");
    }

    private synchronized void addOneRow() {
        final GridItemPresenter mGridItemPresenter = new GridItemPresenter();
        UsersArrayObjectAdapter mUserCategoryAdapter = new UsersArrayObjectAdapter(mGridItemPresenter);

        final HeaderItem headerItem =
                new HeaderItem(CATEGORY_COUNTER++, getResources().getString(R.string.header_users));
        final ListRow usersCategory = new ListRow(headerItem, mUserCategoryAdapter);
        mRowsAdapter.add(usersCategory);
    }


    private synchronized void addUserToRow(final Owner user) {
        final int rowsAmout = mRowsAdapter.size();
        for(int i = 0; i < rowsAmout; i++) {
            ListRow currentRow = (ListRow) mRowsAdapter.get(i);
            ArrayObjectAdapter adapter = (ArrayObjectAdapter) currentRow.getAdapter();
            if ( adapter.size() < 8) {
                adapter.add(user);
            } else if (i == rowsAmout - 1) {
                addOneRow();
                ListRow newRow = (ListRow) mRowsAdapter.get(rowsAmout);
                ArrayObjectAdapter newAdapter = (ArrayObjectAdapter) newRow.getAdapter();
                newAdapter.add(user);
            }
        }
    }

    @Override
    public void addNewUserToGrid(final Owner user) {
        for(int i =0; i<8; i++) addUserToRow(user);
    }

    @Override
    public void startUserDetailedActivity(final Owner user) {/*TODO*/}
}