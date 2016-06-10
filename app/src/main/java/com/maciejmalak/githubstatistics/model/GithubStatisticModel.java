package com.maciejmalak.githubstatistics.model;

import java.util.ArrayList;

public class GithubStatisticModel {
    private static GithubStatisticModel mInstance = null;
    private static final ArrayList<Owner> mUserList = new ArrayList<>();

    private GithubStatisticModel() {};

    public static synchronized GithubStatisticModel getInstance() {
        if (mInstance == null) {
            mInstance = new GithubStatisticModel();
        }
        return mInstance;
    }

    public synchronized void storeUser(final Owner user) {
        if ( !mUserList.contains(user)) {
            mUserList.add(user);
        }
    }
}