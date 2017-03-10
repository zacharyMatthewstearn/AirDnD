package com.epicodus.airdd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.airdd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.textView_title) TextView mTitleTextView;
    @Bind(R.id.button_findGame) Button mFindGameButton;
    @Bind(R.id.button_hostGame) Button mHostGameButton;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("AirD&D");

        Typeface modestoBoldFont = Typeface.createFromAsset(getAssets(), "fonts/nodesto-caps-condensed-bold.otf");
        mTitleTextView.setTypeface(modestoBoldFont);

        mFindGameButton.setOnClickListener(this);
        mHostGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        switch(_view.getId()) {
            case R.id.button_findGame:
                mIntent = new Intent(mContext, FindGameActivity.class);
                startActivity(mIntent);
                break;
            case R.id.button_hostGame:
                mIntent = new Intent(mContext, HostGameActivity.class);
                startActivity(mIntent);
                break;
            default:
                Log.d(TAG, "MainActivity onClick received bad argument for 'view' : " + _view.toString());
        }
    }
}
