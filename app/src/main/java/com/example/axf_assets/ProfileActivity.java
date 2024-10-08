package com.example.axf_assets;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileActivity extends SpinnerLogic {

    TextView usernameGreeting;
    TextView usernameHandle;
    TextView emailUser;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this is for set up the username in profile
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username_value = sharedPreferences.getString("USERNAME", "Guest");

        // Initialize TextViews
        usernameGreeting = findViewById(R.id.username_greeting);
        usernameHandle = findViewById(R.id.username_handle);
        emailUser = findViewById(R.id.email_user);


        usernameGreeting.setText("Hi, " + username_value);
        usernameHandle.setText("@" + username_value);
        emailUser.setText(username_value + "@gmail.com");
    }
}
