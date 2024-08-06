package com.example.axf_assets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView usernameGreeting;
    TextView usernameHandle;
    TextView email_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameGreeting = findViewById(R.id.username_greeting);
        usernameHandle = findViewById(R.id.username_handle);

        // Get the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");

        // Set the username to the TextViews
        if (username != null) {
            usernameGreeting.setText("Hi, " + username);
            usernameHandle.setText("@" + username);
//            email_user.setText(username + "@gmail.com");
        }
    }
}
