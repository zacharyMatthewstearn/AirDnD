package com.epicodus.airdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HostGameActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toggleButton_DM) ToggleButton mToggleButtonDM;
    @Bind(R.id.toggleButton_Play) ToggleButton mToggleButtonPlay;
    @Bind(R.id.editText_title) EditText mEditTextTitle;
    @Bind(R.id.editText_date) EditText mEditTextDate;
    @Bind(R.id.editText_address) EditText mEditTextAddress;
    @Bind(R.id.editText_description) EditText mEditTextDescription;
    @Bind(R.id.button_postGame) Button mButtonPostGame;
    @Bind(R.id.button_cancel) Button mButtonCancel;

    private String mTitle = null;
    private String mDate = null;
    private String mAddress = null;
    private String mDescription = null;
    private boolean mIWantToDM = false;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_host_game);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Host a Game");

        mToggleButtonDM.setOnClickListener(this);
        mToggleButtonPlay.setOnClickListener(this);
        mButtonPostGame.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        switch(_view.getId()) {
            case R.id.toggleButton_DM:
                mToggleButtonPlay.setChecked(!mToggleButtonPlay.isChecked());
                break;
            case R.id.toggleButton_Play:
                mToggleButtonDM.setChecked(!mToggleButtonDM.isChecked());
                break;
            case R.id.button_postGame:
                mTitle = mEditTextTitle.getText().toString().trim();
                mDate = mEditTextDate.getText().toString().trim();
                mAddress = mEditTextAddress.getText().toString().trim();
                mDescription = mEditTextDescription.getText().toString().trim();
                mIWantToDM = mToggleButtonDM.isChecked();

                if(!IsValidTitle(mTitle) || !IsValidDate(mDate) || !IsValidAddress(mAddress) || !IsValidDescription(mDescription))
                    return;

                Game newGame = new Game(mTitle, mDate, mAddress, mDescription, mUid, mIWantToDM);
                mDBRefGames.push().setValue(newGame);

                mIntent = new Intent(mContext, FindGameActivity.class);
                mIntent.putExtra("mNewGame", Parcels.wrap(newGame));
                startActivity(mIntent);

                Toast.makeText(this, "Your game has been posted!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_cancel:
                mIntent = new Intent(mContext, MainActivity.class);
                startActivity(mIntent);
                break;
            default:
                Log.d(TAG, "HostGameActivity onClick received bad argument for 'view' : " + _view.toString());
        }
    }

    private boolean IsValidTitle(String _title) {
        if (_title.equals("")) {
            mEditTextTitle.setError("Please enter a title");
            return false;
        }
        return true;
    }

    private boolean IsValidDate(String _date) {
        if (_date.equals("")) {
            mEditTextDate.setError("Please enter a date");
            return false;
        }
        return true;
    }

    private boolean IsValidAddress(String _address) {
        if (_address.equals("")) {
            mEditTextDate.setError("Please enter an address");
            return false;
        }
        return true;
    }

    private boolean IsValidDescription(String _description) {
        if (_description.equals("")) {
            mEditTextDate.setError("Please enter a description");
            return false;
        }
        return true;
    }
}
