package com.epicodus.airdd.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ToggleButton;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.GameListAdapter;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.services.MeetupService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindGameActivity extends AppCompatActivity {
    public static final String TAG = FindGameActivity.class.getSimpleName();

    @Bind(R.id.toggleButton_DM) ToggleButton mDMToggleButton;
    @Bind(R.id.toggleButton_Play) ToggleButton mPlayToggleButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private SharedPreferences mSharedPreferences;
    private GameListAdapter mAdapter;
    private String mUid;

    public ArrayList<Game> mGames = new ArrayList<>();
    Game mNewGame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUid = mSharedPreferences.getString(Constants.PREFERENCES_UID_KEY, null);

        getGames();
    }

    private void getGames() {

        final MeetupService meetupService = new MeetupService();
        meetupService.findGames(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mGames = meetupService.processResults(response);

                FindGameActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mNewGame = Parcels.unwrap(getIntent().getParcelableExtra("mNewGame"));
                        if(mNewGame != null) {
                            mGames.add(mNewGame);
                            Log.v("FindGameActivity", "Purportedly adding new game!");
                        }

                        GameListAdapter adapter = new GameListAdapter(FindGameActivity.this, mGames);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });

        FindGameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new GameListAdapter(getApplicationContext(), mGames);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FindGameActivity.this);

                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }
        });
    }
}
