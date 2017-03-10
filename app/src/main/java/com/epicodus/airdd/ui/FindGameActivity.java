package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ToggleButton;

import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.FirebaseGameListAdapter;
import com.epicodus.airdd.adapters.FirebaseGameViewHolder;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.util.OnStartDragListener;
import com.epicodus.airdd.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindGameActivity extends BaseActivity implements OnStartDragListener {

    @Bind(R.id.toggleButton_DM) ToggleButton mDMToggleButton;
    @Bind(R.id.toggleButton_Play) ToggleButton mPlayToggleButton;
    @Bind(R.id.firebaseRecyclerView_Games) RecyclerView mGamesFirebaseRecyclerView;

    private FirebaseGameListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelper.Callback mItemTouchHelperCallback;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Games to Join");

        SetUpFirebaseAdapter();
    }

    private void SetUpFirebaseAdapter() {

        mFirebaseAdapter = new FirebaseGameListAdapter(Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class, mDBRefGames, this, mContext);

        mGamesFirebaseRecyclerView.setHasFixedSize(true);
        mGamesFirebaseRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mGamesFirebaseRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int _positionStart, int _itemCount) {
                super.onItemRangeInserted(_positionStart, _itemCount);
                mFirebaseAdapter.notifyDataSetChanged();

            }
        });

        mItemTouchHelperCallback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(mGamesFirebaseRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFirebaseAdapter != null) {
            mFirebaseAdapter.cleanup();
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder _viewHolder) {
        mItemTouchHelper.startDrag(_viewHolder);
    }



}
