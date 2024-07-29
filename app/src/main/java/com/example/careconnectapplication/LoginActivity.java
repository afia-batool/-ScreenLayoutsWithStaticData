package com.example.careconnectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {

    EditText Username, Password;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.editTextTextLoginUsername);
        Password = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(v -> {
            String username = Username.getText().toString();
            String password = Password.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                String storedUsername = sharedPreferences.getString("username", null);
                String storedPassword = sharedPreferences.getString("password", null);

                Log.d("LoginActivity", "Stored Username: " + storedUsername);
                Log.d("LoginActivity", "Stored Password: " + storedPassword);

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("logged_in_user", username);
                    editor.apply();

                    // Redirect to another activity after login success
                    Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
