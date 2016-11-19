package com.epicodus.airdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindGameActivity extends AppCompatActivity {
    @Bind(R.id.toggleButton_DM) ToggleButton mDMToggleButton;
    @Bind(R.id.toggleButton_Play) ToggleButton mPlayToggleButton;
    @Bind(R.id.listView_Games) ListView mGamesListView;

    List<String> games = new ArrayList<>(Arrays.asList("game1","game2","game3","game4","game5","game6","game7","game8","game9","game10","game11","game12","game13","game14","game15","game16","game17","game18", "game19","game20"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
        mGamesListView.setAdapter(adapter);

        mGamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String gameName = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(FindGameActivity.this, GameDetailsActivity.class);
                intent.putExtra("gameName", gameName);
                startActivity(intent);

//                Toast.makeText(FindGameActivity.this, "Clicked: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
