package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/add", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/deleteproduct", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/deleteuser", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/complete", List.of(Role.RoleName.USER));
        protectedUrls.put("/addtoshoppingcart", List.of(Role.RoleName.USER));
        protectedUrls.put("/shoppingcart", List.of(Role.RoleName.USER));
        protectedUrls.put("/deleteproductfromshoppingcart", List.of(Role.RoleName.USER));
        protectedUrls.put("/orders/all", List.of(Role.RoleName.USER));
        protectedUrls.put("/products/buy", List.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestedUrl = req.getServletPath();

        if (protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.getByUserId(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        User user = userService.getByUserId(userId);

        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            chain.doFilter(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessdenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
