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
        Assert.assertEquals("0.25",VendingMachine.display());
    }
}
