package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Item;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    List<Item> getAll();

    Item update(Item old, Item newest);

    boolean delete(Long id);
}
