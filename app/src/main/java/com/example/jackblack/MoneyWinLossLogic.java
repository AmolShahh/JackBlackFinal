package com.example.jackblack;

public class MoneyWinLossLogic{

    public FinanceLogic fl = new FinanceLogic(0, 0);

    public void winMoney(boolean winLose, double betAmount){
        // If they win, give them 1.5 of how much they bet
        if (winLose){
            fl.addMoney(betAmount*1.5);
        }
        else{
            fl.removeMoney(betAmount);
        }
    }
}
