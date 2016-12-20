package com.epicodus.airdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private Context mContext;


    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindGame(Game game) {
        TextView mTextViewTitle = (TextView) mView.findViewById(R.id.TextViewGameTitle);
        TextView mTextViewDateTime = (TextView) mView.findViewById(R.id.TextViewGameDateTime);
        TextView mTextViewLocation = (TextView) mView.findViewById(R.id.TextViewGameLocation);

        mTextViewTitle.setText(game.getTitle());
        mTextViewDateTime.setText(game.getDateTime());
        mTextViewLocation.setText(game.getLocation());
    }
}
