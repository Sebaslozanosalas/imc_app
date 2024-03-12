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
	        <a class="nav-link" href="register.jsp">Registrarse <span class="sr-only">(current)</span></a>
	      </li>
	    </ul>
	  </div>
	</nav>

	<div class="container mt-5">
    <h1 class="text-center">Registrarse</h1>
    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger" role="alert">
          <%= request.getAttribute("error") %>
      </div>
    <% } %>
    <form action="register" method="post" class="mt-4">
      <div class="form-group">
        <label for="user">Usuario:</label>
        <input type="text" class="form-control" id="user" name="user" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>
      <div class="form-group">
        <label for="firstName">Nombre:</label>
        <input type="text" class="form-control" id="firstName" name="firstName" required>
      </div>
      <div class="form-group">
        <label for="lastName">Apellido:</label>
        <input type="text" class="form-control" id="lastName" name="lastName" required>
      </div>
      <div class="form-group">
        <label for="gender">Género:</label>
        <select class="form-control" id="gender" name="gender" required>
          <option value="masculino">Masculino</option>
          <option value="femenino">Femenino</option>
        </select>
      </div>
      <div class="form-group">
        <label for="dateOfBirth">Fecha de nacimiento:</label>
        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
      </div>
      <button type="submit" class="btn btn-primary">Enviar</button>
    </form>
  </div>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>