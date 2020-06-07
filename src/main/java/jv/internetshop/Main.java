package jv.internetshop;

import java.math.BigDecimal;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Order;
import jv.internetshop.model.Product;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.model.User;
import jv.internetshop.service.OrderService;
import jv.internetshop.service.ProductService;
import jv.internetshop.service.ShoppingCartService;
import jv.internetshop.service.UserService;

public class Main {

    private static Injector injector = Injector.getInstance("jv.internetshop");

    public static void main(String[] args) {

        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product chair = new Product("chair", new BigDecimal(20));
        Product table = new Product("table", new BigDecimal(40));

        productService.create(chair);
        productService.create(table);

        System.out.println(productService.getByUserId(chair.getId()));
        System.out.println(productService.getAll());

        Product badChair = new Product("need to remove it", new BigDecimal(20));
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
