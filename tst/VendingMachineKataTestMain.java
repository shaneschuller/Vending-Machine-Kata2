import com.github.shaneschuller.VendingMachine;
import com.github.shaneschuller.Coin;
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
    }

    @After
    public void setTestingBalanceToZero()
    {
        balance = 0.0;
    }

    @Test
    public void acceptsQuarters()
    {
        VendingMachine.insertCoin(Coin.QUARTER);
        Assert.assertEquals("0.25", VendingMachine.display());
        VendingMachine.returnMoney();
    }

    @Test
    public void acceptsNickles()
    {
        VendingMachine.insertCoin(Coin.NICKEL);
        Assert.assertEquals("0.05", VendingMachine.display());
        VendingMachine.returnMoney();
    }

    @Test
    public void acceptsDimes()
    {
        VendingMachine.insertCoin(Coin.DIME);
        Assert.assertEquals("0.1", VendingMachine.display());
        VendingMachine.returnMoney();
    }

    @Test
    public void doesNotAcceptPennies()
    {
        VendingMachine.insertCoin(Coin.PENNY);
        Assert.assertEquals("INSERT COIN", VendingMachine.display());
        Assert.assertEquals(""+balance, VendingMachine.getBalance());
    }
}
