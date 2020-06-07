package jv.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Order;
import jv.internetshop.service.OrderService;

public class OrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Order order = orderService.getByUserId(Long.valueOf(id));
        req.setAttribute("order", order);
        req.getRequestDispatcher("/WEB-INF/views/orders/order.jsp").forward(req, resp);
    }
}
