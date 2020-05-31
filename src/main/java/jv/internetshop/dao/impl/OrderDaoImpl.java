package jv.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import jv.internetshop.dao.OrderDao;
import jv.internetshop.dao.Storage;
import jv.internetshop.model.Order;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addToList(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(ind -> Storage.orders.get(ind).getId().equals(order.getId()))
                .forEach(ind -> Storage.orders.set(ind, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }
}
