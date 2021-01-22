package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotoflash1();
        gotoflash2();
        gotorandom();

    }


    private void gotoflash1() {
        Button goto_flash1 = findViewById(R.id.Go_To_Flash1);
        goto_flash1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });

}


    private void gotoflash2() {
        Button goto_flash2 = findViewById(R.id.Go_To_Flash_set_2);
        goto_flash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this, Flash2.class));
            }
        });
    }

    private void gotorandom() {
        Button goto_flash2 = findViewById(R.id.RandomB);
        goto_flash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, RandomQuesions.class));
            }
        });
    }


}

