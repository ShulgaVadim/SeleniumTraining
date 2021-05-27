package jUnit;

import jUnit.Utils.Utils;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTest {

    @Test
    void virtualItemTest() {
        VirtualItem vItem = new VirtualItem();
        Utils.initVirtualItem("Fifa", 50.40, 8096.48);
        assertEquals(vItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Size on disk: %s",
                vItem.getClass(), vItem.getName(), vItem.getPrice(), vItem.getSizeOnDisk()));
    }
}
