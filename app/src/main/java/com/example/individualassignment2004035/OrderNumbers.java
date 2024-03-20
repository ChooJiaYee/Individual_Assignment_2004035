package com.example.individualassignment2004035;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrangeNumbers extends AppCompatActivity {

    private TextView orderTitle;
    private TextView instructionTextView2;
    private LinearLayout numbersLayout;
    private LinearLayout answerLayout;
    private Button checkAnswerButton3;
    private Button nextQuestionButton3;
    private Button backButton3;

    private ArrayList<Integer> numbers;
    private ArrayList<Button> numberButtons;
    private ArrayList<Button> answerButtons;
    private boolean isAscendingOrder;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_numbers);

        // Initialize views
        orderTitle = findViewById(R.id.orderTitle);
        instructionTextView2 = findViewById(R.id.instructionTextView2);
        numbersLayout = findViewById(R.id.numbersLayout);
        answerLayout = findViewById(R.id.answerLayout);
        checkAnswerButton3 = findViewById(R.id.checkAnswerButton3);
        nextQuestionButton3 = findViewById(R.id.nextQuestionButton3);
        backButton3 = findViewById(R.id.backButton3);

        // Initialize variables
        numbers = new ArrayList<>();
        numberButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();
        random = new Random();

        // Set initial instruction
        setInstruction();

        // Generate numbers and display them
        generateNumbers();
        displayNumbers();

        // Set click listeners
        for (Button button : numberButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAnswer(button.getText().toString());
                }
            });
        }

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setInstruction() {
        // Randomly choose instruction
        isAscendingOrder = random.nextBoolean();
        String instruction = isAscendingOrder ? "Please arrange the numbers from smallest to biggest" :
                "Please arrange the numbers from biggest to smallest";
        instructionTextView.setText(instruction);
    }

    private void generateNumbers() {
        numbers.clear();
        for (int i = 1; i <= 3; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    private void displayNumbers() {
        numbersLayout.removeAllViews();
        numberButtons.clear();
        for (int i = 0; i < numbers.size(); i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(numbers.get(i)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 0, 8, 0);
            button.setLayoutParams(layoutParams);
            numbersLayout.addView(button);
            numberButtons.add(button);
        }
    }

    private void addAnswer(String number) {
        Button button = new Button(this);
        button.setText(number);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        button.setLayoutParams(layoutParams);
        answerLayout.addView(button);
        answerButtons.add(button);
    }

    private void checkAnswer() {
        ArrayList<Integer> sortedNumbers = new ArrayList<>();
        for (Button button : answerButtons) {
            sortedNumbers.add(Integer.parseInt(button.getText().toString()));
        }
        Collections.sort(sortedNumbers);

        boolean isCorrect = true;
        for (int i = 0; i < sortedNumbers.size(); i++) {
            if (!numbers.get(i).equals(sortedNumbers.get(i))) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            Toast.makeText(this, "Correct! You arranged the numbers correctly.", Toast.LENGTH_SHORT).show();
            resetGame();
        } else {
            Toast.makeText(this, "Incorrect. Please try again.", Toast.LENGTH_SHORT).show();
            answerLayout.removeAll
