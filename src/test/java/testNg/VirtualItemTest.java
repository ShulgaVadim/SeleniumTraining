package testNg;

import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTest {

    @Test(groups = {"goodTests"})
    void virtualItemTest() {
        VirtualItem vItem = new VirtualItem();
        vItem.setName("Fifa");
        vItem.setPrice(50.40);
        vItem.setSizeOnDisk(8096.48);
        assertEquals(vItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Size on disk: %s",
                vItem.getClass(), vItem.getName(), vItem.getPrice(), vItem.getSizeOnDisk()));
    }
}
