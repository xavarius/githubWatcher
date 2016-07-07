package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github {
    String BASE_URL = "https://api.github.com/";

    @GET("users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
}
