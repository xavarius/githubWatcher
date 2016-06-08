package com.maciejmalak.githubstatistics.model;

public class GithubUser {
    public String mUserAvatarUri;
    public String mUserName;

    public GithubUser(final String name, final String avatar) {
        mUserAvatarUri = avatar;
        mUserName = name;
    }
}
