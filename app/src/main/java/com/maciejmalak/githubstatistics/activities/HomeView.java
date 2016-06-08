package com.maciejmalak.githubstatistics.activities;

import com.maciejmalak.githubstatistics.model.GithubUser;

public interface HomeView {
    void addNewUserToGrid(final GithubUser user);
    void startUserDetailedActivity();
}
