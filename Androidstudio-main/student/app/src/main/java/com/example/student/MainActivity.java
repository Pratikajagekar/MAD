package com.example.student;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRoll, etCourse, etMobile;
    Button btnRegister;
    ListView listView;

    ArrayList<String> studentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etCourse = findViewById(R.id.etCourse);
        etMobile = findViewById(R.id.etMobile);
        btnRegister = findViewById(R.id.btnRegister);
        listView = findViewById(R.id.listView);

        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                studentList);

        listView.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String roll = etRoll.getText().toString();
                String course = etCourse.getText().toString();
                String mobile = etMobile.getText().toString();

                if(name.isEmpty() || roll.isEmpty() ||
                        course.isEmpty() || mobile.isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String data = "Name: " + name +
                            "\nRoll No: " + roll +
                            "\nCourse: " + course +
                            "\nMobile: " + mobile;

                    studentList.add(data);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this,
                            "Student Registered",
                            Toast.LENGTH_SHORT).show();

                    // Clear fields
                    etName.setText("");
                    etRoll.setText("");
                    etCourse.setText("");
                    etMobile.setText("");
                }
            }
        });
    }
}
