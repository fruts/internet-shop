package jv.internetshop.controllers;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Role;
import jv.internetshop.model.User;
import jv.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("admin", "admin", "123");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        User user = new User("user", "user", "123");
        user.setRoles(Set.of(Role.of("USER")));
        User host = new User("host", "host", "123");
        host.setRoles(Set.of(Role.of("ADMIN"), Role.of("USER")));
        userService.create(admin);
        userService.create(user);
        userService.create(host);
        req.getRequestDispatcher("/WEB-INF/views/injectdata.jsp").forward(req, resp);

    }
}
