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
import android.widget.Toast;

public class RepayDebtScreen extends AppCompatActivity {
    double cash, debt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_debt_screen);

        cash = getIntent().getDoubleExtra("cash", 0);
        debt = getIntent().getDoubleExtra("debt", 0);

        TextView cashTV = findViewById(R.id.cashTextView2);
        TextView debtTV = findViewById(R.id.debtTextView2);

        cashTV.setText("Cash: $" + Math.round(cash));
        debtTV.setText("Debt: $" + Math.round(debt));

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
                boolean repaymentWorked = getRepayment();
                if(repaymentWorked) {
                    Intent intent = new Intent(RepayDebtScreen.this, HomeScreen.class);
                    intent.putExtra("cash", cash);
                    intent.putExtra("debt", debt);

                //starts the HomeScreen Activity
                RepayDebtScreen.this.startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "You don't have this much debt", Toast.LENGTH_SHORT);

                    toast.show();
                }
            }
        });
    }

    public boolean getRepayment(){
        EditText loanView = findViewById(R.id.editTextNumber2);
        String loanValue = String.valueOf(loanView.getText());
        if(loanValue.equals("")){
            loanValue = "0";
        }
        if(Integer.valueOf(loanValue) < cash && Integer.valueOf(loanValue) <= debt+1) {
            double loanAmount = Integer.valueOf(loanValue);
            cash -= loanAmount;
            debt -= loanAmount;
            Log.i("manaans", String.valueOf(loanAmount));
            return true;
        }
        else {
            Log.i("manaans", "ERROR");
            return false;
        }

    }
}