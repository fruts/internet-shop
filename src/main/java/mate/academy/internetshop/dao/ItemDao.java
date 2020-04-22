package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    Item update(Item old, Item newest);

    boolean delete(Long id);
}
