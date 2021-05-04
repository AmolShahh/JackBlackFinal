package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoanScreen extends AppCompatActivity {
    double cash, debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_loan);

        cash = getIntent().getDoubleExtra("cash", 0);
        debt = getIntent().getDoubleExtra("debt", 0);

        TextView cashTV = findViewById(R.id.cashTV);
        TextView debtTV = findViewById(R.id.debtTextView);

        cashTV.setText("Cash: $" + Math.round(cash));
        debtTV.setText("Debt: $" + Math.round(debt));

        final Button backHome = findViewById(R.id.goHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(LoanScreen.this, HomeScreen.class);
                intent.putExtra("cash", cash);
                intent.putExtra("debt", debt);
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
                intent.putExtra("cash", cash);
                intent.putExtra("debt", debt);
                //starts the HomeScreen Activity
                LoanScreen.this.startActivity(intent);
            }
        });
    }

    public void getLoan() {
        EditText loanView = findViewById(R.id.editTextNumber);
        String loanValue = String.valueOf(loanView.getText());
        if (loanValue.equals("")) {
            loanValue = "0";
        }
        double loanAmount = Integer.valueOf(loanValue);
        cash += loanAmount;
        debt += loanAmount * 1.05;
        Log.i("manaans", String.valueOf(loanAmount));

    }
}