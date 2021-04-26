package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public double cash = 100;
    public double debt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Testing button goes to home screen intent
        final Button testingButton = findViewById(R.id.testingButton);
        testingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                intent.putExtra("cash",cash);
                intent.putExtra("debt",debt);
                //starts the HomeScreen Activity
                MainActivity.this.startActivity(intent);
            }
        });

    }





    // No public static void main
    // If you have a variable you want to access throughout multiple methods declare it
    // ABOVE on creates --> instantiate within on create
}