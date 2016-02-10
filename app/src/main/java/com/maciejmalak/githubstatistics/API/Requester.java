package com.maciejmalak.githubstatistics.API;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.maciejmalak.githubstatistics.activities.UserDetailedView;
import com.maciejmalak.githubstatistics.helpers.Logger;
import com.maciejmalak.githubstatistics.model.GithubUser;
import com.maciejmalak.githubstatistics.model.GithubUserModel;

import java.net.HttpURLConnection;

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

    private static final GithubAPI githubAPI = retrofit.create(GithubAPI.class);

    private Context mContext;

    public Requester(final Context context) {
        mContext = context;
    }

    public void fetchUserDescription(final String userName) {
        Call<GithubUser> repoCall = githubAPI.getUserDescription(userName);
        repoCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                logger.logd("RESPONSE OK, code:  " + Integer.toString(response.code()));

                if(response.code() == HttpURLConnection.HTTP_OK) {
                    final GithubUser user = response.body();
                    GithubUserModel.getInstance().putUserForce(user);
                    startUserDetailedActivity(userName);
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Toast.makeText(mContext,"Request failed. Try again.", Toast.LENGTH_SHORT).show();
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
