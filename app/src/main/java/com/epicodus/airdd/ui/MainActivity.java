package com.epicodus.airdd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.epicodus.airdd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.textView_title) TextView mTitleTextView;
    @Bind(R.id.button_findGame) Button mFindGameButton;
    @Bind(R.id.button_hostGame) Button mHostGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface modestoBoldFont = Typeface.createFromAsset(getAssets(), "fonts/nodesto-caps-condensed-bold.otf");
        mTitleTextView.setTypeface(modestoBoldFont);

        mFindGameButton.setOnClickListener(this);
        mHostGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.button_findGame:
                intent = new Intent(MainActivity.this, FindGameActivity.class);
                startActivity(intent);
                break;
            case R.id.button_hostGame:
                intent = new Intent(MainActivity.this, HostGameActivity.class);
                startActivity(intent);
                break;
            default:
                Log.d(TAG, "MainActivity onClick received bad argument for 'view'");
        }
    }
}
