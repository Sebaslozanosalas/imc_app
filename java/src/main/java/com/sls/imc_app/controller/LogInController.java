package com.sls.imc_app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sls.imc_app.dao.UserDAO;
import com.sls.imc_app.service.UserService;

@WebServlet("/login")
public class LogInController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


    public LogInController() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Crea variables extrayendo los datos del request
		String userName = request.getParameter("user");
	    String password = request.getParameter("password");
	    
		// Usa la clase userService para validar las credenciales del usuario
	    UserService userService = new UserService();
	    int userId = userService.userLogIn(userName, password);
	    
	    if( userId != -1 ) {
	    	// Si el usuario existe se guarda su ID en la sesion
			// y es redirigido al Servlet WelcomeController
	    	request.getSession().setAttribute("userId", userId);
	    	response.sendRedirect("welcome"); 

	    } else {
	    	// Si el usuario no existe es redirigido a la pagina de log in
	        request.setAttribute("error", "Error al inciar sesion.");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	    
	}

}
