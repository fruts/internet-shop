package jv.internetshop.security;

import jv.internetshop.exeptions.AuthenticationException;
import jv.internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
