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

public class OrderNumbers extends AppCompatActivity {

    private TextView orderTitle;
    private TextView instructionTextView2;
    private LinearLayout numbersLayout;
    private LinearLayout answerLayout;
    private Button checkAnswerButton3;
    private Button nextQuestionButton3;
    private Button exitButton3;

    private ArrayList<Integer> numbers;
    private ArrayList<Button> numberButtons;
    private ArrayList<Button> answerButtons;
    private boolean isAscendingOrder;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_numbers);

        orderTitle = findViewById(R.id.orderTitle);
        instructionTextView2 = findViewById(R.id.instructionTextView2);
        numbersLayout = findViewById(R.id.numbersLayout);
        answerLayout = findViewById(R.id.answerLayout);
        checkAnswerButton3 = findViewById(R.id.checkAnswerButton3);
        nextQuestionButton3 = findViewById(R.id.nextQuestionButton3);
        exitButton3 = findViewById(R.id.exitButton3);

        numbers = new ArrayList<>();
        numberButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();
        random = new Random();

        setInstruction();
        generateNumbers();
        displayNumbers();

        // Set click listener for number buttons
        for (Button button : numberButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    addAnswer(clickedButton.getText().toString());
                }
            });
        }

        checkAnswerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextQuestionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        exitButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Displays the question to the user, randomizes between ascending and descending order
    private void setInstruction() {
        isAscendingOrder = random.nextBoolean();
        String instruction = isAscendingOrder ? "Please arrange the numbers from smallest to biggest" :
                "Please arrange the numbers from biggest to smallest";
        instructionTextView2.setText(instruction);
    }

    // Generates 3 random numbers for question
    private void generateNumbers() {
        numbers.clear();
        for (int i = 0; i < 3; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
            } while (numbers.contains(randomNumber)); // Check if the number is already in the list
            numbers.add(randomNumber);
        }
        Collections.shuffle(numbers);
    }

    // Displays the generated numbers to the user
    private void displayNumbers() {
        numbersLayout.removeAllViews();
        numberButtons.clear();
        for (int i = 0; i < numbers.size(); i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(numbers.get(i)));
            button.setBackgroundResource(R.drawable.order_display_button); // Apply custom background
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

    // User arranging answer
    private void addAnswer(String number) {
        // Check if the number is already in the answerButtons list
        for (Button button : answerButtons) {
            if (button.getText().toString().equals(number)) {
                // If the number is already in the answerButtons list, remove it
                answerLayout.removeView(button); // Remove the button from the answerLayout
                answerButtons.remove(button); // Remove the button from the answerButtons list
                return; // Exit the method
            }
        }

        // If the number is not already in the answerButtons list, add it
        Button button = new Button(this);
        button.setText(number);
        button.setBackgroundResource(R.drawable.order_selected_answer_button); // Apply custom background
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        button.setLayoutParams(layoutParams);
        answerLayout.addView(button);
        answerButtons.add(button);  // Add the clicked number to the list in the order it was clicked
    }

    // Check answer button, also checks if answer is empty
    private void checkAnswer() {
        if (answerButtons.isEmpty()) {
            Toast.makeText(this, "Please select numbers before checking the answer.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Integer> userNumbers = new ArrayList<>();
        for (Button button : answerButtons) {
            userNumbers.add(Integer.parseInt(button.getText().toString()));
        }

        boolean isCorrect;
        if (isAscendingOrder) {
            isCorrect = isAscending(userNumbers);
        } else {
            isCorrect = isDescending(userNumbers);
        }

        if (isCorrect) {
            Toast.makeText(this, "Correct! You arranged the numbers correctly.",
                    Toast.LENGTH_SHORT).show();
            resetGame();
        } else {
            Toast.makeText(this, "Incorrect. Please try again.", Toast.LENGTH_SHORT).show();
            answerLayout.removeAllViews();
            answerButtons.clear();
        }
    }

    private boolean isAscending(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDescending(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }


    private void resetGame() {
        setInstruction();
        generateNumbers();
        displayNumbers();
        addNumberButtonListeners(); // Add this line to re-add the click listeners for number buttons
        answerLayout.removeAllViews();
        answerButtons.clear();
    }

    private void addNumberButtonListeners() {
        for (Button button : numberButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    addAnswer(clickedButton.getText().toString());
                }
            });
        }
    }


}