package jv.internetshop.dao;

import java.util.List;
import jv.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    boolean deleteProductFromOrder(Long id);

    List<Order> getUserOrders(Long userId);
}
