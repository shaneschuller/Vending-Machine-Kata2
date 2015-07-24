package com.github.shaneschuller;

import java.util.HashMap;

/**
 * Created by Shane on 7/24/2015.
 */
public class VendingMachine {
    private static final String INSERT_COIN = "INSERT COIN";
    private static final String NUMBER_FORMAT_SAFE_DEFAULT_STRING = "0";
    private static final String SUCCESSFUL_PURCHASE = "THANK YOU";
    private static final String UNSUCCESSFUL_PURCHASE = "PRICE";
    private static final String SOLD_OUT = "SOLD OUT" ;
    private static String display = INSERT_COIN;
    private static HashMap<Coin, Integer> balance = new HashMap<Coin, Integer>();
    private static HashMap<Coin, Integer> amount = new HashMap<Coin, Integer>();
    private static HashMap<Selection, Integer> supply = new HashMap<>();

    static
    {
        amount.put(Coin.NICKEL, 0);
        amount.put(Coin.QUARTER, 0);
        amount.put(Coin.DIME, 0);
        balance.put(Coin.NICKEL, 0);
        balance.put(Coin.QUARTER, 0);
        balance.put(Coin.DIME, 0);
        supply.put(Selection.COLA, 0);
        supply.put(Selection.CANDY, 0);
        supply.put(Selection.CHIPS, 0);
    }
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
                amount.put(coin, (amount.get(coin) + 1));
                display = "" + (Double.parseDouble(display) + coin.value());
            }
        } catch (NumberFormatException nfe)
        {
            System.out.println("Display is not a number");
        }
    }

    public static void returnMoney()
    {
        amount.put(Coin.QUARTER, 0);
        amount.put(Coin.DIME, 0);
        amount.put(Coin.NICKEL, 0);
        display = INSERT_COIN;
    }

    public static String getBalance()
    {
        return ""   +(   (balance.get(Coin.QUARTER) * Coin.QUARTER.value())
                    +   (balance.get(Coin.DIME) * Coin.DIME.value())
                    +   (balance.get(Coin.NICKEL) * Coin.NICKEL.value())
                     );
    }

    public static void setBalance(HashMap<Coin, Integer> bal)
    {
        balance = bal;
    }

    public static String purchase(Selection purchase)
    {
        if(getAmountValue() >= purchase.value() && supply.get(purchase) != 0)
        {
            addAmountToBalance();
            return SUCCESSFUL_PURCHASE;
        }
        else
        {
            if(supply.get(purchase) == 0)
                return SOLD_OUT;
            return UNSUCCESSFUL_PURCHASE;
        }
    }

    private static void addAmountToBalance()
    {
        balance.put(Coin.QUARTER, (balance.get(Coin.QUARTER) + amount.get(Coin.QUARTER)));
        amount.put(Coin.QUARTER, 0);
        balance.put(Coin.DIME, (balance.get(Coin.DIME) + amount.get(Coin.DIME)));
        amount.put(Coin.DIME, 0);
        balance.put(Coin.NICKEL, (balance.get(Coin.NICKEL) + amount.get(Coin.NICKEL)));
        amount.put(Coin.NICKEL, 0);
    }

    private static double getAmountValue()
    {
        return  (amount.get(Coin.QUARTER) * Coin.QUARTER.value())
                +   (amount.get(Coin.DIME) * Coin.DIME.value())
                +   (amount.get(Coin.NICKEL) * Coin.NICKEL.value());

    }

    public static void updateSupply(HashMap<Selection, Integer> updateSupply)
    {
        if(updateSupply != null)
        {
            supply.putAll(updateSupply);
        }
    }
}
