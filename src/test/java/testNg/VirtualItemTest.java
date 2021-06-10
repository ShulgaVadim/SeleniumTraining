package testNg;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.VirtualItem;

public class VirtualItemTest {

    @Test(groups = {"goodTests", "itemTests"})
    void virtualItemTest() {
        VirtualItem vItem = new VirtualItem();
        vItem.setName("Fifa");
        vItem.setPrice(50.40);
        vItem.setSizeOnDisk(8096.48);
        Assert.assertEquals(vItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Size on disk: %s",
                vItem.getClass(), vItem.getName(), vItem.getPrice(), vItem.getSizeOnDisk()));
    }
}
