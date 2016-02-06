package com.maciejmalak.githubstatistics.model;

import java.util.ArrayList;
import java.util.List;

public class GithubUser {

    private String mUserLogin;
    private List<Repository> mUserRepos;

    public GithubUser(final List<Repository> repos, final String userLogin) {
        mUserRepos = new ArrayList<>(repos);
        mUserLogin = userLogin;
    }

    public String getUserLogin() {
        return mUserLogin;
    }

    public List<Repository> getRepositories() {
        return  mUserRepos;
    }

    public int getReposAmout() {
        return mUserRepos.size();
    }

}
