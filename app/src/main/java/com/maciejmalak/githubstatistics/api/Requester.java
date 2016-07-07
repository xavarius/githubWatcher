package com.maciejmalak.githubstatistics.api;

import android.content.Context;
import android.content.Intent;

import com.maciejmalak.githubstatistics.activities.UserDetailedView;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.DataStorage;
import com.maciejmalak.githubstatistics.model.GithubUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.maciejmalak.githubstatistics.api.Github.BASE_URL;

public class Requester {
    private final Logger logger = new Logger(this.getClass().getSimpleName());

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Github githubAPI = retrofit.create(Github.class);

    private Context mContext;

    public Requester(final Context context) {
        mContext = context;
    }

    public void requestGithubUserInformationByUsername(final String userName) {
        Call<GithubUser> userCall = githubAPI.getUser(userName);
        userCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                logger.logd("RESPONSE, code:  " + Integer.toString(response.code()));

                final GithubUser userPojo = response.body();
                DataStorage.INSTANCE.storeUser(userPojo);
                logger.logd(userPojo.toString());
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                logger.logd("RESPONSE FAILURE");
            }
        });
    }

    /*TODO move to presenter */
    protected void startUserDetailedActivity(final String userName) {
        final Intent startActivity = new Intent(mContext, UserDetailedView.class);
        startActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra(UserDetailedView.class.getName(), userName);
        mContext.startActivity(startActivity);
    }
}
