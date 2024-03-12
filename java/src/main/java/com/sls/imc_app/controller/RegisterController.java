package com.sls.imc_app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.sls.imc_app.dao.UserDAO;
import com.sls.imc_app.model.User;
import com.sls.imc_app.service.UserService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public RegisterController() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Crea variables extrayendo los datos del request
		String userName = request.getParameter("user");
	    String password = request.getParameter("password");
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String gender = request.getParameter("gender");
	    LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
	    
		// Usa la clase userService para registrar un nuevo usuario
	    // y retraer el id que se le asigno en la base de datos
	    UserService userService = new UserService();
	    int userId = userService.registerUser(userName, password, firstName,
	    										lastName, gender, dateOfBirth);

		// Si el ID es diferente de -1 el usuario se registro con exito
	    if (userId != -1) { 
	        
	    	// Se guardan sus datos en la sesion 
	        request.getSession().setAttribute("userId", userId);
	        request.getSession().setAttribute("firstName", firstName);
	        request.getSession().setAttribute("lastName", lastName);
	        
	        // Se redirige a la pagina de welcome
	        response.sendRedirect("welcome"); 
	        
	    } else {
	        // Se envia la respuesta con el error
	        request.setAttribute("error", userService.getErrorMessage());
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	        
	    }
	    
	}

}
