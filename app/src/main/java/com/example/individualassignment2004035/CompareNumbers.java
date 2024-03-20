package com.example.individualassignment2004035;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CompareNumbers extends AppCompatActivity {

    private TextView compareTitle;
    private TextView questionTextView;
    private Button numberButton1;
    private Button numberButton2;
    private Button checkAnswerButton;
    private Button nextQuestionButton;
    private Button exitButton;

    private int correctAnswer;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers);

        // Initialize views
        compareTitle = findViewById(R.id.compareTitle);
        questionTextView = findViewById(R.id.questionTextView);
        numberButton1 = findViewById(R.id.numberButton1);
        numberButton2 = findViewById(R.id.numberButton2);
        checkAnswerButton = findViewById(R.id.checkAnswerButton);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        exitButton = findViewById(R.id.exitButton);

        // Initialize random
        random = new Random();

        // Generate the first question
        generateQuestion();

        // Set click listeners
        numberButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highlightSelectedButton(numberButton1);
            }
        });

        numberButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highlightSelectedButton(numberButton2);
            }
        });

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
        // Generate two random numbers
        int number1 = random.nextInt(99);
        int number2 = random.nextInt(99);

        // Randomize whether the question asks for the bigger or smaller number
        boolean askForBiggerNumber = random.nextBoolean();

        // Display the question
        if (askForBiggerNumber) {
            questionTextView.setText("Which number is bigger? " + number1 + " or " + number2 + "?");
            // Store the correct answer
            correctAnswer = (number1 > number2) ? 1 : 2;
        } else {
            questionTextView.setText("Which number is smaller? " + number1 + " or " + number2 + "?");
            // Store the correct answer
            correctAnswer = (number1 < number2) ? 1 : 2;
        }

        // Set the numbers on the selection buttons
        numberButton1.setText(String.valueOf(number1));
        numberButton2.setText(String.valueOf(number2));

        // Reset button backgrounds
        numberButton1.setBackgroundResource(R.drawable.compare_selection_button);
        numberButton2.setBackgroundResource(R.drawable.compare_selection_button);

        // Enable selection buttons
        numberButton1.setEnabled(true);
        numberButton2.setEnabled(true);
    }

    private void highlightSelectedButton(Button selectedButton) {
        // Reset button backgrounds
        numberButton1.setBackgroundResource(R.drawable.compare_selection_button);
        numberButton2.setBackgroundResource(R.drawable.compare_selection_button);
        // Highlight the selected button
        selectedButton.setBackgroundResource(R.drawable.compare_selection_button_hightlighted);
    }

    private void checkAnswer() {
        // Disable selection buttons
        numberButton1.setEnabled(false);
        numberButton2.setEnabled(false);
        // Highlight the correct answer
        if (correctAnswer == 1) {
            numberButton1.setBackgroundResource(R.drawable.compare_selection_button_hightlighted_corrected);
        } else {
            numberButton2.setBackgroundResource(R.drawable.compare_selection_button_hightlighted_corrected);
        }
    }
}
