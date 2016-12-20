package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.FirebaseGameListAdapter;
import com.epicodus.airdd.adapters.FirebaseGameViewHolder;
import com.epicodus.airdd.models.Game;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListFragment extends Fragment {
//    public static final String TAG = GameListFragment.class.getSimpleName();

    @Bind(R.id.recyclerViewFirebase) RecyclerView mRecyclerViewFirebase;

//    private DatabaseReference mGameReference;

    private FirebaseGameListAdapter mFirebaseAdapter;
//    public List<Game> mGames = new ArrayList<>();

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mSearchTerm;
//    private OnGameSelectedListener mOnGameSelectedListener;

    public GameListFragment() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mEditor = mSharedPreferences.edit();
//
//        setHasOptionsMenu(true);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mOnGameSelectedListener = (OnGameSelectedListener) context;
//        }
//        catch(ClassCastException e) {
//            throw new ClassCastException(context.toString() + e.getMessage());
//        }
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
//                Log.v("GameListFragment", "search submitted: " + query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
//
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
//                        mFirebaseAdapter = new FirebaseGameListAdapter(getActivity(), mGames, mOnGameSelectedListener);
//                        mRecyclerView.setAdapter(mFirebaseAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }



    private void setUpFirebaseAdapter() {

        Query query = FirebaseDatabase.getInstance().getReference("games");

        mFirebaseAdapter = new FirebaseGameListAdapter(Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class, query, getActivity());


//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder>
//                (Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class, mGameReference) {
//
//            @Override
//            protected void populateViewHolder(FirebaseGameViewHolder viewHolder,
//                                              Game model, int position) {
//                viewHolder.bindGame(model);
//            }
//        };
//        mRecyclerViewFirebase.setAdapter(mFirebaseAdapter);

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


    }

//    private void addToSharedPreferences(String searchTerm) {
//        mEditor.putString("search_term", searchTerm).apply();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFirebaseAdapter != null) {
            mFirebaseAdapter.cleanup();
        }
    }

}