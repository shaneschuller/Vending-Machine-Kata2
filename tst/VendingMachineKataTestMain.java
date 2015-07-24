import com.github.shaneschuller.VendingMachine;
import com.github.shaneschuller.Coin;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Shane on 7/23/2015.
 */

public class VendingMachineKataTestMain
{
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
}
