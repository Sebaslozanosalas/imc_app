<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>IMC Java App</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="welcome">IMC App</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item active">
	        <a class="nav-link" href="welcome">Inicio</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="addImcRecord.jsp">Registrar IMC <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="logout">Cerrar sesion</a>
	      </li>
	    </ul>
	  </div>
	</nav>

	<%
    HttpSession s = request.getSession(false); // false para no crear una nueva si no existe
    if (session == null || s.getAttribute("userId") == null) {
        // No existe userId en la sesión, redirigir a index.jsp
        response.sendRedirect("index.jsp");
    } else {
	%>
    <div class="container mt-5">
    <h2 class="text-center">Agregar nuevo registro de IMC</h2>
	<% if (request.getAttribute("error") != null) { %>
		<div class="alert alert-danger" role="alert">
			<%= request.getAttribute("error") %>
		</div>
	<% } %>
    <form action="newEntry" method="post" class="mt-4">
      <div class="form-group">
        <label for="weight">Peso (kg):</label>
        <input type="text" class="form-control" id="weight" name="weight" min="0" step="0.01" required>
      </div>
      <div class="form-group">
        <label for="height">Altura (cm):</label>
        <input type="text" class="form-control" id="height" name="height" min="0" required>
      </div>
      <button type="submit" class="btn btn-primary">Crear Nuevo Registro</button>
    </form>
  </div>
	<% } %>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>