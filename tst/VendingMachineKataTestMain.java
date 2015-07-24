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
        HashMap<Coin, Integer> initBalance = new HashMap<Coin,Integer>();
        initBalance.put(Coin.QUARTER, 20);
        balance += Coin.QUARTER.value() * 20;
        initBalance.put(Coin.DIME, 20);
        balance += Coin.DIME.value() * 20;
        initBalance.put(Coin.NICKEL, 60);
        balance += Coin.NICKEL.value() * 60;
        VendingMachine.setBalance(initBalance);

        completeSupply = new HashMap<Selection,Integer>();
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
        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.QUARTER);
        Assert.assertEquals("1.0", VendingMachine.display());
        Assert.assertEquals("THANK YOU", VendingMachine.purchase(Selection.COLA));
    }

    @Test
    public void canPurchaseChips()
    {
        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.QUARTER);
        Assert.assertEquals("0.5", VendingMachine.display());
        Assert.assertEquals("THANK YOU", VendingMachine.purchase(Selection.CHIPS));
    }

    @Test
    public void canPurchaseCandy()
    {

        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.QUARTER);
        VendingMachine.insertCoin(Coin.DIME);
        VendingMachine.insertCoin(Coin.NICKEL);
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

    /* CONSTRUCTOR */
    public VendingMachineKataTestMain()
    {
        super();
    }

}
