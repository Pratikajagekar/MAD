package com.example.eventhandlingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button btnAlert, btnRating, btnProgress;
    RatingBar ratingBar;
    ProgressBar progressBar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlert = findViewById(R.id.btnAlert);
        btnRating = findViewById(R.id.btnRating);
        btnProgress = findViewById(R.id.btnProgress);
        ratingBar = findViewById(R.id.ratingBar);
        progressBar = findViewById(R.id.progressBar);
        spinner = findViewById(R.id.spinner);

        // Spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.languages,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Rating
        btnRating.setOnClickListener(v -> {
            String rating = String.valueOf(ratingBar.getRating());
            Toast.makeText(MainActivity.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
        });

        // Progress Bar
        btnProgress.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
        });

        // Alert Dialog
        btnAlert.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Alert")
                    .setMessage("Do you want to close this app?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> finish())
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        });

        // Toast when app starts
        Toast.makeText(this,"Welcome to Android Program",Toast.LENGTH_SHORT).show();
    }
}