package internetshop.dao;

import mate.academy.internetshop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    Item update(Item old, Item newest);

    boolean delete(Long id);
}
