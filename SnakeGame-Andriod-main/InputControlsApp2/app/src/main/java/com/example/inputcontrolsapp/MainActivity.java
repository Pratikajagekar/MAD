package com.example.inputcontrolsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    CheckBox checkBox;
    RadioGroup radioGroup;
    ToggleButton toggleButton;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.imageButton);
        checkBox = findViewById(R.id.checkBox1);
        radioGroup = findViewById(R.id.radioGroup);
        toggleButton = findViewById(R.id.toggleButton);
        spinner = findViewById(R.id.sp1);

        // Image Button Click
        imageButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Image Button Clicked",
                        Toast.LENGTH_SHORT).show());

        // CheckBox Click
        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                Toast.makeText(MainActivity.this,
                        "CheckBox Selected",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "CheckBox Unselected",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // RadioGroup Selection
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected =
                    findViewById(checkedId);
            Toast.makeText(MainActivity.this,
                    "Selected: " + selected.getText(),
                    Toast.LENGTH_SHORT).show();
        });

        // Toggle Button
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this,
                        "Toggle is ON",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Toggle is OFF",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.names,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        String text =
                                parent.getItemAtPosition(position).toString();

                        Toast.makeText(MainActivity.this,
                                "Spinner: " + text,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }
}