import com.github.shaneschuller.VendingMachine;
import com.github.shaneschuller.Coin;
import com.github.shaneschuller.Selection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Created by Shane on 7/23/2015.
 */

public class VendingMachineKataTestMain
{
    private double balance;
    private HashMap<Selection,Integer> completeSupply;
    @Before
    public void setBalanceOfVendingMachine()
    {
        HashMap<Coin, Integer> initBalance = new HashMap<>();
        initBalance.put(Coin.QUARTER, 20);
        balance += Coin.QUARTER.value() * 20;
        initBalance.put(Coin.DIME, 20);
        balance += Coin.DIME.value() * 20;
        initBalance.put(Coin.NICKEL, 60);
        balance += Coin.NICKEL.value() * 60;
        VendingMachine.setBalance(initBalance);

        completeSupply = new HashMap<>();
        completeSupply.put(Selection.CANDY, 10);
        completeSupply.put(Selection.CHIPS, 10);
        completeSupply.put(Selection.COLA, 10);
        VendingMachine.updateSupply(completeSupply);
    }

    @After
    public void setTestingBalanceToZero()
    {
        balance = 0.0;
        completeSupply = null;
        VendingMachine.returnMoney();
    }

    @Test
    public void acceptsQuarters()
    {
        VendingMachine.insertCoin(Coin.QUARTER);
        Assert.assertEquals("0.25", VendingMachine.display());
    }

    @Test
    public void acceptsNickles()
    {
        VendingMachine.insertCoin(Coin.NICKEL);
        Assert.assertEquals("0.05", VendingMachine.display());
    }

    @Test
    public void acceptsDimes()
    {
        VendingMachine.insertCoin(Coin.DIME);
        Assert.assertEquals("0.1", VendingMachine.display());
    }

    @Test
    public void doesNotAcceptPennies()
    {
        VendingMachine.insertCoin(Coin.PENNY);
        Assert.assertEquals("INSERT COIN", VendingMachine.display());
        Assert.assertEquals("" + balance, VendingMachine.getBalance());
    }

    @Test
    public void canPurchaseCola()
    {
        enterEnoughMoney(Selection.COLA.value());
        Assert.assertEquals("1.0", VendingMachine.display());
        Assert.assertEquals("THANK YOU", VendingMachine.purchase(Selection.COLA));
    }

    @Test
    public void canPurchaseChips()
    {
        enterEnoughMoney(Selection.CHIPS.value());
        Assert.assertEquals("0.5", VendingMachine.display());
        Assert.assertEquals("THANK YOU", VendingMachine.purchase(Selection.CHIPS));
    }

    @Test
    public void canPurchaseCandy()
    {
        enterEnoughMoney(Selection.CANDY.value());
        Assert.assertEquals("0.65", VendingMachine.display());
        Assert.assertEquals("THANK YOU", VendingMachine.purchase(Selection.CANDY));

    }



    @Test
    public void alertOfSoldOutCandy()
    {
        completeSupply.put(Selection.CANDY, 0);
        VendingMachine.updateSupply(completeSupply);

        Assert.assertEquals("SOLD OUT", VendingMachine.purchase(Selection.CANDY));
    }

    @Test
    public void alertOfSoldOutChips()
    {
        completeSupply.put(Selection.CHIPS, 0);
        VendingMachine.updateSupply(completeSupply);

        Assert.assertEquals("SOLD OUT", VendingMachine.purchase(Selection.CHIPS));
    }

    @Test
    public void alertOfSoldOutCola()
    {
        completeSupply.put(Selection.COLA, 0);
        VendingMachine.updateSupply(completeSupply);

        Assert.assertEquals("SOLD OUT", VendingMachine.purchase(Selection.COLA));
    }

    @Test
    public void correctChangeIsMade()
    {
        enterEnoughMoney(0.75);
        VendingMachine.purchase(Selection.CANDY);
        Assert.assertEquals("0.1", VendingMachine.coinReturn());
    }

    @Test
    public void returnMoneyShowsZeroOnDisplay()
    {
        VendingMachine.insertCoin(Coin.DIME);
        Assert.assertEquals("0.00", VendingMachine.returnMoney());
    }

    @Test
    public void exactChangeIsDisplayed()
    {
        HashMap<Coin, Integer> initBalance = new HashMap<>();
        initBalance.put(Coin.QUARTER, 0);
        initBalance.put(Coin.DIME, 0);
        initBalance.put(Coin.NICKEL, 0);
        VendingMachine.setBalance(initBalance);
        Assert.assertEquals("EXACT CHANGE ONLY", VendingMachine.display());

    }

    /* CONSTRUCTOR */
    public VendingMachineKataTestMain()
    {
        super();
    }

      /* Private Helper Functions */

    /**
     * This function will build the test up be entering the correct amount of money needed to make the purchase.
     * @param purchaseValue - this is the Selection Enum value we will compare against and get the value of.
     */
    private void enterEnoughMoney(Double purchaseValue)
    {
        double costOfPurchase = purchaseValue;
        if(costOfPurchase > 0)
        {
            putCoinsInTheMachine(convertPriceToInteger(costOfPurchase, Coin.QUARTER.value()), Coin.QUARTER);
            costOfPurchase -= convertPriceToInteger(costOfPurchase, Coin.QUARTER.value()) * Coin.QUARTER.value();
        }
        if(costOfPurchase > 0)
        {
            putCoinsInTheMachine(convertPriceToInteger(costOfPurchase, Coin.DIME.value()), Coin.DIME);
            costOfPurchase -= convertPriceToInteger(costOfPurchase, Coin.DIME.value()) * Coin.DIME.value();
        }
        if(costOfPurchase > 0)
        {
            putCoinsInTheMachine(convertPriceToInteger(costOfPurchase, Coin.NICKEL.value()), Coin.NICKEL);
            //This next line only exists so that we could add more coins later if we so chose, I.E Pennies, or Pesos, or etc
            costOfPurchase -= convertPriceToInteger(costOfPurchase, Coin.NICKEL.value()) * Coin.NICKEL.value();
        }
    }

    /**
     * This is a helper function that will convert the Doubles to int's so we can get the number of times we must loop.
     * This is because i am storing the values as decimals less than 0 to represent their actual dollar amount
     * @param costOfPurchase - this is the current amount remaining to make the purchase
     * @param coinValue - this is the value of the coin we are mod'ing by.
     * @return this integer will be the number of times we loop. In other words how many of the coins do we put in the
     * machine.
     */
    private Integer convertPriceToInteger(Double costOfPurchase, Double coinValue)
    {
        return  (
                ((Double)(costOfPurchase * 100)).intValue() / ((Double)(coinValue * 100)).intValue()
        );

    }

    /**
     * This function actually does the loop of putting the coins in the machine.
     * @param v - number of times we are going to loop
     * @param coin - type of coin we are inserting.
     */
    private void putCoinsInTheMachine(Integer v, Coin coin)
    {
        for(int i = v; i > 0; i--)
        {
            VendingMachine.insertCoin(coin);
        }
    }

}
