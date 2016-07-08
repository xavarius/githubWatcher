package com.maciejmalak.githubstatistics.model.api;

import com.maciejmalak.githubstatistics.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubREST {
    String BASE_URL = "https://api.github.com/";

    @GET("users/{user}")
    Call<Account> getUser(@Path("user") String user);
}
