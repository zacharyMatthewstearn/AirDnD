package com.epicodus.airdd.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

    public Context mContext = null;
    public String TAG = null;

    public FirebaseAuth mFirebaseAuth = null;
    public FirebaseAuth.AuthStateListener mAuthStateListener= null;
    public FirebaseUser mFirebaseUser = null;

    public ProgressDialog mProgressDialog = null;

    public Intent mIntent = null;

    public DatabaseReference mDBRefBase = null;
    public DatabaseReference mDBRefGames = null;
    public DatabaseReference mDBRefUsers = null;

    public static String mUid = null;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mContext = this;
        TAG = ">>>>>>>>>>>>" + this.getClass().getSimpleName();

        mFirebaseAuth = FirebaseAuth.getInstance();

        if(mAuthStateListener == null)
            CreateAuthStateListener();

        mDBRefBase = FirebaseDatabase.getInstance().getReference();
        mDBRefGames = mDBRefBase.child("games");
        mDBRefUsers = mDBRefBase.child("users");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu _menu) {
        if(!mContext.getClass().equals(LoginActivity.class) && !mContext.getClass().equals(CreateAccountActivity.class))
            getMenuInflater().inflate(R.menu.menu_main, _menu);
        return super.onCreateOptionsMenu(_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem _item) {
        int id = _item.getItemId();
        if (id == R.id.action_logout) {
            LogOut();
            return true;
        }
        return super.onOptionsItemSelected(_item);
    }

    public void CreateProgressDialog(String _message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage(_message);
        mProgressDialog.setCancelable(false);
    }

    private void CreateAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = firebaseAuth.getCurrentUser();

                mSharedPreferencesEditor = mSharedPreferences.edit();

                if (mFirebaseUser != null) {
                    mUid = mFirebaseUser.getUid();
                    mSharedPreferencesEditor.putString(Constants.PREFERENCES_UID_KEY, mUid).apply();
                    Log.d(TAG, mUid);

                    if(mContext.getClass().equals(MainActivity.class) )
                        getSupportActionBar().setTitle("Welcome, " + mFirebaseUser.getDisplayName() + "!");

                    if(mContext.getClass().equals(LoginActivity.class) || mContext.getClass().equals(CreateAccountActivity.class)) {
                        mIntent = new Intent(mContext, MainActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mIntent);
                        finish();
                    }
                }
                else if(!mContext.getClass().equals(LoginActivity.class) && !mContext.getClass().equals(CreateAccountActivity.class)) {
                    LogOut();
                }
            }
        };
    }

    public void LogOut() {
        mUid = null;
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_UID_KEY, mUid).apply();

        if(mFirebaseAuth.getCurrentUser() != null)
            mFirebaseAuth.signOut();

        mIntent = new Intent(mContext, LoginActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
        finish();
    }
}
