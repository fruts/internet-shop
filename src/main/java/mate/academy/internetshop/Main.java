package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product table = new Product("Table", new BigDecimal(25.5));
        Product chair = new Product("Chair", new BigDecimal(15.5));
        Product laptop = new Product("Laptop", new BigDecimal(400));
        productService.create(table);
        productService.create(chair);
        productService.create(laptop);
        System.out.println(productService.get(table.getId()));
        System.out.println(productService.getAll());
        System.out.println(productService.delete(table.getId()));
        System.out.println(productService.getAll());
        chair.setPrice(new BigDecimal(50));
        System.out.println(chair);
        productService.update(chair);
        System.out.println(productService.getAll());
    }

}
