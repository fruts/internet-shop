package jv.internetshop.model;

import java.util.List;

public class Order {
    private List<Product> products;
    private Long id;
    private Long userId;

    public Order(List<Product> products, Long userId) {
        this.products = products;
        this.userId = userId;
    }

    public Order(Long id, List<Product> products, Long userId) {
        this(products, userId);
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{"
                + "products=" + products
                + ", id=" + id
                + ", user=" + userId + '}';
    }
}
