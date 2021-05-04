package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BetScreen extends AppCompatActivity {
    double cash, debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_screen);

        cash = getIntent().getDoubleExtra("cash", 0);
        debt = getIntent().getDoubleExtra("debt", 0);

        TextView cashTV = findViewById(R.id.cashTV2);
        TextView debtTV = findViewById(R.id.debtTextView3);

        cashTV.setText("Cash: $" + Math.round(cash));
        debtTV.setText("Debt: $" + Math.round(debt));

        final Button backHome = findViewById(R.id.goHome3);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(BetScreen.this, HomeScreen.class);
                intent.putExtra("cash", cash);
                intent.putExtra("debt", debt);
                //starts the HomeScreen Activity
                BetScreen.this.startActivity(intent);
            }
        });

        final Button betMoney = findViewById(R.id.betButton);
        betMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                int betAmount = Integer.valueOf(bet());
                if (betAmount == -1) {

                } else {
                    Intent intent = new Intent(BetScreen.this, GameLogic.class);
                    intent.putExtra("cash", cash);
                    intent.putExtra("debt", debt);
                    intent.putExtra("bet", betAmount);

                    //starts the HomeScreen Activity
                    BetScreen.this.startActivity(intent);
                }
            }
        });
    }

    public String bet() {
        EditText loanView = findViewById(R.id.editTextNumber3);
        String loanValue = String.valueOf(loanView.getText());
        if (loanValue.equals("")) {
            loanValue = "0";
        }
        if (Double.valueOf(loanValue) > cash) {
            Toast toast = Toast.makeText(getApplicationContext(), "WTF u doin", Toast.LENGTH_SHORT);

            toast.show();
            loanValue = "-1";
        }
        return loanValue;

    }
}