package com.example.myapplication;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Inicjalizacja mapy
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Ustawienie przykładowej lokalizacji na mapie
        LatLng exampleLocation = new LatLng(52.2297, 21.0122); // Warszawa, Polska
        googleMap.addMarker(new MarkerOptions().position(exampleLocation).title("Przykładowa lokalizacja"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(exampleLocation, 10));
    }
}
