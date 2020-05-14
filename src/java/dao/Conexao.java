package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao instance;
    private Connection conn;
    private String banco = "jdbc:mysql://127.0.0.1/realocme?useSSL=false";
    
    private Conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(banco, "root", "");
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Conexao getInstance() {
        if(instance == null){
            instance = new Conexao();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return conn;
    }
}
