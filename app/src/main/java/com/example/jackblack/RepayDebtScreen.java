package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RepayDebtScreen extends AppCompatActivity {
    double cash, debt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_debt_screen);

        cash = getIntent().getDoubleExtra("cash", -1);
        debt = getIntent().getDoubleExtra("debt", -1);

        final Button backHome = findViewById(R.id.goHome2);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(RepayDebtScreen.this, HomeScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                RepayDebtScreen.this.startActivity(intent);
            }
        });

        final Button takeLoan = findViewById(R.id.repayDebtButton);
        takeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                getRepayment();
                Intent intent = new Intent(RepayDebtScreen.this, HomeScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                RepayDebtScreen.this.startActivity(intent);
            }
        });
    }

    public void getRepayment(){
        EditText loanView = findViewById(R.id.editTextNumber2);
        String loanValue = String.valueOf(loanView.getText());
        if(loanValue.equals("")){
            loanValue = "0";
        }
        if(Integer.valueOf(loanValue) < cash) {
            double loanAmount = Integer.valueOf(loanValue);
            cash -= loanAmount;
            debt -= loanAmount;
            Log.i("manaans", String.valueOf(loanAmount));
        }
        else {
            Log.i("manaans", "You are poor, not enough cash. come back when you have money pleb");
        }

    }
}