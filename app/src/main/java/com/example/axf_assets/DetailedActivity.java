package com.example.axf_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.axf_assets.databinding.ActivityDetailedBinding;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.axf_assets.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String price = intent.getStringExtra("price");
            String trending = intent.getStringExtra("trending");
            int desc = intent.getIntExtra("desc", R.string.desc1);
            int image = intent.getIntExtra("image", R.drawable.skins1);
            int imageBanner = intent.getIntExtra("imageBanner", R.drawable.bannerskin1);

            // Set data to views
            binding.detailName.setText(name);
            binding.detailPrice.setText(price);
            binding.detailDesc.setText(getString(desc));  // Use getString() to resolve string resource
            binding.detailImage.setImageResource(image);
            binding.bannerImage.setImageResource(imageBanner);
            binding.detailTrending.setText(trending);
        }

        // Create an ArrayAdapter using the string array and a simple spinner item layout
        String[] array = getResources().getStringArray(R.array.payment);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        binding.spinner.setAdapter(adapter);

        // Set the click listener for the back button
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity, which will take the user back to the previous page
                finish();
            }
        });

        // Set the click listener for the Pay button
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email and selected payment method
                String email = binding.emailEditText.getText().toString().trim();
                String paymentMethod = binding.spinner.getSelectedItem().toString();

                // Enhanced Validation
                if (!isValidEmail(email)) {
                    showAlertDialog("Input Error", "Please input a valid email address.");
                } else if (paymentMethod.equals("--Choose Payment Method--")) {
                    showAlertDialog("Selection Error", "Please choose a payment method.");
                } else {
                    showSuccessDialog();
                }
            }
        });
    }

    // Method to validate the email format
    private boolean isValidEmail(String email) {
        // Regular expression to validate email format
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    // Method to show an error dialog
    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    // Method to show a success dialog
    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Payment successful!")
                .setPositiveButton("Go to Home", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirect to home page
                        Intent intent = new Intent(DetailedActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}





