package jv.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.Product;
import jv.internetshop.service.ProductService;

public class GetAllProductsController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();

        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
