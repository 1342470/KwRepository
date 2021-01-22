package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RandomQuesions extends AppCompatActivity {
    private TextView questions_view;
    private TextView score_counter;

    int score = 0;
    int numOfQuesses = 3;
    int CurrentListNum = 0;
    int skips = 3;
    int gennedRan = (int) Math.random();



    //create a arrary with all of the questions to be inside
    private QuestionsItem[] Qlist = new QuestionsItem[]{
            new QuestionsItem(R.string.question_1, true),
            new QuestionsItem(R.string.question_2, false),
            new QuestionsItem(R.string.question_3, false),
            new QuestionsItem(R.string.question_4, true),
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
        genRan();
        // set questions view to match the textview with the id of questions
        questions_view = findViewById(R.id.questions);
        //set created buttons to their respective id's
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

        /*
        sets button to the id of the next button in the xml
        then sets onclick so when the button is pressed currenetlistnum has a value of one added it its self
        once the value has been added it will run the change question function that the change the output the match the new value of currentlistnum
        after it will minus one to the skips interger which once at 0 will set it so the currentlist num can't add one to it's self meaning it wont skip quesitons
        finaly it updates the counter to show the amout of skips the user can use.
         */
        Button next_button = findViewById(R.id.next);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentListNum = (CurrentListNum + gennedRan + 1) % Qlist.length;
                changeQuestion();
                skips--;
                skipsleft();
                showskips();
                genRan();
            }
        });

        changeQuestion();
    }
    //create method to switch to the next question
    private void changeQuestion() {
        int question = Qlist[CurrentListNum].setText();
        questions_view.setText(question);
    }

    //shows the current score

    /**
     *
     */

    private void setscore(){
        score_counter = findViewById(R.id.Current_score);
        score_counter.setText(String.valueOf("Current score" + " " + score));
    }

    //shows current amount of guesses
    private void showguesses(){
        score_counter = findViewById(R.id.Guesses);
        score_counter.setText(String.valueOf("Guesses left" + " " + numOfQuesses));
    }

    //shows how many skips the user can use
    private void showskips(){
        score_counter = findViewById(R.id.Skips);
        score_counter.setText(String.valueOf("Skips left" + " " + skips));
    }


    //methord that if the number of guesses reachs 0 skip to the next question then set the number of quesses back to the orginal amout
    private void countdown(){
        if(numOfQuesses == 0) {
            CurrentListNum = (CurrentListNum + gennedRan +1) % Qlist.length;
            changeQuestion();
            if(numOfQuesses == 0){
                numOfQuesses = 4;
            }
        }
    }

    //methord that defines the winning condition if the score reaches 10 the app will take the user to the win page
    private void wincon() {
        if (score == 10) {
            startActivity(new Intent(RandomQuesions.this, Win.class));
        }
    }

    //methord that defines the winning condition if the score reaches 10 the app will take the user to the losse page
    private void losecon() {
        if (score == -10) {
            startActivity(new Intent(RandomQuesions.this, Lose.class));
        }
    }

    //methord at takes the user make to the home page once clicked
    private void gotToHome() {
        Button Back_to_home = findViewById(R.id.Back_to_home);
        Back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RandomQuesions.this, Home.class));
            }
        });
    }

    //method that defines the amount of skips a user has once skips = 0 the skip button will not work
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

    private int genRan(){
        int max = 8;
        int min = 1;
        int gennedRan = (int)(Math.random() * (max - min + 1) + min);
        return gennedRan;
    }


    /**
     * methord that checks to see if the id of the button clicked matches the answer depend on the answer a toast
     *     stating their answer will be printed out and a score will be taken or added depending on if the answer matches
     *     if the anwer is correct the users number of guesses will go back to 3 and finaly it will run the wincon
     *     function to check if the users score matches the win conditon the same apilys to the incorret answer
     *     other than it will run the losecon funcion instead.
     * @param clicked
     */
    private void checkAnswer(boolean clicked){
        boolean correct = Qlist[CurrentListNum].checkAnswer();
        int toastoutput;
        if (clicked == correct){
            CurrentListNum = (CurrentListNum + gennedRan + 1 ) % Qlist.length;
            changeQuestion();
            toastoutput = R.string.output_correct;
            score++;
            numOfQuesses = 3;
            wincon();
            genRan();


        }else{
            toastoutput = R.string.output_incorrect;
            countdown();
            numOfQuesses--;
            score--;
            showguesses();
            losecon();
            genRan();

        }
        setscore();
        Toast.makeText(this, toastoutput, Toast.LENGTH_SHORT).show();
    }
}





