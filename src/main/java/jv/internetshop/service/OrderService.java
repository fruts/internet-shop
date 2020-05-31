package jv.internetshop.service;

import java.util.List;
import jv.internetshop.model.Order;
import jv.internetshop.model.Product;
import jv.internetshop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(Long userId);
}
