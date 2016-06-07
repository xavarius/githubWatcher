package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.model.Repository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github {
    @GET("users/{user}/repos")
    Call<ArrayList<Repository>> listRepos(@Path("user") String user);
}
