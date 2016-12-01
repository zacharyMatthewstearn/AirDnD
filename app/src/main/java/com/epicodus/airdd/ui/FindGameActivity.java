package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ToggleButton;

import com.epicodus.airdd.adapters.GameListAdapter;
import com.epicodus.airdd.models.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindGameActivity extends AppCompatActivity {
    @Bind(R.id.toggleButton_DM) ToggleButton mDMToggleButton;
    @Bind(R.id.toggleButton_Play) ToggleButton mPlayToggleButton;
//    @Bind(R.id.listView_Games) ListView mGamesListView;
    @Bind(R.id.rvGames) RecyclerView mGamesRecyclerView;

//    List<String> games = new ArrayList<>(Arrays.asList("game1","game2","game3","game4","game5","game6","game7","game8","game9","game10","game11","game12","game13","game14","game15","game16","game17","game18", "game19","game20"));

    List<Game> games = new ArrayList<>(Arrays.asList(new Game("game1")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);

        GameListAdapter adapter = new GameListAdapter(this, games);
        mGamesRecyclerView.setAdapter(adapter);

//        mGamesRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String gameName = parent.getItemAtPosition(position).toString();
//                Intent intent = new Intent(FindGameActivity.this, GameDetailsActivity.class);
//                intent.putExtra("gameName", gameName);
//                startActivity(intent);
//
////                Toast.makeText(FindGameActivity.this, "Clicked: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
