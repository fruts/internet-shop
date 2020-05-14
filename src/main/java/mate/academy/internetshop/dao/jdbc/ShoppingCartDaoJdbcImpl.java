package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO carts (user_id) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            cart.setId(resultSet.getLong(1));
            insertCartsProducts(cart);
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create cart", e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM carts WHERE cart_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCart(resultSet);
                shoppingCart.setProducts(getProductsFromShoppingCartId(id));
                return Optional.of(shoppingCart);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get cart", e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM carts;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCart(resultSet);
                shoppingCart.setProducts(getProductsFromShoppingCartId(shoppingCart.getId()));
                shoppingCarts.add(shoppingCart);
            }
            return shoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get all carts", e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE carts SET user_id = ? "
                    + "WHERE cart_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cart.getId());
            statement.executeUpdate();
            deleteShoppingCartFromCartsProducts(cart.getId());
            insertCartsProducts(cart);
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM carts WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteShoppingCartFromCartsProducts(id);
            var statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete cart", e);
        }
    }

    private void insertCartsProducts(ShoppingCart cart) throws SQLException {
        String query = "INSERT INTO carts_products (cart_id, product_id) "
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : cart.getProducts()) {
                var insertStatement = connection.prepareStatement(query);
                insertStatement.setLong(1, cart.getId());
                insertStatement.setLong(2, product.getId());
                insertStatement.executeUpdate();
            }
        }
    }

    private ShoppingCart getShoppingCart(ResultSet resultSet)
            throws SQLException {
        return new ShoppingCart(resultSet.getLong("cart_id"),
                resultSet.getLong("user_id"));
    }

    private List<Product> getProductsFromShoppingCartId(Long cartId)
            throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT products.* FROM carts_products "
                    + "JOIN products USING (product_id) WHERE cart_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"));
                products.add(product);
            }
            return products;
        }
    }

    private void deleteShoppingCartFromCartsProducts(Long shoppingCartId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM carts_products WHERE cart_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            statement.executeUpdate();
        }
    }
}
