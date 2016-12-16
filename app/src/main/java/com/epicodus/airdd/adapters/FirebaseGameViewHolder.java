package com.epicodus.airdd.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGame(Game game) {
        TextView mTextViewTitle = (TextView) mView.findViewById(R.id.TextViewGameTitle);
        TextView mTextViewDateTime = (TextView) mView.findViewById(R.id.TextViewGameDateTime);
        TextView mTextViewLocation = (TextView) mView.findViewById(R.id.TextViewGameLocation);

        mTextViewTitle.setText(game.getTitle());
        mTextViewDateTime.setText(game.getDateTime());
        mTextViewLocation.setText(game.getLocation());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Game> games = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("games");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(Game.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, GameDetailsActivity.class);
                intent.putExtra("thisGame", Parcels.wrap(games.get(itemPosition)));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
