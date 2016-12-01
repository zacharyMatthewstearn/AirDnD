package com.epicodus.airdd.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HostGameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = HostGameActivity.class.getSimpleName();

    @Bind(R.id.toggleButton_DM) ToggleButton mToggleButtonDM;
    @Bind(R.id.toggleButton_Play) ToggleButton mToggleButtonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        ButterKnife.bind(this);

        mToggleButtonDM.setOnClickListener(this);
        mToggleButtonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.toggleButton_DM:
                mToggleButtonPlay.setChecked(!mToggleButtonPlay.isChecked());
                break;
            case R.id.toggleButton_Play:
                mToggleButtonDM.setChecked(!mToggleButtonDM.isChecked());
                break;
            default:
                Log.d(TAG, "HostGameActivity onClick received bad argument for 'view'");
        }
    }
}
