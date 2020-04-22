package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        Item table = new Item("Table", 25.5);
        Item chair = new Item("Chair", 15);
        Item laptop = new Item("Laptop", 400);

        itemService.create(table);
        itemService.create(chair);
        itemService.create(laptop);
        System.out.println(itemService.get(table.getId()));
        System.out.println(itemService.getAll());
        System.out.println(itemService.delete(table.getId()));
        System.out.println(itemService.getAll());
        Item newChair = new Item("SuperChair2000", 200);
        itemService.update(chair, newChair);
        System.out.println(itemService.getAll());
    }

}
