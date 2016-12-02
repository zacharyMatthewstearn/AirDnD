package com.epicodus.airdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.epicodus.airdd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailsActivity extends AppCompatActivity {
    @Bind(R.id.titleTextView) TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String output = intent.getStringExtra("gameName");

        mOutputTextView.setText(output);
    }
}
