package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserByResultSet(resultSet);
                user.setRoles(getUsersRolesById(user.getId()));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get user with login " + login, e);
        }
    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                insertUsersRoles(user);
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + user, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserByResultSet(resultSet);
                user.setRoles(getUsersRolesById(id));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get user with ID " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUserByResultSet(resultSet);
                user.setRoles(getUsersRolesById(user.getId()));
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all users", e);
        }
    }

    @Override
    public User update(User element) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET name = ?, login = ?, password = ? "
                    + "WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setLong(4, element.getId());
            statement.executeUpdate();
            deleteUserFromUsersRoles(element.getId());
            insertUsersRoles(element);
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users WHERE user_id = ?;";
            deleteUserFromUsersRoles(id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int numberOfRowsDeleted = statement.executeUpdate();
            return numberOfRowsDeleted != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete user with ID " + id, e);
        }
    }

    private void insertUsersRoles(User user) throws SQLException {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, 1);";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        }
    }

    private User getUserByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User user = new User(name, login, password);
        user.setId(id);
        return user;
    }

    private Set<Role> getUsersRolesById(Long userId) throws SQLException {
        String selectRoleNameQuery = "SELECT role_name FROM users_roles "
                + "JOIN roles USING (role_id) WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectRoleNameQuery);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            return roles;
        }
    }

    private void deleteUserFromUsersRoles(Long userId) throws SQLException {
        String deleteUserQuery = "DELETE FROM users_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteUserQuery);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

}
