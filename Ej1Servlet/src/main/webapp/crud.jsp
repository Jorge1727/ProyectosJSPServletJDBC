<%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 02/12/2023
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Log in</title>
</head>
<body class="bg-light">


<%
    //CARGA DEL DRIVER Y PREPARACIÓN DE LA CONEXIÓN CON LA BBDD
    //						v---------UTILIZAMOS LA VERSIÓN MODERNA DE LLAMADA AL DRIVER, no deprecado
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root", "123456");

    //UTILIZAR STATEMENT SÓLO EN QUERIES NO PARAMETRIZADAS.
    Statement s = conexion.createStatement();
    ResultSet listado = s.executeQuery ("SELECT * FROM usuario");

    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwds = new ArrayList<>();

    while (listado.next()) {
        usernames.add(listado.getString("username"));
        passwds.add(listado.getString ("passwd"));
    }
    listado.close();
    s.close();
    conexion.close();
%>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-12">
            <h1 class="mb-0 bg-primary text-white">Gestión de usuarios</h1>
            <table class="table">
                <!-- Encabezados de la tabla -->
                <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                </tr>
                </thead>
                <!-- Contenido de la tabla -->
                <tbody>
                <% for (int i = 0; i < usernames.size(); i++) { %>
                <tr>
                    <td><%= usernames.get(i) %></td>
                    <td><%= passwds.get(i) %></td>

<%--
                SIN SENTIDO DE IMPLEMENTAR PUESTO QUE NO TIENE ID, MEJOR EN EL EJERCICIO DEL BANCO.
                            <td>--%>

<%--                        <form method="post" action="BorrarSociosServlet">--%>
<%--                            <input type="hidden" name="codigo" value=""/>--%>
<%--                            <input class="btn btn-danger" type="submit" value="Borrar">--%>
<%--                        </form>--%>
<%--                        <form method="get" action="EditarSociosServlet">--%>
<%--                            <input type="hidden" name="codigo" value=""/>--%>
<%--                            <input class="btn btn-warning" type="submit" value="Editar">--%>
<%--                        </form>--%>
<%--                    </td>--%>
                </tr>
                <% } %>
                </tbody>
            </table>

            <div class="card-body">
                <form method="post" action="crearUsuario.jsp">
                    <div class="form-group">
                        <label>Usuario:</label>
                        <input type="text" class="form-control" name="usuario" placeholder="Usuario">
                    </div>
                    <div class="form-group">
                        <label>Contraseña:</label>
                        <input type="password" class="form-control" name="contrasena" placeholder="Contraseña">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Añadir usuario</button>
                </form>
            </div>

        </div>
    </div>
</div>

<%
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

    //recupera error de duplicidad de nombres usuario
    String error = (String)session.getAttribute("error");

    if (error != null && !error.isEmpty()) {

        out.println("<table>");
        out.println("<tr>");
        out.println("<td style=\"background-color: #ff7c70;\">" + error + "</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
    }

    //limpia los errores almacenados en la sesión
    session.removeAttribute("error");

%>


<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
