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
    private static final String ZERO_AMOUNT = "0.00";
    private static String display = INSERT_COIN;
    private static HashMap<Coin, Integer> balance = new HashMap<>();
    private static HashMap<Coin, Integer> amount = new HashMap<>();
    private static HashMap<Selection, Integer> supply = new HashMap<>();
    private static Double coinReturn = 0.0;

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

    public static String returnMoney()
    {
        amount.put(Coin.QUARTER, 0);
        amount.put(Coin.DIME, 0);
        amount.put(Coin.NICKEL, 0);
        display = INSERT_COIN;
        return ZERO_AMOUNT;
    }

    private static void makeChange(Selection purchase)
    {
        double amountOfChange = Math.round((getAmountValue() - purchase.value()) * 100.0) / 100.0;
        boolean needToMakeChange = amountOfChange >= 0 ? true : false;
        //This section determines if we actually need to make change
        int quarters = needToMakeChange? getAmountOfCoins(amountOfChange, Coin.QUARTER.value()) : 0;
        amountOfChange -= quarters * Coin.QUARTER.value();
        int dimes = needToMakeChange ? getAmountOfCoins(amountOfChange, Coin.DIME.value()) : 0;
        amountOfChange -= dimes * Coin.DIME.value();
        int nickles = needToMakeChange ? getAmountOfCoins(amountOfChange, Coin.NICKEL.value()) : 0;
        amountOfChange -= nickles * Coin.NICKEL.value();

        //This section actually processes the change and dispenses it to the coinReturn
        coinReturn += Coin.QUARTER.value() * quarters;
        coinReturn += Coin.DIME.value() * dimes;
        coinReturn += Coin.NICKEL.value() * nickles;
    }


    /**
     * This is a helper function that will convert the Doubles to int's so we can get the number of times we must loop.
     * This is because i am storing the values as decimals less than 0 to represent their actual dollar amount
     * @param costOfPurchase - this is the current amount remaining to make the purchase
     * @param coinValue - this is the value of the coin we are mod'ing by.
     * @return this integer will be the number of times we loop. In other words how many of the coins do we put in the
     * machine.
     */
    private static Integer getAmountOfCoins(Double costOfPurchase, Double coinValue)
    {
        return  (
                ((Double)(costOfPurchase * 100)).intValue() / ((Double)(coinValue * 100)).intValue()
        );

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
            makeChange(purchase);
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

    public static String coinReturn()
    {
        return ""+coinReturn;
    }
}
