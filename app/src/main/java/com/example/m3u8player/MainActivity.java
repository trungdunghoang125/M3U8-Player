package com.example.m3u8player;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button M3U8PlayerButton;
    private Button MP3PlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        M3U8PlayerButton = findViewById(R.id.m3u8_player_btn);
        MP3PlayerButton = findViewById(R.id.mp3_player_btn);

        M3U8PlayerButton.setOnClickListener(view -> {
            startM3U8Player();
        });

        MP3PlayerButton.setOnClickListener(view -> {
            startMP3Player();
        });
    }

    private void startMP3Player() {
        Intent intent = new Intent(this, VOVMediaStreamingActivity.class);
        startActivity(intent);
    }

    private void startM3U8Player() {
        Intent intent = new Intent(this, M3U8PlayerActivity.class);
        startActivity(intent);
    }
}