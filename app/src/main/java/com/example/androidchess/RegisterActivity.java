package com.example.androidchess;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    /**
     * Keep track of the register task to ensure we can cancel it if requested.
     */
    private UserRegisterTask mAuthTask = null;


    // UI references.
    private Button mReturnButton;
    private Button mRegisterButton;
    private EditText mEmailText;
    private EditText mPasswordText;
    private EditText mUsernameText;
    private static int account_id = 0;

    //Objects
    private static Random random = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       /* if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       */
        mReturnButton = findViewById(R.id.return_button_from_register);
        mRegisterButton = findViewById(R.id.create_user_button);
        mEmailText = findViewById(R.id.email);
        mUsernameText = findViewById(R.id.username);
        mPasswordText = findViewById(R.id.password);

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Returning to log in!", Toast.LENGTH_SHORT).show();

                Intent returnActivity = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(returnActivity);
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();
            }
        });


    }

    private boolean isUsernameValid(String username) {
        return username.length() > 3 && username.length() < 15;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3 && password.length() < 15;
    }

    private boolean isEmailValid(String email) {
        return email.length() > 4 && email.contains("@");
    }

    public void attemptSignUp() {
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(mPasswordText.getText().toString()) && !isPasswordValid(mPasswordText.getText().toString())) {
            mPasswordText.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordText;
            cancel = true;
        }

        // Check for a valid email.
        if (TextUtils.isEmpty(mEmailText.getText())) {
            mEmailText.setError(getString(R.string.error_field_required));
            focusView = mEmailText;
            cancel = true;
        } else if (!isEmailValid(mEmailText.getText().toString())) {
            mEmailText.setError(getString(R.string.error_invalid_email));
            focusView = mEmailText;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(mUsernameText.getText())) {
            mUsernameText.setError(getString(R.string.error_field_required));
            focusView = mUsernameText;
            cancel = true;
        } else if (!isUsernameValid(mUsernameText.getText().toString())) {
            mUsernameText.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            account_id = 0;
            String user = String.valueOf(mUsernameText.getText());
            String email = String.valueOf(mEmailText.getText());
            String password = String.valueOf(mPasswordText.getText());
            Toast.makeText(getBaseContext(), "User created, returning!", Toast.LENGTH_SHORT).show();
            Intent returnActivity = new Intent(RegisterActivity.this, LogInActivity.class);
            startActivity(returnActivity);
            mAuthTask = new UserRegisterTask(user, email, password, account_id);
            mAuthTask.execute((Void) null);

        }
    }


    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */

    class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        private final String mUsername;
        private final String mEmail;
        private final String mPassword;
        private final int mAccount_Id;


        UserRegisterTask(String username, String email, String password, int account_id) {
            mUsername = username;
            mEmail = email;
            mPassword = password;
            mAccount_Id = account_id;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Database db = new Database();

            return db.registerUser(mUsername, mEmail, mPassword, mAccount_Id);
        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

}
