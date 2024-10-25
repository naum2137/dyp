package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RouteRecommendationActivity extends AppCompatActivity {
    private static final int START_LOCATION_REQUEST = 1;
    private static final int END_LOCATION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_recommendation);

        Button setStartLocationButton = findViewById(R.id.set_start_location_button);
        Button setEndLocationButton = findViewById(R.id.set_end_location_button);

        // Przycisk do ustawienia lokalizacji startowej
        setStartLocationButton.setOnClickListener(v -> openMapForLocation(START_LOCATION_REQUEST));

        // Przycisk do ustawienia lokalizacji końcowej
        setEndLocationButton.setOnClickListener(v -> openMapForLocation(END_LOCATION_REQUEST));
    }

    private void openMapForLocation(int requestCode) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(mapIntent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Otrzymaj wybraną lokalizację i przypisz ją jako lokalizację startową lub końcową
        if (resultCode == RESULT_OK) {
            if (requestCode == START_LOCATION_REQUEST) {
                // Zapisz lokalizację startową
            } else if (requestCode == END_LOCATION_REQUEST) {
                // Zapisz lokalizację końcową
            }
        }
    }
}
