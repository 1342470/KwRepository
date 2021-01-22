package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Flash2 extends AppCompatActivity {



    private TextView questions_view;
    private TextView score_counter;

    int score = 0;
    int numOfQuesses = 3;
    int CurrentListNum = 0;
    int skips = 3;



    //create a arrary with all of the questions to be inside
    private QuestionsItem[] Qlist = new QuestionsItem[]{
            new QuestionsItem(R.string. question_1_flash2, true),
            new QuestionsItem(R.string.question_2_flash2, false),
            new QuestionsItem(R.string.question_3_flash2, true),
            new QuestionsItem(R.string.question_4_flash2, true)

    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setscore();
        showguesses();
        gotToHome();
        showskips();
        //set created buttons to their respective id's
        questions_view = findViewById(R.id.questions);
        Button correct_button = findViewById(R.id.correct_button);
        //set onclicklistener to check if the buttons id matches the answer
        correct_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        Button incorrect_button = findViewById(R.id.incorrect_button);
        incorrect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        Button next_button = findViewById(R.id.next);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentListNum = (CurrentListNum + 1) % Qlist.length;
                changeQuestion();
                skips--;
                skipsleft();
                showskips();
            }
        });

        changeQuestion();
    }
    //create method to switch to the next question
    private void changeQuestion() {
        int question = Qlist[CurrentListNum].setText();
        questions_view.setText(question);
    }

    //
    private void setscore(){
        score_counter = findViewById(R.id.Current_score);
        score_counter.setText(String.valueOf("Current score" + " " + score));
    }

    private void showguesses(){
        score_counter = findViewById(R.id.Guesses);
        score_counter.setText(String.valueOf("Guesses left" + " " + numOfQuesses));
    }

    private void showskips(){
        score_counter = findViewById(R.id.Skips);
        score_counter.setText(String.valueOf("Skips left" + " " + skips));
    }


    private void countdown(){
        if(numOfQuesses == 0) {
            CurrentListNum = (CurrentListNum + 1) % Qlist.length;
            changeQuestion();
            if(numOfQuesses == 0){
                numOfQuesses = 4;
            }
        }
    }

    private void wincon() {
        if (score == 10) {
            startActivity(new Intent(Flash2.this, Win.class));
        }
    }

    private void losecon() {
        if (score == -10) {
            startActivity(new Intent(Flash2.this, Lose.class));
        }
    }

    private void gotToHome() {
        Button Back_to_home = findViewById(R.id.Back_to_home);
        Back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Flash2.this, Home.class));
            }
        });
    }

    private void skipsleft(){
        if (skips == 0){
            Button next_button = findViewById(R.id.next);
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurrentListNum = (CurrentListNum + 0) % Qlist.length;
                    changeQuestion();
                    skips--;
                }
            });
        }
    }



    private void checkAnswer(boolean clicked){
        boolean correct = Qlist[CurrentListNum].checkAnswer();
        int toastoutput;
        if (clicked == correct){
            CurrentListNum = (CurrentListNum + 1) % Qlist.length;
            changeQuestion();
            toastoutput = R.string.output_correct;
            score++;
            numOfQuesses = 3;
            wincon();


        }else{
            toastoutput = R.string.output_incorrect;
            countdown();
            numOfQuesses--;
            score--;
            showguesses();
            losecon();

        }
        setscore();
        Toast.makeText(this, toastoutput, Toast.LENGTH_SHORT).show();
    }
}


