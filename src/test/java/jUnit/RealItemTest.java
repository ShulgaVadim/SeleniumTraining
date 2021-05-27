package jUnit;

import jUnit.Utils.Utils;
import org.junit.jupiter.api.Test;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {

    @Test
    void realItemTest() {
        RealItem rItem = new RealItem();
        Utils.initRealItem("Scoda", 17500.55, 1400.25);
        assertEquals(rItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Weight: %s",
                rItem.getClass(), rItem.getName(), rItem.getPrice(), rItem.getWeight()));
    }
}
