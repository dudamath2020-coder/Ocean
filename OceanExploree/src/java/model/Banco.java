package model;
import java.sql.*;

public class Banco {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            // Adicione ?useUnicode=true&characterEncoding=UTF-8 na URL
            String url = "jdbc:postgresql://localhost:5432/OCEAN?useUnicode=true&characterEncoding=UTF-8";
            return DriverManager.getConnection(url, "postgres", "ifsp");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado.");
        }
    }
}
