package com.epicodus.airdd.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = GameDetailsActivity.class.getSimpleName();

    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.hostTextView) TextView mHostTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    private SharedPreferences mSharedPreferences;
    private String mUid;

    public Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        ButterKnife.bind(this);

        mLocationTextView.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUid = mSharedPreferences.getString(Constants.PREFERENCES_UID_KEY, null);

        mGame = Parcels.unwrap(getIntent().getParcelableExtra("thisGame"));
        if(mGame != null) {
            mTitleTextView.setText(mGame.getTitle());
            mDescriptionTextView.setText(mGame.getDescription());
            mTimeTextView.setText(mGame.getDateTime());
            mLocationTextView.setText(mGame.getLocation());
            if(mGame.getHost() != null) {
//                mHostTextView.setText(mGame.getHost().getUsername()); // TODO: ADD A THING HERE
            }
            else {
                mHostTextView.setText("ERROR: no host for this game");
            }
        }
        else {
            Toast.makeText(this, "ERROR: failed to retrieve data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.locationTextView:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:" + "45.5207050"
                                + "," + "-122.6773970"
                                + "?q=(" + "Epicodus" + ")"));
                startActivity(mapIntent);
                break;
            default:
                Log.d(TAG, "GameDetailsActivity onClick received bad argument for 'view'");
        }
    }
}