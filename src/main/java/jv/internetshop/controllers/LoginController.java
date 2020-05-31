package jv.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jv.internetshop.exeptions.AuthenticationException;
import jv.internetshop.security.AuthenticationService;
import jv.internetshop.lib.Injector;
import jv.internetshop.model.User;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("jv");
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private AuthenticationService authService
            = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");

        try {
            User user = authService.login(login, pwd);
            HttpSession session = req.getSession();
            LOGGER.info("User login: " + user.getLogin());
            session.setAttribute("user_id", user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
