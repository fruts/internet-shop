package jv.internetshop.dao;

import java.util.Optional;
import jv.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUserId(Long userId);
}
