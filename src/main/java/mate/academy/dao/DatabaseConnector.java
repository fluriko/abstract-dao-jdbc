package mate.academy.dao;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class DatabaseConnector {
    private static final Logger logger = Logger.getLogger(DatabaseConnector.class);

    private static final String userName = "sa";
    private static final String password = "";
    private static final String connectionUrl = "jdbc:h2:tcp://localhost/~/ma";

    public static Optional<Connection> connect() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            return Optional.ofNullable(connection);
        } catch (Exception e) {
            logger.error("Error in connecting to h2", e);
            return Optional.empty();
        }
    }
}
