package com.maciejmalak.githubstatistics.adapters;

import android.support.v17.leanback.widget.ArrayObjectAdapter;

import com.maciejmalak.githubstatistics.presenters.GridItemPresenter;

public class UsersArrayObjectAdapter extends ArrayObjectAdapter {
    /*TODO How extract code from MainActivity to process all data/events here*/
    public UsersArrayObjectAdapter(final GridItemPresenter presenter) {
        super(presenter);
    }
}
