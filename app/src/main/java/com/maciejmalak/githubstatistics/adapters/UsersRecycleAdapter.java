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

public class UsersRecycleAdapter extends RecyclerView.Adapter<UsersRecycleAdapter.ViewHolder> {
    private ArrayList<Owner> mData = new ArrayList<>();

    private OnClickListener mListener;

    public interface OnClickListener {
        public abstract void onItemClicked(final int adapterPosition);
    }

    public void unregisterListener() {
        mListener = null;
    }

    public void registerListener(final OnClickListener listener) {
        mListener = listener;
    }

    public void add(final Object newItem) {
        mData.add((Owner) newItem);
    }

    public Object getItemAtPosition(final int pos) {
        return mData.get(pos);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        final View cardView = inflater.inflate(R.layout.grid_item_mobile, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Owner user = mData.get(position);
        final View view = holder.itemView;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(holder.getAdapterPosition());
            }
        });

        Glide.with(view.getContext())
                .load(user.getAvatarUrl())
                .into(holder.mUserAvatar);

        final TextView userName = holder.mUserName;
        userName.setText(user.getLogin() == null ? "dunno" : user.getLogin());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_avatar) ImageView mUserAvatar;
        @BindView(R.id.card_text) TextView mUserName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
