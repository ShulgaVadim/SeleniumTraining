package jUnit;

import org.junit.jupiter.api.Test;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {

    @Test
    void realItemTest() {
        RealItem rItem = new RealItem();
        rItem.setName("Scoda");
        rItem.setPrice(17500.55);
        rItem.setWeight(1400.25);
        assertEquals(rItem.toString(), String.format("Class: %s; Name: %s; Price: %s; Weight: %s",
                rItem.getClass(), rItem.getName(), rItem.getPrice(), rItem.getWeight()));
    }
}
