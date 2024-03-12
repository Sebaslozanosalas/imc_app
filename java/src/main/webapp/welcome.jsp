<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.sls.imc_app.model.ImcRecord" %>
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
	        <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="addImcRecord.jsp">Registrar IMC</a>
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
			    	response.sendRedirect("index.jsp");
		    	}
		%>


	<%
	List<ImcRecord> imcRecords = (List<ImcRecord>) request.getAttribute("imcRecords");
	%>	
	 <br>
	 <h2>Bienvenido <%=request.getAttribute("userFirstName")%>!</h2>
	 <br>
	 <%
	 if (imcRecords != null && !imcRecords.isEmpty()) {
	 %>
    <table class="table">
        <thead>
            <tr>
           		<th>Edad Actual</th>
                <th>Altura (cm)</th>
                <th>Peso (Kg)</th>
                <th>IMC</th>
                <th>Fecha de Registro</th>
            </tr>
        </thead>
        <tbody>
            <%
            for (ImcRecord record : imcRecords) {
            %>
                <tr>
                    <td><%= record.getCurrentAge() %></td>
                    <td><%= record.getHeightCm() %></td>
                    <td><%= record.getWeightKm() %></td>
                    <td><%= record.getImc() %></td>
                    <td><%= record.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
	<% } else { %>
	    <p>No hay registros de IMC para mostrar.</p>
	<% } %>
	
</body>
</html>