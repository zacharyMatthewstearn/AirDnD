package com.epicodus.airdd.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailActivity;
import com.epicodus.airdd.ui.GameDetailFragment;
import com.epicodus.airdd.util.OnGameSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private Context mContext;
    private OnGameSelectedListener mGameSelectedListener;
    private int mOrientation;
    private List<Game> mGames;

    public FirebaseGameViewHolder(View itemView, List<Game> games, OnGameSelectedListener gameSelectedListener) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        mOrientation = itemView.getResources().getConfiguration().orientation;
        if(mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }
        mGames = games;
        mGameSelectedListener = gameSelectedListener;

        itemView.setOnClickListener(this);
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
                if(mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                }
                else {
                    Intent intent = new Intent(mContext, GameDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_GAMES, Parcels.wrap(mGames));
                    mContext.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void bindGame(Game game) {
        TextView mTextViewTitle = (TextView) mView.findViewById(R.id.TextViewGameTitle);
        TextView mTextViewDateTime = (TextView) mView.findViewById(R.id.TextViewGameDateTime);
        TextView mTextViewLocation = (TextView) mView.findViewById(R.id.TextViewGameLocation);

        mTextViewTitle.setText(game.getTitle());
        mTextViewDateTime.setText(game.getDateTime());
        mTextViewLocation.setText(game.getLocation());
    }

    private void createDetailFragment(int position) {
        GameDetailFragment detailFragment = GameDetailFragment.newInstance(mGames, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.gameDetailContainer, detailFragment);
        ft.commit();
    }
}
