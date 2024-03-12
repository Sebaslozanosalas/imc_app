package com.sls.imc_app.service;

import java.util.List;

import com.sls.imc_app.dao.ImcRegistersDAO;
import com.sls.imc_app.model.ImcRecord;

public class UserImcService {
	
	private ImcRegistersDAO imcRegistersDAO;
	private String errorMessage;


    public UserImcService() {
        this.imcRegistersDAO = new ImcRegistersDAO();
    }
    
    
    public boolean addImcRecord(int userId, double userHeight,
    					double userWeight) {
    		
    	// Retraer la edad del usuario 
    	UserService userService = new UserService();
    	int userCurrentAge = userService.getUserCurrentAge(userId);
    	
    	// Validar altura
    	if (!validateHeight(userHeight)) {
    		return false;
    	}
    	
    	// Validar peso
    	if (!validateWeight(userWeight)) {
    		return false;
    	}
    	
    	// Calcula IMC
    	double userImc = calculateImc(userHeight, userWeight);
    	
    	// Validar IMC
    	if (userImc == -1) {
    		return false;
    	}
    	
    	// Guarda los datos en un objeto imcRecord 
    	ImcRecord imcRecord = new ImcRecord(userId, userCurrentAge, userHeight,
    										userWeight, userImc);
    	
		// Usa la clase DAO para agregar el registro a la base de datos
    	return imcRegistersDAO.createRecord(imcRecord);
    	
    }
    
    public List<ImcRecord> getUserImcData(int userId) {
        return imcRegistersDAO.getAllRecords(userId);
    }
        
    
    private double calculateImc(double height, double weight) {
		
    	// Calcular IMC
    	double heightInMeters = height / 100.0;;
    	double imc = weight / (heightInMeters * heightInMeters);
    	
    	// Validar IMC
    	if (!validateImc(imc)) {
    		return -1;
    	}
    	
    	return imc;
    	
    }
    
    private boolean validateHeight(double height) {
    	if (height < 100 || height > 250) { 
            errorMessage = "Altura inválida. Ingresa una altura entre 100 cm y 250 cm.";
            return false;
        }
        return true;
    }
    
    private boolean validateWeight(double weight) {
    	if (weight < 40 || weight > 300) { 
            errorMessage = "Peso inválido. Ingresa un peso entre 40kg y 300kg";
            return false;
        }
        return true;
    }
    
    private boolean validateImc(double imc) {
   	if (imc <= 0) {
   		errorMessage = "IMC Invalido";
   		return false;
   	}
    	return true;
    }


	public String getErrorMessage() {
		return errorMessage;
	}

}
