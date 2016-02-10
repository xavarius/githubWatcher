package com.maciejmalak.githubstatistics.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

// TODO What kind of design pattern can implement instead/along this? DAO?
public class GithubUserModel {
    private static GithubUserModel mModelInstance = null;
    private static ArrayList<GithubUser> mUsersData;

    private GithubUserModel() {
        mUsersData = new ArrayList<>();
    }

    public static synchronized GithubUserModel getInstance() {
        if (mModelInstance == null) {
            mModelInstance = new GithubUserModel();
        }
        return mModelInstance;
    }

    public synchronized void putUser(final GithubUser user) {
        if (!mUsersData.contains(user)) {
            mUsersData.add(user);
        }
    }

    public synchronized void putUserForce(final GithubUser user) {
        mUsersData.add(user);
    }

    @NonNull
    public synchronized ArrayList<GithubUser> getAllUsers() {
        return mUsersData;
    }

    @Nullable
    public synchronized GithubUser getUser(final String user) {
        for (GithubUser u : mUsersData) {
            if (u.getLogin().equals(user)) {
                return u;
            }
        }
        return null;
    }
}