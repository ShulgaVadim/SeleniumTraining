package jUnit;

import jUnit.Utils.Utils;
import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
    public Cart cart;
    RealItem realItem;

    @Test
    void getTotalPriceTest() {
        cart = Utils.initCart(0);
        realItem = Utils.initRealItem("Chevrolet", 20000, 1800);
        double expectedTotal = cart.getTotalPrice() + realItem.getPrice() * 1.2;
        cart.addRealItem(realItem);
        assertEquals(expectedTotal, cart.getTotalPrice());
    }
}
