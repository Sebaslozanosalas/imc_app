package com.sls.imc_app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.sls.imc_app.model.ImcRecord;
import com.sls.imc_app.service.UserImcService;
import com.sls.imc_app.service.UserService;


@WebServlet("/welcome")
public class WelcomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public WelcomeController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userId") != null){

            // Verifica si existe un ID en la sesion 
            int userId = (int) session.getAttribute("userId");
            
            // Obtener el nombre del usuario y agregarlo en el request
            UserService userService = new UserService();
            String userFirstName = userService.getUserFirstName(userId);
            request.setAttribute("userFirstName", userFirstName);
            
            // Obtén la lista de registros IMC para el usuario
            UserImcService userIMCService = new UserImcService();
            List<ImcRecord> records = userIMCService.getUserImcData(userId);

            // Establece los registros como atributo de la solicitud
            request.setAttribute("imcRecords", records);
            
            // Reenvía a welcome.jsp con los datos
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            dispatcher.forward(request, response);

        } else {
            // Redirige al usuario a la página de inicio de sesión si no está logueado
            response.sendRedirect("login.jsp");
        }

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
