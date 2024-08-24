package com.example.pethelp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";
    DatabaseHelper dbh;
    EditText txtEmail;
    EditText txtPassword;
    private String loggedInUserEmail;
    private String loggedInUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);

        dbh = new DatabaseHelper(this);

        Intent intent = getIntent();
        loggedInUserEmail = intent.getStringExtra("USER_EMAIL");
        loggedInUserPassword = intent.getStringExtra("USER_PASSWORD");

        if (loggedInUserEmail != null && loggedInUserPassword != null) {
            Log.d(TAG, "Logged in user email: " + loggedInUserEmail);
            txtEmail.setText(loggedInUserEmail);
            txtPassword.setText(loggedInUserPassword);
        } else {
            Log.e(TAG, "No email or password found in the intent.");
        }
    }

    public void update(View v) {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        SQLiteDatabase db = dbh.getWritableDatabase();

        try {
            if (dbh.updateUser(db, loggedInUserEmail, email, password)) {
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
                loggedInUserEmail = email;
            } else {
                Toast.makeText(this, "Failed to update record", Toast.LENGTH_SHORT).show();
            }
        } finally {
            db.close();
        }
    }

    public void logout(View v) {
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);            // Clear the activity stack
        startActivity(intent);
        finish();
    }

    public void map(View v) {
        Intent intent = new Intent(UserProfileActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
