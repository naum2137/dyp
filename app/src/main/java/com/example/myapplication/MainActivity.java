package com.example.myapplication;  // Upewnij się, że to pasuje do twojego pakietu


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przejście do ekranu rekomendacji tras
                startActivity(new Intent(MainActivity.this, com.example.myapplication.RouteRecommendationActivity.class));
            }
        });
    }
}
