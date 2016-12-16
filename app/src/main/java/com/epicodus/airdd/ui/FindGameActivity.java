package com.epicodus.airdd.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ToggleButton;

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

public class FindGameActivity extends AppCompatActivity {
    public static final String TAG = FindGameActivity.class.getSimpleName();

    @Bind(R.id.toggleButton_DM) ToggleButton mDMToggleButton;
    @Bind(R.id.toggleButton_Play) ToggleButton mPlayToggleButton;
    @Bind(R.id.recyclerViewFirebase) RecyclerView mRecyclerViewFirebase;
//    @Bind(R.id.recyclerViewAPI) RecyclerView mRecyclerViewAPI;

    private DatabaseReference mGameReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private GameListAdapter mAdapter;
    private List<Game> mGames = new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mSearchTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);

        mGameReference = FirebaseDatabase.getInstance().getReference("games");
        setUpFirebaseAdapter();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

//        getAPIGames();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder>
                (Game.class, R.layout.game_list_item, FirebaseGameViewHolder.class,
                        mGameReference) {

            @Override
            protected void populateViewHolder(FirebaseGameViewHolder viewHolder,
                                              Game model, int position) {
                viewHolder.bindGame(model);
            }
        };
        mRecyclerViewFirebase.setHasFixedSize(true);
        mRecyclerViewFirebase.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewFirebase.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
//                searchGames(query);
                Log.v(TAG, "search submitted: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String searchTerm) {
        mEditor.putString("search_term", searchTerm).apply();
    }





//    private void getAPIGames() {
//
//        final MeetupService meetupService = new MeetupService();
//        meetupService.findGames(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                mGames = meetupService.processResults(response);
//
//                FindGameActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        GameListAdapter adapter = new GameListAdapter(FindGameActivity.this, mGames);
//                        mRecyclerViewAPI.setAdapter(adapter);
//                    }
//                });
//            }
//        });
//
//        FindGameActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter = new GameListAdapter(getApplicationContext(), mGames);
//                mRecyclerViewAPI.setAdapter(mAdapter);
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FindGameActivity.this);
//
//                mRecyclerViewAPI.setLayoutManager(layoutManager);
//                mRecyclerViewAPI.setHasFixedSize(true);
//            }
//        });
//    }
}
