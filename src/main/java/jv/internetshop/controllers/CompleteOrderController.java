package jv.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Order;
import jv.internetshop.model.Product;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.model.User;
import jv.internetshop.service.OrderService;
import jv.internetshop.service.ShoppingCartService;
import jv.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private static UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        List<Product> products = shoppingCart.getProducts();
        User user = userService.getByUserId(shoppingCart.getUserId());
        Order order = orderService.completeOrder(products, user);
        resp.sendRedirect(req.getContextPath() + "/order?id=" + order.getId());
    }
}
