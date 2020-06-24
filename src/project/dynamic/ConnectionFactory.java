package project.dynamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory{
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://http://xx.xx.xx.xx/:3306/test?useTimezone=true&serverTimezone=UTC";
            String usuario = "xxx";
            String senha = "xx";
            return DriverManager.getConnection(url, usuario, senha);
            
        } catch (SQLException e) {
            System.out.println("Erro  1");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro  2");
            throw new RuntimeException(ex);

        }
    }
}
