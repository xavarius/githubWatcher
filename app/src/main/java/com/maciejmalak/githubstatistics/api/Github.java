package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.model.Owner;
import com.maciejmalak.githubstatistics.model.Repository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github {
    @GET("users/{user}")
    Call<Owner> listRepos(@Path("user") String user);
}
