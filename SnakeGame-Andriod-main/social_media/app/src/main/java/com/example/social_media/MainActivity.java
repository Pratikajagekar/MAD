package com.example.social_media;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnWp, btnInsta, btnYT, btnFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWp = findViewById(R.id.btnWp);
        btnInsta = findViewById(R.id.btnInsta);
        btnYT = findViewById(R.id.btnYT);
        btnFB = findViewById(R.id.btnFB);

        btnWp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse("https://web.whatsapp.com"));
                startActivity(whatsappIntent);
            }
        });
        btnInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent instaIntent = new Intent(Intent.ACTION_VIEW);
                instaIntent.setData(Uri.parse("https://instagram.com"));
                startActivity(instaIntent);
            }
        });
        btnYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent YTIntent = new Intent(Intent.ACTION_VIEW);
                YTIntent.setData(Uri.parse("https://youtube.com"));
                startActivity(YTIntent);
            }
        });
        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = new Intent(Intent.ACTION_VIEW);
                fbIntent.setData(Uri.parse("https://facebook.com"));
                startActivity(fbIntent);
            }
        });
    }
}