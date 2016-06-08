package com.maciejmalak.githubstatistics.presenters;

import com.maciejmalak.githubstatistics.activities.HomeView;
import com.maciejmalak.githubstatistics.api.GithubInteractor;
import com.maciejmalak.githubstatistics.api.GithubInteractorImpl;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.model.Owner;

import java.net.URI;

import retrofit2.http.Url;

public class PresenterImpl implements Presenter, GithubInteractor.OnFinishedListener {
    private HomeView mCurrentView;
    private GithubInteractorImpl mGithubInteractorImpl; /* TODO interface type instead of concrete class*/

    public PresenterImpl(final HomeView view) {
        mCurrentView = view;
        mGithubInteractorImpl = new GithubInteractorImpl(this);
    }

    @Override
    public void onNewUserAdded(final String userName) {
        mGithubInteractorImpl.requestGithubUser(userName);
    }

    @Override
    public void onFinished(Owner user) {
        final String userName = user.getName();
        final String userAvatarUrl = user.getAvatarUrl();
        final GithubUser githubUser = new GithubUser(userName, userAvatarUrl);
        mCurrentView.addNewUserToGrid(githubUser);
    }
}
