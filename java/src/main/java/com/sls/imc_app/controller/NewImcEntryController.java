package com.sls.imc_app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sls.imc_app.service.UserImcService;

@WebServlet("/newEntry")
public class NewImcEntryController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
       
    public NewImcEntryController() {
        super();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		// Retraer el id de la sesion
		int userId = (int) request.getSession().getAttribute("userId");
		
		// Retraer el peso y la altura del request
		double userHeightCm = Double.parseDouble(request.getParameter("height"));
		double userWeightKm = Double.parseDouble(request.getParameter("weight"));
		
		// Usa la clase UserImcService para agregar un nuevo registro 
	    UserImcService userImcService = new UserImcService();
	    boolean success = userImcService.addImcRecord(userId, userHeightCm, userWeightKm);
	    
	    if (!success) {
			// Si el registro fallo se guarda el error en el request y se regresa
	        request.setAttribute("error", userImcService.getErrorMessage());
	        request.getRequestDispatcher("addImcRecord.jsp").forward(request, response);
	    } else {

			// Redirige al Servlet WelcomeController para que actualice los registros IMC del usuario
			response.sendRedirect("welcome");
		}
	    
		

	}
	

}
