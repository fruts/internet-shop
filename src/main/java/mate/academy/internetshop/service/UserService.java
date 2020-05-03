package mate.academy.internetshop.service;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
