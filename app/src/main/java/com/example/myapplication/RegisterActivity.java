package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerEmailInput, registerPasswordInput, registerConfirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmailInput = findViewById(R.id.register_email_input);
        registerPasswordInput = findViewById(R.id.register_password_input);
        registerConfirmPasswordInput = findViewById(R.id.register_confirm_password_input);
        Button registerButton = findViewById(R.id.register_button);
        TextView loginLink = findViewById(R.id.login_link);

        // Obsługa przycisku "Zarejestruj się"
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dodaj logikę rejestracji
            }
        });

        // Obsługa przycisku "Masz już konto? Zaloguj się"
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

    }
}