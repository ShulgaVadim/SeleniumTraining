package testNg;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;


public class CartTest {

    @Parameters({"price"})
    @Test(groups = {"goodTests"}, testName = "Check getTotalPrice method")
    void getTotalPriceTest(@Optional("10000") double price) {
        Cart cart = new Cart("newCart");
        RealItem realItem =new RealItem();
        realItem.setPrice(price);

        double expectedTotal = cart.getTotalPrice() + realItem.getPrice() * 1.2;
        cart.addRealItem(realItem);
        Assert.assertEquals(expectedTotal, cart.getTotalPrice());
    }
}
