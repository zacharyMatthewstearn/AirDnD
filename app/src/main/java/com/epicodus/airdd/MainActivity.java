package com.epicodus.airdd;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textView_title) TextView mTitleTextView;
    @Bind(R.id.button_findGame) Button mFindGameButton;
    @Bind(R.id.button_hostGame) Button mHostGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface modestoBoldFont = Typeface.createFromAsset(getAssets(), "fonts/modesto-bold.ttf");
        mTitleTextView.setTypeface(modestoBoldFont);

        mFindGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindGameActivity.class);
                startActivity(intent);
            }
        });

        mHostGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.coming_soon, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
