package jUnit;

import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    void getTotalPriceTest() {
        Cart cart = new Cart("newCart");
        RealItem realItem =new RealItem();
        realItem.setPrice(20000);

        double expectedTotal = cart.getTotalPrice() + realItem.getPrice() * 1.2;
        cart.addRealItem(realItem);
        assertEquals(expectedTotal, cart.getTotalPrice());
    }
}
