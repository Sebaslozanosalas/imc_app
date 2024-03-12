package com.sls.imc_app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/imc_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    
    public static Connection getConnection() {
    	
        Connection conn = null;
        
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD); 
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
            
        }
        
        return conn;
    }
}
