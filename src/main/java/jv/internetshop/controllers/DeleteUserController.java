package jv.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Order;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.service.OrderService;
import jv.internetshop.service.ShoppingCartService;
import jv.internetshop.service.UserService;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = Long.valueOf(req.getParameter("id"));
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        shoppingCartService.delete(shoppingCart.getId());
        List<Order> orders = orderService.getUserOrders(userId);
        for (Order order : orders) {
            orderService.delete(order.getId());
        }
        userService.delete(userId);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
