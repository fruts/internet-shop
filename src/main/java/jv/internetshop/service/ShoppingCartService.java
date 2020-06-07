package jv.internetshop.service;

import java.util.List;
import jv.internetshop.model.Product;
import jv.internetshop.model.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
