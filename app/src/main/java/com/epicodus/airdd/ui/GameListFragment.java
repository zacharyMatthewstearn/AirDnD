package com.epicodus.airdd.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.FirebaseGameViewHolder;
import com.epicodus.airdd.adapters.GameListAdapter;
import com.epicodus.airdd.models.Game;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListFragment extends Fragment {
//    public static final String TAG = GameListFragment.class.getSimpleName();

    @Bind(R.id.recyclerViewFirebase) RecyclerView mRecyclerViewFirebase;

    private DatabaseReference mGameReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private GameListAdapter mAdapter;
    public List<Game> mGames = new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mSearchTerm;

    public GameListFragment() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mGameReference = FirebaseDatabase.getInstance().getReference("games");
//        setUpFirebaseAdapter();
//
////        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
////        mEditor = mSharedPreferences.edit();
//
////        setHasOptionsMenu(true);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
        ButterKnife.bind(this, view);
//        mSearchTerm = mSharedPreferences.getString("search_term", null);

//        if (mSearchTerm != null) {
//            getGames(mSearchTerm);
//        }
//        getGames();

        mGameReference = FirebaseDatabase.getInstance().getReference("games");
        setUpFirebaseAdapter();


        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_search, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
////                getGames(query);
//                Log.v(TAG, "search submitted: " + query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

//    public void getGames() {
//        final MeetupService meetupService = new MeetupService();
//
//        meetupService.findGames(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mGames = meetupService.processResults(response);
//
//                getActivity().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mAdapter = new GameListAdapter(getActivity(), mGames);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }



    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder>
                (Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class, mGameReference) {

            @Override
            protected void populateViewHolder(FirebaseGameViewHolder viewHolder,
                                              Game model, int position) {
                viewHolder.bindGame(model);
            }
        };
        mRecyclerViewFirebase.setHasFixedSize(true);
        mRecyclerViewFirebase.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewFirebase.setAdapter(mFirebaseAdapter);
    }

//    private void addToSharedPreferences(String searchTerm) {
//        mEditor.putString("search_term", searchTerm).apply();
//    }

}