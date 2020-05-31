package jv.internetshop.security;

import jv.internetshop.exeptions.AuthenticationException;
import jv.internetshop.lib.Inject;
import jv.internetshop.lib.Service;
import jv.internetshop.model.User;
import jv.internetshop.service.UserService;
import jv.internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService
                .findByLogin(login)
                .orElseThrow(()
                        -> new AuthenticationException("Incorrect login or password"));

        if (HashUtil.hashPassword(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
