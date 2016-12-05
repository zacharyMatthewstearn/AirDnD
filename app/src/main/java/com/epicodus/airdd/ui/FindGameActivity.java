package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ToggleButton;

import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.GameListAdapter;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.models.User;
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

    private GameListAdapter mAdapter;

    public ArrayList<Game> mGames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);

        GameListAdapter adapter = new GameListAdapter(this, mGames);
        mRecyclerView.setAdapter(adapter);

        getGames();
    }

    private void getGames() {
        User tempUser1 = new User("test_username_1", "test_password_1");
        User tempUser2 = new User("test_username_2", "test_password_2");
        User tempUser3 = new User("test_username_3", "test_password_3");
        Game tempGame1 = new Game(tempUser1, true, "test_title_1", "test_description_1", "test_location_1", "9/19/2017");
        Game tempGame2 = new Game(tempUser2, true, "test_title_2", "test_description_2", "test_location_2", "9/20/2017");
        Game tempGame3 = new Game(tempUser3, true, "test_title_3", "test_description_3", "test_location_3", "9/21/2017");
        mGames.add(tempGame1);
        mGames.add(tempGame2);
        mGames.add(tempGame3);





        final MeetupService meetupService = new MeetupService();
        meetupService.findGames("97215", new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





        Game newGame = Parcels.unwrap(getIntent().getParcelableExtra("newGame"));
        if(newGame != null) {
            mGames.add(newGame);
        }

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
