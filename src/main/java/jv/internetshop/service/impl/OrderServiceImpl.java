package jv.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import jv.internetshop.dao.OrderDao;
import jv.internetshop.lib.Inject;
import jv.internetshop.lib.Service;
import jv.internetshop.model.Order;
import jv.internetshop.model.Product;
import jv.internetshop.model.User;
import jv.internetshop.service.OrderService;
import jv.internetshop.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        List<Product> productsToOrder = new ArrayList<>(products);
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        Order newOrder = new Order(productsToOrder, user.getId());
        return orderDao.create(newOrder);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order create(Order element) {
        return orderDao.create(element);
    }

    @Override
    public Order getByUserId(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order element) {
        return orderDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
