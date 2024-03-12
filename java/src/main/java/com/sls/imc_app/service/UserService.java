package com.sls.imc_app.service;

import java.time.*;

import com.sls.imc_app.dao.UserDAO;
import com.sls.imc_app.model.User;

// validar la fecha de nacimiento (PENDIENTE)

public class UserService {

    private UserDAO userDAO;
    private String errorMessage;


    public UserService() {
        this.userDAO = new UserDAO();
    }
    

    public int registerUser(String username, String password, String firstName,
    						String lastName, String gender, LocalDate dateOfBirth) {
    	
        // Validar la fecha de nacimiento (PENDIENTE)
        if (!validateUserDateOfBirth(dateOfBirth)){
            return -1;
        }

        // Crear objeto User para pasarle los datos a la clase DAO
        User newUser = new User(username, password, firstName,
        						lastName, gender, dateOfBirth);
        return userDAO.createNewUser(newUser);

    }
    

    public int userLogIn(String userName, String password) {

    	// Crear objeto User para pasarle los datos a la clase DAO
    	User user = new User(userName, password);
    	return userDAO.verifyUserCredentials(user);
    	
    }
    

    public String getUserFirstName(int userId) {

    	// Usa la clase DAO para retraer la informacion del usuario
    	User user = userDAO.getUserData(userId);

    	if (user != null) {
            // Regresa el nombre del usuario
            return user.getFirstName();
        }
    	
    	return null;
    }
    

    public int getUserCurrentAge(int userId) {

        // Usa la clase DAO para retraer la informacion del usuario
    	User user = userDAO.getUserData(userId);

    	if (user != null) {
            // Calcula y regresa el nombre del usuario
            LocalDate birthdate = user.getDateOfBirth();
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthdate, currentDate).getYears();
            
        } else {
            return -1;
        }    	
    	
    }


    private boolean validateUserDateOfBirth(LocalDate dateOfBirth){

        // Calcula la edad del usuario usando la fecha actual y la fecha de nacimiento
        LocalDate today = LocalDate.now();
        int userCurrentAge = Period.between(dateOfBirth, today).getYears();

        if (userCurrentAge < 15 || userCurrentAge > 110) {
            errorMessage = "Edad de nacimiento invalida. Edades aceptadas 15 - 110 años";
            return false;
        }
        
        return true;   

    }

    public String getErrorMessage() {
		return errorMessage;
	}

}