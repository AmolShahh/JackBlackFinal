package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    double cash, debt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        updateFinances();

        final Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(HomeScreen.this, BetScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
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

        final Button repayDebtButton = findViewById(R.id.repayButton);
        repayDebtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(HomeScreen.this, RepayDebtScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                HomeScreen.this.startActivity(intent);
            }
        });


    }

    public void onResume(){
        super.onResume();

        updateFinances();
    }


    public void updateFinances(){
         cash = getIntent().getDoubleExtra("cash", 0);
         debt = getIntent().getDoubleExtra("debt", 0);

        TextView cashTV = findViewById(R.id.cashTV);
        TextView debtTV = findViewById(R.id.debtTextView);

        cashTV.setText("Cash: $" + Math.round(cash));
        debtTV.setText("Debt: $" + Math.round(debt));
    }

}