package jUnit.Utils;

import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static jUnit.Utils.Constants.FILE_NAME_LIST;

public class Utils {

    public static Cart initCart(int i){
        Cart cart = new Cart(FILE_NAME_LIST.get(i));
        RealItem rItem = initRealItem("Scoda", 17500.50, 1400.25);
        VirtualItem vItem = initVirtualItem("Fifa", 50, 8096);
        cart.addRealItem(rItem);
        cart.addVirtualItem(vItem);
        return cart;
    }

    public static RealItem initRealItem(String name, double price, double weight) {
        RealItem rItem = new RealItem();
        rItem.setName(name);
        rItem.setPrice(price);
        rItem.setWeight(weight);
        return rItem;
    }

    public static VirtualItem initVirtualItem(String name, double price, double size) {
        VirtualItem vItem = new VirtualItem();
        vItem.setName(name);
        vItem.setPrice(price);
        vItem.setSizeOnDisk(size);
        return vItem;
    }
}
