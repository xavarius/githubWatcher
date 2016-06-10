package com.maciejmalak.githubstatistics.activities;

import com.maciejmalak.githubstatistics.model.Owner;

public interface HomeView {
    void addNewUserToGrid(final Owner user);
    void startUserDetailedActivity(final Owner user);
}
