package com.company1.gpasaver;

import org.junit.Test;

import static org.junit.Assert.*;

public class availableAccountTest {
    availableAccount accountCheck = new availableAccount();

    @Test
    public void accountUnavailable(){
        assertEquals(false, accountCheck.accountCheck());
    }

    @Test
    public void accountAvailable(){
        accountCheck.balance = 10.0;
        assertEquals(true, accountCheck.accountCheck());
    }

    @Test
    public void balanceNotEnough(){
        float cost = 20;
        accountCheck.balance = 10.0;
        assertEquals(false, accountCheck.balanceCheck(cost));
    }

    @Test
    public void balanceEnough(){
        float cost = 10;
        accountCheck.balance = 10.0;
        assertEquals(true, accountCheck.balanceCheck(cost));
    }
}
