package com.example.jackblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class win_lose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_win_lose);

        String result = getIntent().getStringExtra("result");
        double cash = getIntent().getDoubleExtra("cash", 0);
        double debt = getIntent().getDoubleExtra("debt", 0);
        double betAmount = getIntent().getDoubleExtra("bet", 0);
        ArrayList<String> pHand = getIntent().getStringArrayListExtra("pHand");
        ArrayList<String> dHand = getIntent().getStringArrayListExtra("dHand");
        int playerTotal = getIntent().getIntExtra("pTotal", 0);
        int dealerTotal = getIntent().getIntExtra("dTotal", 0);

        TextView winLoseTextView = (TextView) findViewById(R.id.winLoseTextview);
        winLoseTextView.setText("You " + result + "!");
        Log.i("fatal", String.valueOf(pHand));
        String displayedPHand = prettifyCards(pHand);
        TextView playerHandTextView = (TextView) findViewById(R.id.playerHandTextView2);
        playerHandTextView.setText(displayedPHand);
        TextView pTotal = (TextView) findViewById(R.id.playerTotal2);
        TextView dTotal = (TextView) findViewById(R.id.dealerTotal2);

        ImageView jackBlack = (ImageView) findViewById(R.id.jackBlack);
        if(result.equals("win")){
            jackBlack.setImageResource(R.mipmap.jbp5);
        }
        else if (result.equals("lose")){
            jackBlack.setImageResource(R.mipmap.jpb6);
        }
        else {
            jackBlack.setImageResource(R.mipmap.jbp7);
        }

        pTotal.setText(String.valueOf("Player Total: " + playerTotal));
        dTotal.setText(String.valueOf("Dealer Total: " + dealerTotal));
        //Set dealer hand text view to dealer hand
        String displayedDHand = prettifyCards(dHand);
        TextView dealerHandTextView = (TextView) findViewById(R.id.dealerHandTextView2);
        dealerHandTextView.setText(displayedDHand);

        Log.i("bet", String.valueOf(betAmount));


        final Button backHome = findViewById(R.id.homeButton);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(win_lose.this, HomeScreen.class);
                if (result.equals("win")) {
                    intent.putExtra("cash", cash + betAmount * 1.5);
                    intent.putExtra("debt", debt * 1.05);
                } else if (result.equals("lose")) {
                    intent.putExtra("cash", cash - betAmount);
                    intent.putExtra("debt", debt * 1.05);
                } else {
                    intent.putExtra("cash", cash);
                    intent.putExtra("debt", debt * 1.05);
                }

                //starts the HomeScreen Activity
                win_lose.this.startActivity(intent);
            }
        });
    }

    public String prettifyCards(ArrayList<String> hand){
        String displayedPlayerHand = "";
        for(Object s: hand){
            s = s.toString();
            displayedPlayerHand = displayedPlayerHand + " " + s;
        }
        return displayedPlayerHand;
    }


}