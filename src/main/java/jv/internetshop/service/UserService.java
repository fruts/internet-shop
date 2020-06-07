package jv.internetshop.service;

import java.util.Optional;
import jv.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
