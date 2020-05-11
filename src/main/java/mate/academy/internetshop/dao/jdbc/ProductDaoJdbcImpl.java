package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.controllers.LoginController;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Override
    public Product create(Product product) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO products (name, price) VALUES (?, ?);";
            PreparedStatement statement =
                    con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create product", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products WHERE product_id = ?;";
            PreparedStatement statement =
                    con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get product with id = " + id, e);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products;";
            PreparedStatement statement =
                    con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getProduct(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get all products", e);
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "UPDATE products SET name=?, price=? WHERE product_id=?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update product", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong("product_id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"));
    }
}
