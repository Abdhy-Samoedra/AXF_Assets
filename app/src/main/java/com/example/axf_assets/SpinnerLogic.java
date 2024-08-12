package com.example.axf_assets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class SpinnerLogic extends AppCompatActivity {

    protected Spinner spinner_homepage;
    protected ImageButton hamburger_button;
    private boolean isSpinnerInitial = true;
    protected TextView username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId()); // Use the layout resource provided by the subclass

        spinner_homepage = findViewById(R.id.spinner_homepage);
        hamburger_button = findViewById(R.id.hamburger_button);
        username = findViewById(R.id.username_input);

        // Image resources
        int[] images = {
                R.drawable.home_image,
                R.drawable.items,
                R.drawable.profile,
                R.drawable.log_out,
        };

        // Spinner items
        String[] items = getResources().getStringArray(R.array.spinner_items);

        CustomSpinner adapter = new CustomSpinner(this, items, images);
        spinner_homepage.setAdapter(adapter);

        hamburger_button.setOnClickListener(v -> spinner_homepage.performClick());

        spinner_homepage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SpinnerSelection", "Item selected at position: " + position);
                if (isSpinnerInitial) {
                    isSpinnerInitial = false;
                    return;
                }
                handleSpinnerSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    protected abstract int getLayoutResourceId();

    protected void handleSpinnerSelection(int position) {
        switch (position) {
            case 0: // Home
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case 1: // Items
                startActivity(new Intent(this, ListItem.class));
                break;
            case 2: // Profile
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();

                String usernameText = username.getText().toString();
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("USERNAME", usernameText);
                startActivity(intent);
                break;
            case 3: // Log Out
                Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
