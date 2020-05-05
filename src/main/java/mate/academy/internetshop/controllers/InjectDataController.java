package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("admin", "admin", "123");
        admin.setRoles(List.of(Role.of("ADMIN")));
        User user = new User("user", "user", "123");
        user.setRoles(List.of(Role.of("USER")));
        User host = new User("host", "host", "123");
        host.setRoles(List.of(Role.of("ADMIN"), Role.of("USER")));
        userService.create(admin);
        userService.create(user);
        userService.create(host);
        req.getRequestDispatcher("/WEB-INF/views/injectdata.jsp").forward(req, resp);

    }
}
