package com.maciejmalak.githubstatistics.api;

import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubStatisticModel;
import com.maciejmalak.githubstatistics.model.Owner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.maciejmalak.githubstatistics.api.Github.BASE_URL;

public class GithubInteractorImpl implements GithubInteractor {
    private final Logger logger = new Logger(this.getClass().getSimpleName());

    private static final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    private static final Github githubAPI = retrofit.create(Github.class);

    private OnFinishedListener onFinishedListener;

    public GithubInteractorImpl(final OnFinishedListener finishedListener) {
        onFinishedListener = finishedListener;
    }

    @Override
    public void requestGithubUser(final String userName) {
        final Call<Owner> repoCall = githubAPI.listRepos(userName);
        repoCall.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                logger.logd("RESPONSE OK, code:  " + Integer.toString(response.code()));

                final Owner owner = response.body();
                GithubStatisticModel.getInstance().storeUser(owner);
                onFinishedListener.onFinished(owner);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                logger.logd("RESPONSE FAILURE");
                /*TODO trigger presenter when failure*/
            }
        });
    }
}
