package com.sls.imc_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sls.imc_app.model.ImcRecord;
import com.sls.imc_app.model.User;
import com.sls.imc_app.util.DatabaseConnection;

public class ImcRegistersDAO {
	
	private Connection connection;
	

	public ImcRegistersDAO() {
		this.connection = DatabaseConnection.getConnection();
	}
	

	public boolean createRecord(ImcRecord imcRecord) {
		
		// Query para insertar un nuevo regsitro de imc
		String sql = "INSERT INTO imc_registers (user_id, current_age, height_cm, weight_km, imc, created_at) " + 
				     "VALUES (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	// Inserta los datos al Query
			statement.setInt(1, imcRecord.getUserId());									// user_id
            statement.setInt(2, imcRecord.getCurrentAge());								// current_age
            statement.setDouble(3, imcRecord.getHeightCm());							// height_cm				
            statement.setDouble(4, imcRecord.getWeightKm());							// weight_km
            statement.setDouble(5, imcRecord.getImc());									// imc
            statement.setTimestamp(6, java.sql.Timestamp.valueOf(LocalDateTime.now())); // created_at
            
	        int rowsInserted = statement.executeUpdate();
	        
	        return rowsInserted > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	   
	}

	
	public List<ImcRecord> getAllRecords(int userId) {
		
		List<ImcRecord> records = new ArrayList<>();
		
		// Query para retraer todos los registrios ordenados 
	    String sql = "SELECT * FROM imc_registers WHERE user_id = ? ORDER BY created_at DESC";
		
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
            
			// Inserta los datos al Query
			statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
            	
				// Recorrer todos los registros 
				while (resultSet.next()) {
				    
					// Crear un objeto IMCRecord para guardar los datos en la lista records
					ImcRecord record = new ImcRecord(resultSet.getInt("current_age"),
													 resultSet.getDouble("height_cm"),
											 		 resultSet.getDouble("weight_km"),
											 		 resultSet.getDouble("imc"),
											 		 resultSet.getTimestamp("created_at").toLocalDateTime()
													 );
				    records.add(record);
				}
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    
		return records;
	}
}
