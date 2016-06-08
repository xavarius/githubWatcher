package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.model.Owner;

public interface GithubInteractor {
    public abstract void requestGithubUser(final String name);

    public interface OnFinishedListener {
        public abstract void onFinished(final Owner user);
    }
}
