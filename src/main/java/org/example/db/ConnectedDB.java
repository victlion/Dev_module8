package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public final class ConnectedDB {
    private static final String DB_URL = "jdbc:h2:~/testBase";
    private static Connection connection;

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection(DB_URL);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
}
