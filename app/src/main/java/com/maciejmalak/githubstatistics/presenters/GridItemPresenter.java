package com.maciejmalak.githubstatistics.presenters;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.GithubUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridItemPresenter extends Presenter {
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_avatar)
    ImageView mUserAvatar;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.grid_item_tv, null);
        view.setFocusable(true);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        final GithubUser user = (GithubUser) item;
        mUserName.setText(user.mUserName);

        final Context context = viewHolder.view.getContext();
        Log.d("maciek", "onBindViewHolder: " + user.mUserAvatarUri);
        Glide.with(context).load(user.mUserAvatarUri).fitCenter().into(mUserAvatar);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {/*TODO cleaning*/}
}
