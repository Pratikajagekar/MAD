package com.example.database_handling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button b1, b2;
    TextView t1;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        t1 = (TextView) findViewById(R.id.textView3);

        try {
            db = openOrCreateDatabase("StudentDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Temp(id integer, name text)");
        } catch (SQLException e) {
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText eid = (EditText) findViewById(R.id.editText1);
                EditText ename = (EditText) findViewById(R.id.editText2);

                ContentValues values = new ContentValues();
                values.put("id", eid.getText().toString());
                values.put("name", ename.getText().toString());

                if ((db.insert("Temp", null, values)) != -1) {
                    Toast.makeText(MainActivity.this, "Record Successfully Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insert Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str = "";
                Cursor c = db.rawQuery("SELECT * FROM Temp", null);

                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        str = str + c.getString(0) + " " + c.getString(1) + "\n";
                        c.moveToNext();
                    }
                }

                t1.setText(str);
                c.close();
            }
        });
    }
}