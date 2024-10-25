package com.example.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckInternetAndPermissionsActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private TextView statusText;
    private Button retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        statusText = findViewById(R.id.internet_status_text);
        retryButton = findViewById(R.id.retry_button);

        // Sprawdź połączenie internetowe oraz uprawnienia
        checkInternetAndPermissions();

        // Obsługa przycisku "Sprawdź ponownie"
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetAndPermissions();
            }
        });
    }

    // Funkcja sprawdzająca połączenie internetowe oraz uprawnienia
    private void checkInternetAndPermissions() {
        if (isConnected()) {
            // Jeśli Internet jest dostępny, sprawdź uprawnienia
            checkPermissions();
        } else {
            // Brak połączenia z Internetem – pokaż komunikat
            statusText.setText("Brak połączenia z Internetem. Sprawdź połączenie i spróbuj ponownie.");
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    // Funkcja sprawdzająca, czy urządzenie ma połączenie z Internetem
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    // Funkcja sprawdzająca, czy aplikacja ma wymagane uprawnienia
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Poproś o uprawnienia, jeśli nie zostały nadane
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            // Jeśli uprawnienia są nadane, przejdź do głównej aktywności
            proceedToMainActivity();
        }
    }

    // Obsługa wyniku prośby o uprawnienia
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Uprawnienia nadane, przejdź do głównej aktywności
                proceedToMainActivity();
            } else {
                // Uprawnienia nie zostały nadane – pokaż komunikat
                Toast.makeText(this, "Aplikacja potrzebuje uprawnień do lokalizacji, aby działać.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Przejście do głównej aktywności
    private void proceedToMainActivity() {
        Intent intent = new Intent(CheckInternetAndPermissionsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Zamknij aktywność sprawdzania Internetu
    }
}
