package mate.academy.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long productId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;

    public static void addToList(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addToList(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }

    public static void addToList(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addToList(User user) {
        user.setId(++userId);
        users.add(user);
    }
}
