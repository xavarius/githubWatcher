package com.maciejmalak.githubstatistics.API;

import com.maciejmalak.githubstatistics.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    @GET("users/{user}")
    Call<GithubUser> getUserDescription(@Path("user") String user);
}