package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.Owner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubInteractorImpl implements GithubInteractor {
    private final Logger logger = new Logger(this.getClass().getSimpleName());

    private static final String BASE_URL = "https://api.github.com/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Github githubAPI = retrofit.create(Github.class);
    private OnFinishedListener mPresenter;

    public GithubInteractorImpl(final OnFinishedListener presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestGithubUser(final String userName) {
        Call<Owner> repoCall = githubAPI.listRepos(userName);
        repoCall.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                logger.logd("RESPONSE OK, code:  " + Integer.toString(response.code()));

                final Owner owner = response.body();
                mPresenter.onFinished(owner);
                /*TODO Storing data */
//                GithubStatisticModel.getInstance().putRepositories(userName, owner);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                logger.logd("RESPONSE FAILURE");
            }
        });
    }
}
