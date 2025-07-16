package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMYSQL {

    private static final String HOST = "jdbc:mysql://localhost:3312/industria_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(HOST, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao se conectar ao banco: " + e.getMessage(), e);
        }
    }
}


