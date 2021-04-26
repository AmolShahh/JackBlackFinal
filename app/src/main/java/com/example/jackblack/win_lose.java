package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class win_lose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_win_lose);

        String result = getIntent().getStringExtra("result");
        TextView winLoseTextView = (TextView) findViewById(R.id.winLoseTextview);
        winLoseTextView.setText(result);


        final Button backHome = findViewById(R.id.homeButton);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(win_lose.this, MainActivity.class);

                //starts the HomeScreen Activity
                win_lose.this.startActivity(intent);
            }
        });
    }


}