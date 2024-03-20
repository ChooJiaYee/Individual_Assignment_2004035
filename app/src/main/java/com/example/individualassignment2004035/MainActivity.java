package com.example.individualassignment2004035;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play Button
        Button playBtn = findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to activity_choose_game.xml
                Intent intent = new Intent(MainActivity.this, ChooseGame.class);
                startActivity(intent);
            }
        });

        // How to Play Button
        Button howToPlayBtn = findViewById(R.id.howToPlayBtn);
        howToPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to activity_how_to_play.xml
                Intent intent = new Intent(MainActivity.this, HowToPlay.class);
                startActivity(intent);
            }
        });

        // Exit Button
        Button exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Finish all activities in the stack
            }
        });
    }
}
