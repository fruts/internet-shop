package jv.internetshop.controllers;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Role;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.model.User;
import jv.internetshop.service.ShoppingCartService;
import jv.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String repPassword = req.getParameter("pwd-rep");

        if (password.equals(repPassword)) {
            User newUser = new User(name, login, password);
            newUser.setRoles(Set.of(Role.of("USER")));
            userService.create(newUser);
            shoppingCartService.create(new ShoppingCart(newUser.getId()));
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("message", "Your passwords are not equals, try again");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
