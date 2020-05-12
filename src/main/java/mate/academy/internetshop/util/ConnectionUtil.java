package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import mate.academy.internetshop.controllers.LoginController;
import org.apache.log4j.Logger;

public class ConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String URL =
            "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "1234");

        try {
            LOGGER.info("Connection to DB established");
            return DriverManager.getConnection(URL, dbProperties);
        } catch (SQLException e) {
            LOGGER.error("Can't establish the connection to DB", e);
            throw new RuntimeException("Can't establish the connection to DB", e);
        }
    }
}
