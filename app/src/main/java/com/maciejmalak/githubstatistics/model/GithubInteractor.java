package com.maciejmalak.githubstatistics.model;

import com.maciejmalak.githubstatistics.model.api.GithubREST;
import com.maciejmalak.githubstatistics.helpers.Logger;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.maciejmalak.githubstatistics.model.api.GithubREST.BASE_URL;

public class GithubInteractor implements GithubUseCases {
    private final Logger logger = new Logger(this.getClass().getSimpleName());
    private final static int RESPONSE_OK = 200;

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final GithubREST githubAPI = retrofit.create(GithubREST.class);

    private GithubInteractorListener mListener;

    public GithubInteractor(final GithubInteractorListener listener) {
        mListener = listener;
    }

    @Override
    public void fetchGithubAccount(final String name) {
        final Call<Account> userCall = githubAPI.getUser(name);

        userCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                logger.logd("RESPONSE, code:  " + Integer.toString(response.code()));

                final Account account = response.body();

                if (account != null & response.code() == RESPONSE_OK) {
                    DataStorage.INSTANCE.storeUser(account);
                    mListener.onAccountInfoFetched(account);
                } else {
                    mListener.onAccoutFetchingError();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                logger.logd("RESPONSE FAILURE");
                mListener.onAccoutFetchingError();
            }
        });
    }

    public interface GithubInteractorListener {
        void onAccountInfoFetched(@NotNull final Account user);
        void onAccoutFetchingError();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mListener = null;
    }
}