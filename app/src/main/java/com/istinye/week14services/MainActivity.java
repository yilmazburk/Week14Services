package com.istinye.week14services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnStoppedListener {

    private Button playButton;
    private boolean musicPlaying = false;
    private Intent musicIntent;


    private String musicURL = "https://www.redboxgamestudios.com/piano.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);

        CustomApplication.context = this;

        musicIntent = new Intent(MainActivity.this, MusicService.class);
        musicIntent.putExtra(MusicService.MUSIC_URL, musicURL);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!musicPlaying) {
                    playButton.setText("Pause");
                    playMusic();
                    musicPlaying = true;
                } else {
                    playButton.setText("Play");
                    stopMusic();
                    musicPlaying = false;
                }
            }
        });

    }

    private void stopMusic() {
        stopService(musicIntent);
    }

    private void playMusic() {
        startService(musicIntent);
    }

    @Override
    public void onMusicStopped() {
        playButton.setText("Play");
        musicPlaying = false;
    }
}