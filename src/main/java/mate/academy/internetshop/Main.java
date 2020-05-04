package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Main {

    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {

        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product chair = new Product("chair", 20);
        Product table = new Product("table", 40);

        productService.create(chair);
        productService.create(table);

        System.out.println(productService.getByUserId(chair.getId()));
        System.out.println(productService.getAll());

        Product badChair = new Product("need to remove it", 20);
        System.out.println(productService.getAll());
        productService.delete(badChair.getId());
        System.out.println(productService.getAll());

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User serhii = new User("Serhii", "1234", "0000");
        userService.create(serhii);
        User badGuy = new User("Bad", "Guy", "666");
        System.out.println(userService.getByUserId(serhii.getId()));
        System.out.println(userService.getAll());
        userService.delete(badGuy.getId());
        System.out.println(userService.getAll());

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart serhiiCart = shoppingCartService.getByUserId(serhii.getId());
        shoppingCartService.addProduct(serhiiCart, chair);
        shoppingCartService.addProduct(serhiiCart, table);
        System.out.println(shoppingCartService.getAllProducts(serhiiCart));

        shoppingCartService.deleteProduct(serhiiCart, chair);
        System.out.println(shoppingCartService.getAllProducts(serhiiCart));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order serhiiOrder = orderService
                .completeOrder(shoppingCartService.getAllProducts(serhiiCart), serhii);
        System.out.println(shoppingCartService.getByUserId(serhii.getId()));
    }
}
