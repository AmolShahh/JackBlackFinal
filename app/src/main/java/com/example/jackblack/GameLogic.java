package com.example.jackblack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic extends AppCompatActivity {

    //App view stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_play);

        //Deal cards upon coming to this actvity
        dealCards();

        //Set player hand text view to player hand
        TextView playerHandTextView = (TextView) findViewById(R.id.playerHandTextView);
        playerHandTextView.setText(playerHand.toString());

        //Set dealer hand text view to dealer hand
        TextView dealerHandTextView = (TextView) findViewById(R.id.dealerHandTextView);
        dealerHandTextView.setText(dealerHand.toString());

        //Testing button goes to home screen intent
        final Button backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define a new intent to take us to Home Page (HomeScreen)
                Intent intent = new Intent(GameLogic.this, MainActivity.class);

                //starts the HomeScreen Activity
                GameLogic.this.startActivity(intent);
            }
        });

        final Button hitButton = (Button) findViewById(R.id.hit);
        hitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform hit function on click
                hit();
                playerHandTextView.setText(playerHand.toString());
            }
        });

        final Button standButton = (Button) findViewById(R.id.stand);
        standButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform hit function on click
                stand();
                playerHandTextView.setText(playerHand.toString());
                dealerHandTextView.setText(dealerHand.toString());
            }
        });

    }


    //Logic
    private ArrayList deck = new ArrayList<String>();
    private ArrayList playerHand = new ArrayList<String>();
    private ArrayList dealerHand = new ArrayList<String>();
    private int playerTotal;
    private int dealerTotal;
    private double bet;

    private Random rng = new Random();

    // Creates the deck
    public void createDeck() {
        String cards[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String suits[] = {"♦", "♣", "♥", "♠"};

        // Clears deck every time we load into this page
        deck.clear();

        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                deck.add(cards[i] + suits[j]);
            }
        }
    }

    // Assigns a value to each card (using arrays) + calculates value of player hand
    public void calculatePlayerHand() {
        boolean containsAce = false;
        int total = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            String card = (String) playerHand.get(i);
            card = card.substring(0, card.length() - 1);
            if (card.equals("A")) {
                containsAce = true;
                total = total + 11;
            }
            else if (card.equals("K") || card.equals("J") || card.equals("Q")) {
                total = total + 10;
            }
            else {
                total = total + Integer.valueOf(card);
            }
        }
        if (total > 21) {
            if (containsAce == true) {
                total -= 10;
                if (total > 21) {
                    lose();
                }
            } else {
                lose();
            }
        }

        if (total == 21) {
            win();
        }

        playerTotal = total;
    }

    // Assigns a value to each card (using arrays) + calculates value of dealer hand
    public void calculateDealerHand() {
        boolean containsAce = false;
        int total = 0;
        for (int i = 0; i < dealerHand.size(); i++) {
            String card = (String) dealerHand.get(i);
            card = card.substring(0, card.length() - 1);
            if (card.equals("A")) {
                containsAce = true;
                total = total + 11;
            }
            else if (card.equals("K") || card.equals("J") || card.equals("Q")) {
                total = total + 10;
            }
            else {
                total = total + Integer.valueOf(card);
            }
        }
        if (total > 21) {
            if (containsAce == true) {
                total -= 10;
                if (total > 21) {
                    lose();
                }
            } else {
                lose();
            }
        }

        if (total == 21) {
            win();
        }

        dealerTotal = total;
    }

    // Calculates card values for player and dealer
    public void calculateCardValues() {
        calculateDealerHand();
        calculatePlayerHand();
    }


        // Deals the cards to both players
    public void dealCards() {
        createDeck();
        // Do it twice
        for (int i = 0; i < 2; i++) {
            // Deals 1 card to player 1 + removes that card from deck
            int p1CardIndex = rng.nextInt(deck.size() - 1);
            playerHand.add(deck.get(p1CardIndex));
            deck.remove(p1CardIndex);

            // Deals 1 card to dealer + removes that card from deck
            int dealerCardIndex = rng.nextInt(deck.size() - 1);
            dealerHand.add(deck.get(dealerCardIndex));
            deck.remove(dealerCardIndex);
        }
        calculateCardValues();
    }


    // Starts game by creating deck and dealing out cards
    //Calculates value of each card in hand
    //Above 3 functions sink into here
    public void startGame() {

    }

    //Gets amount player bets
    public void getBet() {

    }

    //Logic for hitting (getting one more card)
    public void hit() {
        int newCardIndex = rng.nextInt(deck.size() - 1);
        playerHand.add(deck.get(newCardIndex));
        deck.remove(newCardIndex);

        calculateCardValues();

        Log.i("phand", playerHand.toString());

    }

    //How the dealer hits or stands
    //After player stands, the dealer will hit until 17 or higher
    //Dealer stands after 17 or busts
    public void dealerLogic() {
        while(dealerTotal < 17){
            int newCardIndex = rng.nextInt(deck.size() - 1);
            dealerHand.add(deck.get(newCardIndex));
            deck.remove(newCardIndex);

            calculateCardValues();
        }
    }

    //Logic for standing (not drawing any card)
    public void stand() {
       dealerLogic();
       if(playerTotal > dealerTotal && playerTotal <= 21){
           win();
       }
       else if(playerTotal < dealerTotal && dealerTotal <= 21){
           lose();
       }
       else if(playerTotal == dealerTotal){
           push();
       }
       else{
           lose();
       }
        Log.i("phand", playerHand.toString());
    }

    // Logic for pushing (when you tie in hand value)
    public double push() {
        Log.i("push", "YOU TIED");
        changeActivity("tie");

        // Return bet to player
        return bet;
    }

    //Logic for winning
    public double win() {
        Log.i("win", "YOU WIN");
        changeActivity("win");

        // Return 1.5 times bet to player
        return bet*1.5;
    }

    //Logic for losing
    public double lose() {
        Log.i("lose", "YOU LOSE");
        changeActivity("lose");
        // Return 0 dollars to player
        return 0;
    }

    //Going back to HomePage
    public void goHome() {

    }

    public String displayHands(ArrayList hand){
        String output = "";
        for(int i = 0; i <= hand.size(); i++){
            output += hand.get(i);
            output += " ";
        }
        return output;
    }

    //Put MoneyWinLossLogic stuff here

    public void changeActivity(String result){
        Intent intent = new Intent(GameLogic.this, win_lose.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }}
