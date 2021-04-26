package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        double cash = getIntent().getDoubleExtra("cash", -1);
        double debt = getIntent().getDoubleExtra("debt", -1);

        final Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(HomeScreen.this, GameLogic.class);

                //starts the HomeScreen Activity
                HomeScreen.this.startActivity(intent);
            }
        });

        final Button loanButton = findViewById(R.id.loanButton);
        loanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(HomeScreen.this, LoanScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                HomeScreen.this.startActivity(intent);
            }
        });


    }

}