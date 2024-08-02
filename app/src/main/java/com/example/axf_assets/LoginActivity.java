package com.example.axf_assets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username_input;
    EditText password_input;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_input = findViewById(R.id.username_user);
        password_input = findViewById(R.id.password_user);
        button_submit = findViewById(R.id.submitButton);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_input.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Username Can't Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_input.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Password Can't Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_input.length() < 8)
                {
                    Toast.makeText(LoginActivity.this, "Password Must Be Greater Than 7", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this).create();
                    dialog.setTitle("Confirmation");
                    dialog.setMessage("All Is Set Up");
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(String.valueOf(LoginActivity.this), null);
                            startActivity(intent);

                        }
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
            }
        });

//        for checking the username input
    }
}