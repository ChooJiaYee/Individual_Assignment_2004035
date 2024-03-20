package com.example.individualassignment2004035;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ComposeNumbers extends AppCompatActivity {

    private TextView composeTitle;
    private TextView instructionTextView;
    private TextView givenNumberTextView;
    private TextView equationTextView;
    private EditText firstNumberEditText;
    private EditText secondNumberEditText;
    private Button checkAnswerButton;
    private Button nextQuestionButton;
    private Button exitButton;

    private int targetNumber;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_number);

        // Initialize views
        composeTitle = findViewById(R.id.composeTitle);
        instructionTextView = findViewById(R.id.instructionTextView);
        givenNumberTextView = findViewById(R.id.givenNumberTextView);
        equationTextView = findViewById(R.id.equationTextView);
        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);
        checkAnswerButton = findViewById(R.id.checkAnswerButton2);
        nextQuestionButton = findViewById(R.id.nextQuestionButton2);
        exitButton = findViewById(R.id.exitButton2);

        // Initialize random
        random = new Random();

        // Generate the first question
        generateQuestion();

        // Set click listeners
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void generateQuestion() {
        // Generate target number (random number between 10 and 99)
        targetNumber = random.nextInt(90) + 10;
        givenNumberTextView.setText(String.valueOf(targetNumber));

        // Randomly choose between addition and subtraction
        boolean isAddition = random.nextBoolean();
        if (isAddition) {
            equationTextView.setText("+");
        } else {
            equationTextView.setText("-");
        }

        // Clear input fields
        firstNumberEditText.setText("");
        secondNumberEditText.setText("");
    }

    private void checkAnswer() {
        // Get user input
        String firstNumberStr = firstNumberEditText.getText().toString();
        String secondNumberStr = secondNumberEditText.getText().toString();

        if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input to integers
        int firstNumber = Integer.parseInt(firstNumberStr);
        int secondNumber = Integer.parseInt(secondNumberStr);

        // Calculate the result based on the equation
        int result;
        if (equationTextView.getText().toString().equals("+")) {
            result = firstNumber + secondNumber;
        } else {
            result = firstNumber - secondNumber;
        }

        // Check if the result matches the target number
        if (result == targetNumber) {
            Toast.makeText(this, "Correct! Please try the next question.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
