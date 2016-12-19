package com.epicodus.airdd.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailActivity;
import com.epicodus.airdd.util.OnGameSelectedListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder> {
    private DatabaseReference mRef;
    private OnGameSelectedListener mOnGameSelectedListener;
    private ChildEventListener mChildEventListener;
    private Context mContext;
    private List<Game> mGames = new ArrayList<>();
    private int mOrientation;

    public FirebaseGameListAdapter(Class<Game> modelClass, int modelLayout, Class<FirebaseGameViewHolder> viewHolderClass, DatabaseReference ref, OnGameSelectedListener onGameSelectedListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnGameSelectedListener = onGameSelectedListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mGames.add(dataSnapshot.getValue(Game.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseGameViewHolder viewHolder, Game model, int position) {
        viewHolder.bindGame(model);

        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                }
                else {
                    Intent intent = new Intent(mContext, GameDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_GAMES, Parcels.wrap(mGames));
                    mContext.startActivity(intent);
                }
            }
        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                String thisGameName = ((TextView)((ViewGroup)((ViewGroup)v).getChildAt(0)).getChildAt(0)).getText().toString(); //TODO: Refactor
//                Intent intent = new Intent(context, GameDetailActivity.class);
//                intent.putExtra("thisGame", Parcels.wrap(Game.findByTitle(mGames, thisGameName)));
//                context.startActivity(intent);
//            }
//        });
    }

    private void createDetailFragment(int position) {
//        GameDetailFragment detailFragment = GameDetailFragment.newInstance(mGames, position);
//        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.gameDetailContainer, detailFragment);
//        ft.commit();
    }

}
