package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoanScreen extends AppCompatActivity {
    public double totalLoans = 0;
    public double totalCash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_loan);

        double cash = getIntent().getDoubleExtra("cash", -1);
        double debt = getIntent().getDoubleExtra("debt", -1);

        final Button backHome = findViewById(R.id.goHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(LoanScreen.this, MainActivity.class);

                //starts the HomeScreen Activity
                LoanScreen.this.startActivity(intent);
            }
        });

        final Button takeLoan = findViewById(R.id.loanButton);
        takeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                getLoan();
                Intent intent = new Intent(LoanScreen.this, HomeScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                LoanScreen.this.startActivity(intent);
            }
        });
    }

    public void getLoan(){
        double loanAmount = Integer.valueOf(String.valueOf((findViewById(R.id.loanAmountNumber))));
        Log.i("manaans", String.valueOf(loanAmount));

    }
}