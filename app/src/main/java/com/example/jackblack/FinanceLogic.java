package com.example.jackblack;

public class FinanceLogic {
    private double debt;
    private double money;

    public FinanceLogic() {
        this.debt = 0;
        this.money = 0;
    }

    public FinanceLogic(double debt, double money) {
        this.debt = debt;
        this.money = money;
    }

    public void addMoney(double amount){
        this.money += amount;
    }

    public void removeMoney(double amount){
        this.money -= amount;
    }

    public void takeLoan(double amount){
        //1.1 is because of 10% interest rate
        debt += amount * 1.1;
        money += amount;
    }

    public void repayLoan(double amount){
        if(amount <= money){
            money -= amount;
            debt -= amount;
        }
        else{
            //Placeholder for error message
            System.out.println("You're broke kid");
        }
    }
    // Getters and Setters here
    public double getDebt(){
        return debt;
    }

    public double getMoney() {
        return money;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
