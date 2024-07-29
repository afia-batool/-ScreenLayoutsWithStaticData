package com.example.careconnectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText Username, Email, Password, Confirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.usernameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwordEditText);
        Confirm = findViewById(R.id.confirmPasswordEditText);
        btn = findViewById(R.id.registerButton);
        tv = findViewById(R.id.existingUserTextView);

        tv.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btn.setOnClickListener(v -> {
            String username = Username.getText().toString();
            String email = Email.getText().toString();
            String password = Password.getText().toString();
            String confirm = Confirm.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
            } else {
                if (password.equals(confirm)) {
                    if (isValid(password)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters with letters," +
                                " digits, and special symbols", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean isValid(String password) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (password.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < password.length(); p++) {
                if (Character.isLetter(password.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < password.length(); r++) {
                if (Character.isDigit(password.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < password.length(); s++) {
                char c = password.charAt(s);
                if ((c >= 33 && c <= 46) || c == 64) {
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}
