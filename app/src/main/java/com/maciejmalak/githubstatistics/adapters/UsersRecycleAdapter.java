package com.maciejmalak.githubstatistics.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maciejmalak.githubstatistics.R;
import com.maciejmalak.githubstatistics.model.Owner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersRecycleAdapter extends RecyclerView.Adapter<UsersRecycleAdapter.ViewHolder>{
    private ArrayList<Owner> mData = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        final View cardView = inflater.inflate(R.layout.grid_item_mobile, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Owner user = mData.get(position);

        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .into(holder.mUserAvatar);
        holder.mUserName.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(final Object newItem) {
        mData.add((Owner) newItem);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_avatar)
        ImageView mUserAvatar;
        @BindView(R.id.card_text)
        TextView mUserName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
