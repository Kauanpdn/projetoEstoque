package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/estoque_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 👈 ESSENCIAL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco", e);
        }
    }
}
