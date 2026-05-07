package com.example.allmenusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        // ✅ STEP 5.1: Register Context Menu
        registerForContextMenu(textView);

        // ✅ STEP 5.2: Popup Menu
        button.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, button);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(MainActivity.this,
                        "Clicked: " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return true;
            });

            popupMenu.show();
        });
    }

    // ✅ STEP 5.3: Option Menu Create
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    // ✅ STEP 5.4: Option Menu Click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.red)
            layout.setBackgroundColor(Color.RED);
        else if (item.getItemId() == R.id.green)
            layout.setBackgroundColor(Color.GREEN);
        else if (item.getItemId() == R.id.blue)
            layout.setBackgroundColor(Color.BLUE);

        return true;
    }

    // ✅ STEP 5.5: Context Menu Create
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Choose Color");
        menu.add(0, 1, 0, "Yellow");
        menu.add(0, 2, 0, "Gray");
        menu.add(0, 3, 0, "Cyan");
    }

    // ✅ STEP 5.6: Context Menu Click
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle().equals("Yellow"))
            layout.setBackgroundColor(Color.YELLOW);
        else if (item.getTitle().equals("Gray"))
            layout.setBackgroundColor(Color.GRAY);
        else if (item.getTitle().equals("Cyan"))
            layout.setBackgroundColor(Color.CYAN);

        return true;
    }
}