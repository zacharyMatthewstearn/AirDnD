package com.epicodus.airdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLoginTextView;

    private String mName;
    private String mEmail;
    private String mPassword;
    private String mPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Sign Up");

        CreateProgressDialog("Authenticating with Firebase...");

        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        switch (_view.getId()) {
            case R.id.loginTextView:
                mIntent = new Intent(mContext, LoginActivity.class);
                startActivity(mIntent);
                break;
            case R.id.createUserButton:
                CreateNewUser();
                break;
            default:
                Log.d(TAG, "onClick received bad argument for '_view' : " + _view.toString());
        }
    }

    private void CreateNewUser() {
        mName = mNameEditText.getText().toString().trim();
        mEmail = mEmailEditText.getText().toString().trim();
        mPassword = mPasswordEditText.getText().toString().trim();
        mPasswordConfirmation = mConfirmPasswordEditText.getText().toString().trim();

        boolean validName = IsValidName(mName);
        boolean validEmail = IsValidEmail(mEmail);
        boolean validPassword = IsValidPassword(mPassword, mPasswordConfirmation);
        if (!validEmail || !validName || !validPassword)
            return;

        mProgressDialog.show();

        mFirebaseAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> _task) {
                mProgressDialog.dismiss();

                if (_task.isSuccessful()) {
                    Toast.makeText(mContext, "Welcome to AirD&D!", Toast.LENGTH_SHORT).show();
                    CreateFirebaseUser(_task.getResult().getUser());
                }
                else {
                    Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CreateFirebaseUser(final FirebaseUser _firebaseUser) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder().setDisplayName(mName).build();
        _firebaseUser.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, _firebaseUser.getDisplayName());

                    User newUser = new User(_firebaseUser.getDisplayName(), _firebaseUser.getEmail(), "KEPT SEPARATE FOR SECURITY PURPOSES");
                    newUser.setUid(_firebaseUser.getUid());

                    SaveUserToFirebase(newUser);
                }
            }
        });
    }

    private boolean IsValidName(String _name) {
        if (_name.equals("")) {
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean IsValidEmail(String _email) {
        if (_email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean IsValidPassword(String _password, String _passwordConfirmation) {
        if (_password.length() < 6) {
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        }
        else if (!_password.equals(_passwordConfirmation)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void SaveUserToFirebase(User _newUser) {
//        mDBRefUsers.push().setValue(_newUser);
        mDBRefUsers.child(mUid).setValue(_newUser);
    }
}
