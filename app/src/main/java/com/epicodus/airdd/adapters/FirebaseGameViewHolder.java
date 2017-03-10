package com.epicodus.airdd.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder {

    private View mView;

    public FirebaseGameViewHolder(View _itemView) {
        super(_itemView);
        mView = _itemView;
    }

    public void bindGame(Game _game) {
        TextView mTextViewTitle = (TextView) mView.findViewById(R.id.TextViewGameTitle);
        TextView mTextViewDateTime = (TextView) mView.findViewById(R.id.TextViewGameDateTime);
        TextView mTextViewLocation = (TextView) mView.findViewById(R.id.TextViewGameLocation);

        mTextViewTitle.setText(_game.getTitle());
        mTextViewDateTime.setText(_game.getDateTime());
        mTextViewLocation.setText(_game.getLocation());
    }
}
