package com.epicodus.airdd.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailActivity;
import com.epicodus.airdd.util.ItemTouchHelperAdapter;
import com.epicodus.airdd.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder> implements ItemTouchHelperAdapter {

    private Context mContext;
    private DatabaseReference mDBRef_Games;
    private ChildEventListener mChildEventListener;
    private OnStartDragListener mOnStartDragListener;
    private List<Game> mGames = new ArrayList<>();
    private Intent mIntent;


    public FirebaseGameListAdapter(Class<Game> _modelClass, int _modelLayout, Class<FirebaseGameViewHolder> _viewHolderClass, Query _ref, OnStartDragListener _onStartDragListener, Context _context) {
        super(_modelClass, _modelLayout, _viewHolderClass, _ref);
        mDBRef_Games = _ref.getRef();
        mOnStartDragListener = _onStartDragListener;
        mContext = _context;


        mChildEventListener = mDBRef_Games.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mGames.add(dataSnapshot.getValue(Game.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                mGames.remove(dataSnapshot.getValue(Game.class));
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
    protected void populateViewHolder(final FirebaseGameViewHolder _viewHolder, Game _model, final int _position) {
        _viewHolder.bindGame(_model);

        _viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(_viewHolder.itemView.getContext(), "You have joined this game in your chosen role!", Toast.LENGTH_SHORT).show();


                //TODO: ACTUALLY SIGN UP FOR THE DAMNED GAME!!!!!!!!!!!!!!


                return false;
            }
        });

        _viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIntent = new Intent(mContext, GameDetailActivity.class);
                mIntent.putExtra(Constants.EXTRA_KEY_POSITION, _position);
                mIntent.putExtra(Constants.EXTRA_KEY_GAMES, Parcels.wrap(mGames));
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public void cleanup() {
        super.cleanup();
        mDBRef_Games.removeEventListener(mChildEventListener);
    }

    @Override
    public void onItemDismiss(int _position) {
        mGames.remove(_position);
        getRef(_position).removeValue();
    }
}
