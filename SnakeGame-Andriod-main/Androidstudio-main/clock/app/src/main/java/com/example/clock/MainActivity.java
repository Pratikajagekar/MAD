package com.example.clock;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

public class MainActivity extends AppCompatActivity {

    AnalogClock analogClock;
    DigitalClock digitalClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        analogClock = findViewById(R.id.analogClock1);
        digitalClock = findViewById(R.id.digitalClock1);
    }
}