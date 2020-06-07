package jv.internetshop.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Product;
import jv.internetshop.model.ShoppingCart;
import jv.internetshop.service.ProductService;
import jv.internetshop.service.ShoppingCartService;

public class AddProductToCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Product product = productService.getByUserId(Long.valueOf(req.getParameter("id")));
        shoppingCartService.addProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/products/buy");
    }
}
