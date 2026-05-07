package com.example.data_selection;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatePicker picker;
    Button displayDate;
    TextView textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1 = findViewById(R.id.textView1);
        picker = findViewById(R.id.datePicker);
        displayDate = findViewById(R.id.button1);

        // Set current date initially
        textview1.setText("Current Date: " + getCurrentDate());

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview1.setText("Selected Date: " + getCurrentDate());
            }
        });
    }

    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();

        int day = picker.getDayOfMonth();
        int month = picker.getMonth() + 1; // month is 0-based
        int year = picker.getYear();

        builder.append(day).append("/").append(month).append("/").append(year);

        return builder.toString();
    }
}