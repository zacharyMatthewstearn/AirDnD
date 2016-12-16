package com.epicodus.airdd.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

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
    @Bind(R.id.joinButton) Button mJoinButton;

    private SharedPreferences mSharedPreferences;
    private DatabaseReference mUsersReference;
    private ValueEventListener mUsersReferenceListener;
    private FirebaseAuth mAuth;
    private String mUid;
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        ButterKnife.bind(this);

        mLocationTextView.setOnClickListener(this);
        mJoinButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUid = mSharedPreferences.getString(Constants.PREFERENCES_UID_KEY, null);

        mAuth = FirebaseAuth.getInstance();
        mUsersReference = FirebaseDatabase.getInstance().getReference().child("users");

        mGame = Parcels.unwrap(getIntent().getParcelableExtra("thisGame"));
        if(mGame != null) {
            mTitleTextView.setText(mGame.getTitle());
            mTimeTextView.setText(mGame.getDateTime());
            mLocationTextView.setText(mGame.getLocation());
            mDescriptionTextView.setText(mGame.getDescription());

//            if(mGame.getHostId() != null) {
//                String hostId = mGame.getHostId();
//                try {
//                    String username = mUsersReference.child(hostId).child("username").toString();
//
//                    Log.v(TAG, username);
//
//
//                } catch(NullPointerException e) {
//                    e.printStackTrace();
//                    Log.v(TAG, "Aaaaaaaaaaaaaah!");
//                }
////                String hostName = ""; // TODO: Get username of user with uid of hostId
////                mHostTextView.setText(hostName);
//            }
//            else {
//                mHostTextView.setText("ERROR: no host for this game");
//            }
//            if(mGame.getDmId() != null) {
//                String dmId = mGame.getDmId();
////                String dmName = ""; // TODO: Get username of user with uid of dmId
////                mDmTextView.setText(dmName);
//            }
//            else {
//                mDmTextView.setText("Want to DM? This game still needs someone!");
//            }
//            if(mGame.getPlayerIds() != null && mGame.getPlayerIds().size() > 0) {
//                List<String> playerIds = mGame.getPlayerIds();
//                List<String> playerNames = new ArrayList<>(); // TODO: Get usernames of users with uids of playerIds
//                mPlayersTextView.setText(TextUtils.join(", ", playerNames));
//            }
//            else {
//                mPlayersTextView.setText("Want to play? Be the first to sign up!");
//            }
        }
        else {
            Toast.makeText(this, "ERROR: failed to retrieve data", Toast.LENGTH_SHORT).show();
        }


//        Log.d(TAG, mGame.getDmId());


        mUsersReferenceListener = mUsersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uid;
                String username;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    uid = userSnapshot.child("uid").getValue().toString();
                    username = userSnapshot.child("username").getValue().toString();
//                    Log.v(TAG, mGame.getDmId()); // TODO: Find out why mGame's DmId is being assigned erroneously
                    if(uid.equals(mGame.getHostId())) {
                        mHostTextView.setText("Host: " + username);
                    }
                    Log.d(TAG, "uid: " + uid);
                    Log.d(TAG, "mGame.getDmId(): " + mGame.getDmId());
                    if(uid.equals(mGame.getDmId())) {
//                        Log.v(TAG, "DM: " + username);
//                        Log.v(TAG, "mGame.getDmId() == " + mGame.getDmId());
                        mDmTextView.setText("DM: " + username);
                    }
                    if(mGame.getPlayerIds().contains(uid)) {
                        if(!mPlayersTextView.getText().equals("Players: ")) {
                            mPlayersTextView.setText(mPlayersTextView.getText() + ", ");
                        }
                        mPlayersTextView.setText(mPlayersTextView.getText() + username);
                    }
//                    Log.d(TAG, username);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.locationTextView:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mGame.getLocation()));
                startActivity(mapIntent);
                break;
            case R.id.joinButton:
                Log.d(TAG, "Joined!");
                break;
            default:
                Log.d(TAG, "GameDetailsActivity onClick received bad argument for 'view'");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUsersReference.removeEventListener(mUsersReferenceListener);
    }
}