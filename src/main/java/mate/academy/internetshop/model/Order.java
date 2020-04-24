package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private List<Product> products;
    private Long id;
    private User user;

    public Order(List<Product> products, User user) {
        this.products = products;
        this.user = user;
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
                + ", user=" + user + '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
