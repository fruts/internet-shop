package mate.academy.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long itemId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;

    public static void addToList(Product product) {
        product.setId(++itemId);
        products.add(product);
    }

    public static void addToList(Bucket bucket) {
        bucket.setId(++bucketId);
        buckets.add(bucket);
    }

    public static void addToList(Order order) {
        order.setOrderId(++orderId);
        orders.add(order);
    }

    public static void addToList(User user) {
        user.setId(++userId);
        users.add(user);
    }
}
