package com.epicodus.airdd.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ToggleButton;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.GamePagerAdapter;
import com.epicodus.airdd.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailActivity extends AppCompatActivity {
    public static final String TAG = GameDetailActivity.class.getSimpleName();

    @Bind(R.id.toggleButton_DM) ToggleButton mToggleButtonDM;
    @Bind(R.id.toggleButton_Play) ToggleButton mToggleButtonPlay;
//    @Bind(R.id.titleTextView) TextView mTitleTextView;
//    @Bind(R.id.timeTextView) TextView mTimeTextView;
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.hostTextView) TextView mHostTextView;
//    @Bind(R.id.dmTextView) TextView mDmTextView;
//    @Bind(R.id.playersTextView) TextView mPlayersTextView;
//    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.joinButton) Button mJoinButton;

    private SharedPreferences mSharedPreferences;
    private DatabaseReference mUsersReference;
    private DatabaseReference mGamesReference;
    private ValueEventListener mUsersReferenceListener;
    private ValueEventListener mGamesReferenceListener;
    private FirebaseAuth mAuth;
    private String mUid;
    private GamePagerAdapter adapterViewPager;
    private List<Game> mGames;
//    private int position;
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);

//        mToggleButtonDM.setOnClickListener(this);
//        mToggleButtonPlay.setOnClickListener(this);
////        mLocationTextView.setOnClickListener(this);
//        mJoinButton.setOnClickListener(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mUid = mSharedPreferences.getString(Constants.PREFERENCES_UID_KEY, null);
//
//        mAuth = FirebaseAuth.getInstance();
//        mUsersReference = FirebaseDatabase.getInstance().getReference().child("users");
//        mGamesReference = FirebaseDatabase.getInstance().getReference().child("games");

        mGames = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_GAMES));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
//        mGame = mGames.get(position);

        adapterViewPager = new GamePagerAdapter(getSupportFragmentManager(), mGames);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

//        if(mGame != null) {
//            mTitleTextView.setText(mGame.getTitle());
//            mTimeTextView.setText(mGame.getDateTime());
//            mLocationTextView.setText(mGame.getLocation());
//            mDescriptionTextView.setText(mGame.getDescription());

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
//        }
//        else {
//            Toast.makeText(this, "ERROR: failed to retrieve data", Toast.LENGTH_SHORT).show();
//        }


//        Log.d(TAG, mGame.getDmId());


//        mUsersReferenceListener = mUsersReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String uid;
//                String username;
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    uid = userSnapshot.child("uid").getValue().toString();
//                    username = userSnapshot.child("username").getValue().toString();
////                    Log.v(TAG, mGame.getDmId()); // TODO: Find out why mGame's DmId is being assigned erroneously
//                    if(uid.equals(mGame.getHostId())) {
//                        mHostTextView.setText("Host: " + username);
//                    }
//                    Log.d(TAG, "uid: " + uid);
//                    Log.d(TAG, "mGame.getDmId(): " + mGame.getDmId());
//                    if(uid.equals(mGame.getDmId())) {
////                        Log.v(TAG, "DM: " + username);
////                        Log.v(TAG, "mGame.getDmId() == " + mGame.getDmId());
//                        mDmTextView.setText("DM: " + username);
//                    }
//                    if(mGame.getPlayerIds().contains(uid)) {
//                        if(!mPlayersTextView.getText().equals("Players: ")) {
//                            mPlayersTextView.setText(mPlayersTextView.getText() + ", ");
//                        }
//                        mPlayersTextView.setText(mPlayersTextView.getText() + username);
//                    }
////                    Log.d(TAG, username);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

//    @Override
//    public void onClick(View view) {
//        switch(view.getId()) {
//            case R.id.toggleButton_DM:
//                mToggleButtonPlay.setChecked(!mToggleButtonPlay.isChecked());
//                break;
//            case R.id.toggleButton_Play:
//                mToggleButtonDM.setChecked(!mToggleButtonDM.isChecked());
//                break;
////            case R.id.locationTextView:
////                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mGame.getLocation()));
////                startActivity(mapIntent);
////                break;
//            case R.id.joinButton:
//                if(mToggleButtonDM.isChecked()) {
//                    Log.d(TAG, "Joined as the DM!");
//                }
//                else {
//                    Log.d(TAG, "Joined as a PLAYER!");
//
//                }
//                break;
//            default:
//                Log.d(TAG, "GameDetailActivity onClick received bad argument for 'view'");
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mUsersReference.removeEventListener(mUsersReferenceListener);
//    }
}