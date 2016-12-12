package com.epicodus.airdd.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.google.firebase.auth.FirebaseAuth;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = GameDetailsActivity.class.getSimpleName();

    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.hostTextView) TextView mHostTextView;
    @Bind(R.id.dmTextView) TextView mDmTextView;
    @Bind(R.id.playersTextView) TextView mPlayersTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;

    private SharedPreferences mSharedPreferences;
    private FirebaseAuth mAuth;
    private String mUid;
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        ButterKnife.bind(this);

        mLocationTextView.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUid = mSharedPreferences.getString(Constants.PREFERENCES_UID_KEY, null);

        mAuth = FirebaseAuth.getInstance();

        mGame = Parcels.unwrap(getIntent().getParcelableExtra("thisGame"));
        if(mGame != null) {
            mTitleTextView.setText(mGame.getTitle());
            mTimeTextView.setText(mGame.getDateTime());
            mLocationTextView.setText(mGame.getLocation());

            if(mGame.getHostId() != null) {
                String hostId = mGame.getHostId();
//                String hostName = ""; // TODO: Get username of user with uid of hostId
//                mHostTextView.setText(hostName);
            }
            else {
                mHostTextView.setText("ERROR: no host for this game");
            }
            if(mGame.getDmId() != null) {
                String dmId = mGame.getDmId();
//                String dmName = ""; // TODO: Get username of user with uid of dmId
//                mDmTextView.setText(dmName);
            }
            else {
                mDmTextView.setText("Want to DM? This game still needs someone!");
            }
            if(mGame.getPlayerIds() != null && mGame.getPlayerIds().size() > 0) {
                List<String> playerIds = mGame.getPlayerIds();
                List<String> playerNames = new ArrayList<>(); // TODO: Get usernames of users with uids of playerIds
                mDmTextView.setText(TextUtils.join(", ", playerNames));
            }
            else {
                mPlayersTextView.setText("Want to play? Be the first to sign up!");
            }

            mDescriptionTextView.setText(mGame.getDescription());
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