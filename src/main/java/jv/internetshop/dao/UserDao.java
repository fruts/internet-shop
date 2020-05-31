package jv.internetshop.dao;

import java.util.Optional;
import jv.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
