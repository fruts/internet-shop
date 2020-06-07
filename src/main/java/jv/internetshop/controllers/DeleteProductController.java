package jv.internetshop.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.service.ProductService;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        productService.delete(Long.parseLong(id));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
