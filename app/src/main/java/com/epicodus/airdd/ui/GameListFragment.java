package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.FirebaseGameListAdapter;
import com.epicodus.airdd.adapters.FirebaseGameViewHolder;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.util.OnStartDragListener;
import com.epicodus.airdd.util.SimpleItemTouchHelperCallback;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListFragment extends Fragment implements OnStartDragListener {
    @Bind(R.id.recyclerViewFirebase) RecyclerView mRecyclerViewFirebase;

    private FirebaseGameListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public GameListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {

        Query query = FirebaseDatabase.getInstance().getReference("games");

        mFirebaseAdapter = new FirebaseGameListAdapter(Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class, query, this, getActivity());

        mRecyclerViewFirebase.setHasFixedSize(true);
        mRecyclerViewFirebase.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewFirebase.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();

              }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerViewFirebase);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFirebaseAdapter != null) {
            mFirebaseAdapter.cleanup();
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}