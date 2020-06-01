package jv.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Order;
import jv.internetshop.model.Role;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.model.User;
import jv.internetshop.service.OrderService;
import jv.internetshop.service.ShoppingCartService;
import jv.internetshop.service.UserService;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private static final String USER_ID = "userId";
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long userId = Long.valueOf(req.getParameter("id"));
        if (userId.equals(req.getSession().getAttribute(USER_ID))) {
            req.getRequestDispatcher("/views/accessDenied.jsp").forward(req, resp);
        } else {
            User user = userService.getByUserId(userId);
            if (user.getRoles().stream()
                    .noneMatch(role -> role.getRoleName().equals(Role.RoleName.ADMIN))) {
                ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
                shoppingCartService.delete(shoppingCart.getId());
                List<Order> orders = orderService.getUserOrders(user.getId());
                for (Order order : orders) {
                    orderService.delete(order.getId());
                }
            }
            userService.delete(userId);
            resp.sendRedirect(req.getContextPath() + "/users/all");
        }
    }

}
