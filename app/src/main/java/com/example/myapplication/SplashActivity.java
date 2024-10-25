package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 1000; // 1 sekunda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Wyświetl ekran powitalny przez 1 sekundę, a potem przejdź do ekranu sprawdzania Internetu
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, CheckInternetAndPermissionsActivity.class));
                finish(); // Zamknij SplashActivity
            }
        }, SPLASH_SCREEN_DURATION);
    }
}