package com.example.axf_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText username_input;
    EditText password_input;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.username_user);
        password_input = findViewById(R.id.password_user);
        button_submit = findViewById(R.id.submitButton);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_input.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Username Can't Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_input.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Password Can't Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_input.length() < 8)
                {
                    Toast.makeText(MainActivity.this, "Password Must Be Greater Than 7", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USERNAME", username_input.getText().toString());
                    boolean isSaved = editor.commit();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
//        for checking the username input
    }
}