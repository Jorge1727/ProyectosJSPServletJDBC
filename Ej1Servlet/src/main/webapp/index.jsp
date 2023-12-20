<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <title>Log in</title>
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header bg-primary text-white">
          <h4 class="mb-0">Control de Acceso</h4>
        </div>
        <div class="card-body">
          <form method="post" action="login.jsp">
            <div class="form-group">
              <label>Usuario:</label>
              <input type="text" class="form-control" name="usuario" placeholder="Ingrese su usuario">
            </div>
            <div class="form-group">
              <label>Contraseña:</label>
              <input type="password" class="form-control" name="contrasena" placeholder="Ingrese su contraseña">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Aceptar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%
  String error = (String)session.getAttribute("errorLogin");

  if(error != null && !error.isEmpty())
  {
    out.print("<div style=\"background-color: #ff7c70;\"><p>" + error + "</p></div>");
  }

  session.removeAttribute("errorLogin");

  //recupera los errores de la sesión
  ArrayList<String> errores = (ArrayList<String>) session.getAttribute("erroresValidacion");

  if (errores != null && !errores.isEmpty()) {
    int numErrores = errores.size();

    out.println("<table>");
    out.println("<tr>");
    for (int i = 0; i < numErrores; i++) {
      out.println("<td style=\"background-color: #ff7c70;\">" + errores.get(i) + "</td>");
    }
    out.println("</tr>");
    out.println("</table>");
    out.println("<br>");
  }

  //limpia los errores almacenados en la sesión
  session.removeAttribute("erroresValidacion");

%>

<!-- bootstrap y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
