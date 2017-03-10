package com.epicodus.airdd.ui;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GameDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.hostTextView) TextView mHostTextView;
    @Bind(R.id.dmTextView) TextView mDmTextView;
    @Bind(R.id.playersTextView) TextView mPlayersTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;

    private DatabaseReference mDBRefUsers;
    private ValueEventListener mValueEventListener_Users;
    private List<Game> mGames;
    private int mPosition;
    private Game mGame;
    private Intent mIntentFrom;
    private Intent mIntentTo;
    private Context mContext;


    public static GameDetailFragment newInstance(List<Game> _games, Integer _position) {
        GameDetailFragment gameDetailFragment = new GameDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EXTRA_KEY_GAMES, Parcels.wrap(_games));
        bundle.putInt(Constants.EXTRA_KEY_POSITION, _position);
        gameDetailFragment.setArguments(bundle);

        return gameDetailFragment;
    }

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        mContext = getActivity();

        mIntentFrom = getActivity().getIntent();

        mGames = Parcels.unwrap(mIntentFrom.getParcelableExtra(Constants.EXTRA_KEY_GAMES));
        mPosition = mIntentFrom.getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
        mGame = mGames.get(mPosition);

//        Log.d(String.valueOf(mContext), "_position == " + mPosition);
//        if(mGames.isEmpty())
//            Log.d(String.valueOf(mContext), "mGames.isEmpty == true ");
//        else {
//            Log.d(String.valueOf(mContext), "mGames.size() == " + mGames.size());
//            if(mPosition >= 0 && mPosition < mGames.size())
//                Log.d(String.valueOf(mContext), "mGames.get(_position).getTitle() == " + mGames.get(mPosition).getTitle());
//        }

        mDBRefUsers = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);

        if(mGame != null) {
            mTitleTextView.setText(mGame.getTitle());
            mTimeTextView.setText(mGame.getDateTime());
            mLocationTextView.setText(mGame.getLocation());
            mDescriptionTextView.setText(mGame.getDescription());
        }
        else {
            Toast.makeText(getActivity(), "ERROR: failed to retrieve data", Toast.LENGTH_SHORT).show();
        }

        mValueEventListener_Users = mDBRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uid;
                String username;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    uid = userSnapshot.child("uid").getValue().toString();
                    username = userSnapshot.child("username").getValue().toString();
                    if(uid.equals(mGame.getHostId())) {
                        mHostTextView.setText("Host: " + username);
                    }
                    if(uid.equals(mGame.getDmId())) {
                        mDmTextView.setText("DM: " + username);
                    }
                    if(mGame.getPlayerIds().contains(uid)) {
                        if(!mPlayersTextView.getText().equals("Players: ")) {
                            mPlayersTextView.setText(mPlayersTextView.getText() + ", ");
                        }
                        mPlayersTextView.setText(mPlayersTextView.getText() + username);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mLocationTextView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View _view) {
        switch(_view.getId()) {
            case R.id.locationTextView:
                mIntentTo = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mGame.getLocation()));
                startActivity(mIntentTo);
                break;
            default:
                Log.d("GameDetailFragment", "onClick received bad argument for 'view' : " + _view.toString());
        }
    }

}
