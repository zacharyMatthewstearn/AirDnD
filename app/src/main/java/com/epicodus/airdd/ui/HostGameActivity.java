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
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.models.User;

import org.parceler.Parcels;

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
        Intent intent;
        User temp = new User("Guest", "password");
        Game newGame;
        switch(view.getId()) {
            case R.id.toggleButton_DM:
                mToggleButtonPlay.setChecked(!mToggleButtonPlay.isChecked());
                break;
            case R.id.toggleButton_Play:
                mToggleButtonDM.setChecked(!mToggleButtonDM.isChecked());
                break;
            case R.id.button_postGame:
                if(mEditTextTitle.getText().toString().length() > 0 &&
                   mEditTextDate.getText().toString().length() > 0 &&
                   mEditTextDescription.getText().toString().length() > 0) {

                    newGame = new Game(temp, mToggleButtonDM.isChecked(), mEditTextTitle.getText().toString(), mEditTextDescription.getText().toString(), temp.getAddress(), mEditTextDate.getText().toString());

                    intent = new Intent(HostGameActivity.this, FindGameActivity.class);
                    intent.putExtra("newGame", Parcels.wrap(newGame));
                    startActivity(intent);

                    Toast.makeText(this, "Your game has been posted!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(HostGameActivity.this, "All fields are required to post a game.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_cancel:
                intent = new Intent(HostGameActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                Log.d(TAG, "HostGameActivity onClick received bad argument for 'view'");
        }
    }
}