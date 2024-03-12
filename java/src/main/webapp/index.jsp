<%@ page import="com.sls.imc_app.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>IMC Java App</title>
	<!-- Incluye Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
    .header-container {
      min-height: 100vh; /* Altura de viewport para que ocupe toda la pantalla */
      display: flex;
      justify-content: center; /* Centrado horizontal */
      align-items: center; /* Centrado vertical */
      background-color: #f8f9fa; 
    }
    h2 {
      font-weight: 700; 
      color: #212529; 
    }
  </style>
</head>
<body class="bg-light">
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="index.jsp">IMC App</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="login.jsp">Iniciar sesion</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="register.jsp">Registrarse</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	<%
	    HttpSession s = request.getSession(false); // false para no crear una nueva si no existe
	    if (session == null || s.getAttribute("userId") == null) {  
    } else {
        // Existe userId, imprimir el ID del usuario
        response.sendRedirect("welcome.jsp");
	
	    }
	%>
	
	<div class="header-container">
    	<h2>Bienvenido a IMC App!</h2>
  	</div>
  	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>