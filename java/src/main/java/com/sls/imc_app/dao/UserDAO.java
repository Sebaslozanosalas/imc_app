package com.sls.imc_app.dao;

import java.sql.Statement;

import com.sls.imc_app.model.User;
import com.sls.imc_app.util.DatabaseConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
	
	private Connection connection;
	

	public UserDAO() {
		this.connection = DatabaseConnection.getConnection();
	}
	

	public int createNewUser(User newUser) {
		
		// Query para insertar un nuevo usuario
	    String sql = "INSERT INTO users (user_name, password, first_name, last_name, gender, date_of_birth, created_at)" + 
	    			 "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    	
	    	// Inserta los datos al Query
	        statement.setString(1, newUser.getUserName());								// user_name 
	        statement.setString(2, newUser.getPassword()); 								// password
	        statement.setString(3, newUser.getFirstName()); 							// first_name
	        statement.setString(4, newUser.getLastName()); 								// last_name
	        statement.setString(5, newUser.getGender()); 								// gender
	        statement.setDate(6, java.sql.Date.valueOf(newUser.getDateOfBirth())); 		// date_of_birth
	        statement.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now())); // created_at
	        
	        int rowsInserted = statement.executeUpdate();
	        
	        if (rowsInserted > 0) {
	            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1); // Retorna el ID generado para el nuevo usuario
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return -1;
	    
	}
	

	public int verifyUserCredentials(User newUser) {

		// Query para consultar si existe un registro con ese usuario y contraseña
        String sql = "SELECT id FROM users WHERE user_name = ? AND password = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	
			// Inserta los datos al Query
            statement.setString(1, newUser.getUserName());
            statement.setString(2, newUser.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id"); // Devuelve el id del usuario
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; 
        
    }


	public User getUserData(int userId) {
		
		User user = null;
		
		// Query para retraer los datos de un usuario
	    String sql = "SELECT id, user_name, first_name, last_name, gender, date_of_birth, created_at FROM USERS WHERE ID = ?";
	    
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	
			// Inserta los datos al Query
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
            	
                if (resultSet.next()) {
					
					user = new User(resultSet.getInt("id"),
									resultSet.getString("user_name"),
									resultSet.getString("first_name"),
									resultSet.getString("last_name"),
									resultSet.getString("gender"),
									resultSet.getDate("date_of_birth").toLocalDate());
                      
                } 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user; // Error durante la consulta SQL
		
	}

	
}
