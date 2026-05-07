package com.example.smartconvertpro;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.units,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });
    }

    private void convert() {
        String input = inputValue.getText().toString();

        if (input.isEmpty()) {
            resultText.setText("Please enter value");
            return;
        }

        double value = Double.parseDouble(input);

        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();

        double result = convertUnits(value, from, to);

        resultText.setText("Result: " + result);
    }

    private double convertUnits(double value, String from, String to) {

        // Convert to meter
        if (from.equals("Kilometer")) value *= 1000;
        else if (from.equals("Centimeter")) value /= 100;

        // Convert from meter
        if (to.equals("Kilometer")) value /= 1000;
        else if (to.equals("Centimeter")) value *= 100;

        return value;
    }
}