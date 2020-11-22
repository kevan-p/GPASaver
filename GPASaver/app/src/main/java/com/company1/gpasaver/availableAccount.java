package com.company1.gpasaver;

public class availableAccount {

    double balance = 0.0;

    public boolean accountCheck(){
        if(this.balance <= 0.0){
            return false;
        }
        return true;
    }

    public boolean balanceCheck(float cost){
        if((this.balance < cost)) { //balance not enough
            return  false;
        }
        return true;
    }

    public void getUserBalance(){
        double bal = MainActivity.Mysig.mainUser.getBalance();

        this.balance = bal;
    }
}
