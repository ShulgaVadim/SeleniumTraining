package testNg;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.RealItem;


public class RealItemTest {

    @Test(groups = {"goodTests", "itemTests"})
    void realItemTest() {
        RealItem rItem = new RealItem();
        rItem.setName("Scoda");
        rItem.setPrice(17500.55);
        rItem.setWeight(1400.25);
        Assert.assertEquals(rItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Weight: %s",
                rItem.getClass(), rItem.getName(), rItem.getPrice(), rItem.getWeight()));
    }
}
