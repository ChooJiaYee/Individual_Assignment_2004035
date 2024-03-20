package com.example.individualassignment2004035;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        // Comparing Numbers Button
        Button compareButton = findViewById(R.id.compareButton);
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, CompareNumbers.class);
                startActivity(intent);
            }
        });

        // Ordering Numbers Button
        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, OrderNumbers.class);
                startActivity(intent);
            }
        });

        // Composing Numbers Button
        Button composeButton = findViewById(R.id.composeButton);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, ComposeNumbers.class);
                startActivity(intent);
            }
        });

        // Back Button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
