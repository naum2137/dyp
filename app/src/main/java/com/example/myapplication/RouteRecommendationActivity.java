package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Arrays;
import java.util.List;

public class RouteRecommendationActivity extends AppCompatActivity {
    private static final int START_LOCATION_REQUEST = 1;
    private static final int END_LOCATION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_recommendation);

        Places.initialize(getApplicationContext(), "");

        Button setStartLocationButton = findViewById(R.id.set_start_location_button);
        Button setEndLocationButton = findViewById(R.id.set_end_location_button);

        setStartLocationButton.setOnClickListener(v -> openPlacePicker(START_LOCATION_REQUEST));
        setEndLocationButton.setOnClickListener(v -> openPlacePicker(END_LOCATION_REQUEST));
    }

    private void openPlacePicker(int requestCode) {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            String locationMessage = "Wybrano lokalizację: " + place.getName();
            if (requestCode == START_LOCATION_REQUEST) {
                Toast.makeText(this, "Początkowa: " + locationMessage, Toast.LENGTH_SHORT).show();
            } else if (requestCode == END_LOCATION_REQUEST) {
                Toast.makeText(this, "Końcowa: " + locationMessage, Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Toast.makeText(this, "Błąd wyboru lokalizacji", Toast.LENGTH_SHORT).show();
        }
    }
}