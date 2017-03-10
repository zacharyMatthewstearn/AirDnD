package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.epicodus.airdd.adapters.GamePagerAdapter;
import com.epicodus.airdd.models.Game;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toggleButton_DM) ToggleButton mToggleButtonDM;
    @Bind(R.id.toggleButton_Play) ToggleButton mToggleButtonPlay;
    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.joinButton) Button mJoinButton;

    private GamePagerAdapter adapterViewPager;
    private List<Game> mGames;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);

        mGames = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_GAMES));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new GamePagerAdapter(getSupportFragmentManager(), mGames);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

        mJoinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {

        switch(_view.getId()) {
            case R.id.joinButton:

                Toast.makeText(_view.getContext(), "You have joined this game in your chosen role!", Toast.LENGTH_SHORT).show();





//                mIntent = new Intent(mContext, FindGameActivity.class);
//                startActivity(mIntent);
                break;
            default:
                Log.d(TAG, "GameDetailsActivity onClick received bad argument for 'view' : " + _view.toString());
        }
    }
}