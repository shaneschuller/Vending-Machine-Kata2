package com.github.shaneschuller;
import com.github.shaneschuller.Coin;
/**
 * Created by Shane on 7/24/2015.
 */
public class VendingMachine {
    private static String display = "0";

    public static String display()
    {
        return display;
    }

    public static void insertCoin(Coin coin)
    {
        try
        {
            display = "" + (Double.parseDouble(display) + coin.value());
        } catch (NumberFormatException nfe)
        {
            System.out.println("Display is not a number");
        }
    }

    public static void returnMoney()
    {
        display = "0";
    }
}
