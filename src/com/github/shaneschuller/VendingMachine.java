package com.github.shaneschuller;
import com.github.shaneschuller.Coin;

import java.util.HashMap;

/**
 * Created by Shane on 7/24/2015.
 */
public class VendingMachine {
    private static final String INSERT_COIN = "INSERT COIN";
    private static final String NUMBER_FORMAT_SAFE_DEFAULT_STRING = "0";
    private static String display = INSERT_COIN;
    private static HashMap<Coin, Integer> balance;

    public static String display()
    {
        return display;
    }

    public static void insertCoin(Coin coin)
    {
        try
        {
            if(coin == Coin.DIME || coin == Coin.NICKEL || coin == Coin.QUARTER)
            {
                if(display.equals(INSERT_COIN))
                {
                    display = NUMBER_FORMAT_SAFE_DEFAULT_STRING;
                }
                display = "" + (Double.parseDouble(display) + coin.value());
            }
        } catch (NumberFormatException nfe)
        {
            System.out.println("Display is not a number");
        }
    }

    public static void returnMoney()
    {
        display = INSERT_COIN;
    }

    public static String getBalance()
    {
        double balanceInNumeric = 0.0;
        balanceInNumeric += balance.get(Coin.QUARTER) * Coin.QUARTER.value();
        balanceInNumeric += balance.get(Coin.DIME) * Coin.DIME.value();
        balanceInNumeric += balance.get(Coin.NICKEL) * Coin.NICKEL.value();
        return ""+balanceInNumeric;
    }

    public static void setBalance(HashMap<Coin, Integer> bal)
    {
        balance = bal;
    }
}
