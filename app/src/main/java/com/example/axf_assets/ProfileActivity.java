package com.example.axf_assets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends SpinnerLogic {

    TextView usernameGreeting;
    TextView usernameHandle;
    TextView emailUser;

    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usernameGreeting = findViewById(R.id.username_greeting);
        usernameHandle = findViewById(R.id.username_handle);
        emailUser = findViewById(R.id.email_user);

        // Get the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");

        // Set the username to the TextViews
        if (username != null) {
            usernameGreeting.setText("Hi, " + username);
            usernameHandle.setText("@" + username);
            emailUser.setText(username + "@gmail.com");
        }
    }

}
