package com.epicodus.airdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.epicodus.airdd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HostGameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = HostGameActivity.class.getSimpleName();

    @Bind(R.id.toggleButton_DM) ToggleButton mToggleButtonDM;
    @Bind(R.id.toggleButton_Play) ToggleButton mToggleButtonPlay;
    @Bind(R.id.editText_title) EditText mEditTextTitle;
    @Bind(R.id.editText_description) EditText mEditTextDescription;
    @Bind(R.id.editText_date) EditText mEditTextDate;
    @Bind(R.id.button_postGame) Button mButtonPostGame;
    @Bind(R.id.button_cancel) Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        ButterKnife.bind(this);

        mToggleButtonDM.setOnClickListener(this);
        mToggleButtonPlay.setOnClickListener(this);
        mButtonPostGame.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HostGameActivity.this, MainActivity.class);
        switch(view.getId()) {
            case R.id.toggleButton_DM:
                mToggleButtonPlay.setChecked(!mToggleButtonPlay.isChecked());
                break;
            case R.id.toggleButton_Play:
                mToggleButtonDM.setChecked(!mToggleButtonDM.isChecked());
                break;
            case R.id.button_postGame:
                Toast.makeText(this, "Your game has been posted!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.button_cancel:
//                finish();
                startActivity(intent);
                break;
            default:
                Log.d(TAG, "HostGameActivity onClick received bad argument for 'view'");
        }
    }
}
