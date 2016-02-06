package com.maciejmalak.githubstatistics.API;

import android.content.Context;
import android.content.Intent;
import android.net.http.HttpResponseCache;

import com.maciejmalak.githubstatistics.activities.UserDetailedView;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.model.GithubUserModel;
import com.maciejmalak.githubstatistics.model.Repository;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester {
    private final Logger logger = new Logger(this.getClass().getSimpleName());

    private static final String BASE_URL = "https://api.github.com/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Github githubAPI = retrofit.create(Github.class);

    private Context mContext;

    public Requester(final Context context) {
        mContext = context;
    }

    public void requestRepositoryForUser(final String userName) {
        Call<ArrayList<Repository>> repoCall = githubAPI.listRepos(userName);
        repoCall.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                logger.logd("RESPONSE OK, code:  " + Integer.toString(response.code()));

                if(response.code() == HttpURLConnection.HTTP_OK) {
                    final ArrayList<Repository> repositories = response.body();
                    GithubUserModel.getInstance().putUserForce(new GithubUser(repositories, userName));
                    startUserDetailedActivity(userName);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                logger.logd("RESPONSE FAILURE");
            }
        });
    }

    protected void startUserDetailedActivity(final String userName) {
        final Intent startActivity = new Intent(mContext, UserDetailedView.class);
        startActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra(UserDetailedView.class.getName(), userName);
        mContext.startActivity(startActivity);
    }
}
