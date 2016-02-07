package com.maciejmalak.githubstatistics.model;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

// TODO store in a DB, sync with DB
// TODO APPLY DAO pattern to Model/User

public class GithubUserModel {
    private static GithubUserModel mModelInstance = null;
    private static LinkedHashMap<String, GithubUser> mUsersData;

    private GithubUserModel() {
        mUsersData = new LinkedHashMap<>();
    }

    public static synchronized GithubUserModel getInstance() {
        if (mModelInstance == null) {
            mModelInstance = new GithubUserModel();
        }
        return mModelInstance;
    }

    public synchronized void putUser(final GithubUser user) {
        if (!mUsersData.containsValue(user)) {
            mUsersData.put(user.getUserLogin(), user);
        }
    }

    public synchronized void putUserForce(final GithubUser user) {
        mUsersData.put(user.getUserLogin(), user);
    }

    @NonNull
    public synchronized LinkedHashMap<String, GithubUser> getAllUsers() {
        return mUsersData;
    }

    @NonNull
    public synchronized GithubUser getUser(final String user) {
        for (Map.Entry<String, GithubUser> entry : mUsersData.entrySet()) {
            if (entry.getKey().equals(user)) {
                return entry.getValue();
            }
        }
        return new GithubUser(Collections.<Repository>emptyList(),"");
    }
}