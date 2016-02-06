package com.maciejmalak.githubstatistics.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// TODO provide USER type with all information about repositories : list of them + user key.

public class GithubStatisticModel {
    private static GithubStatisticModel mInstance = null;
    private static LinkedHashMap<String, ArrayList<Repository>> mAllRepositories; // :(

    private GithubStatisticModel() {
        mAllRepositories = new LinkedHashMap<>();
    }

    public static synchronized GithubStatisticModel getInstance() {
        if (mInstance == null) {
            mInstance = new GithubStatisticModel();
        }
        return mInstance;
    }

    public synchronized void putRepositories(final String user,
                                             final ArrayList<Repository> repositories) {
        if (!mAllRepositories.containsValue(repositories)) {
            mAllRepositories.put(user, repositories);
        }
    }

    @NonNull
    public synchronized LinkedHashMap<String, ArrayList<Repository>> getRepositoryList() {
        return mAllRepositories;
    }

    @NonNull
    public synchronized List<Repository> getRepositoryListForUser(final String user) {
        for (Map.Entry<String, ArrayList<Repository>> entry : mAllRepositories.entrySet()) {
            if (entry.getKey().equals(user)) { // !!!!!! :(
                return entry.getValue();
            }
        }
        return Collections.<Repository>emptyList();
    }
}