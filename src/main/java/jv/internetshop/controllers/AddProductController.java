package jv.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Product;
import jv.internetshop.service.ProductService;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        productService.create(new Product(name, price));
        resp.sendRedirect(req.getContextPath() + "/products/add");
    }
}
