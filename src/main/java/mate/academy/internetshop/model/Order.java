package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private List<Product> products;
    private Long id;
    private Long userId;

    public Order(List<Product> products, Long userId) {
        this.products = products;
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{"
                + "products=" + products
                + ", id=" + id
                + ", user=" + userId + '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
