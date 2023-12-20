<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 02/12/2023
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crea usuario</title>
</head>
<body>
<%
    //CÓDIGO DE VALIDACIÓN
    ArrayList<String> errores = new ArrayList<String>();

    boolean valida = true;
    String usuario = null;
    String password = null;

    //error usuario
    usuario = request.getParameter("usuario");

    if(usuario.isBlank())
    {
        errores.add("Usuario vacío o todo espacios blancos.");
        valida = false;
    }

    if(usuario == null)
    {
        errores.add("El usuario no puede ser nulo.");
        valida = false;
    }

    if(usuario.matches("\\d+"))//Para que el nombre no sea numero
    {
        errores.add("El usuario no puede ser un número");
        valida = false;
    }


    //error contraseña
    password = request.getParameter("contrasena");
    if(password.isBlank())
    {
        errores.add("Contraseña vacía o todo espacios blancos.");
        valida = false;
    }

    if(password == null)
    {
        errores.add("La contraseña no puede ser nula.");
        valida = false;
    }

    //Si cumple con los rquisitos vemos si existe en la BBDD
    if(valida)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String error = "";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root", "123456");

            String sql = "INSERT INTO usuario (username, passwd) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);//tabla usuario para gestión username=admin y passwd=admin123

            ps.setString(1, usuario);
            ps.setString(2, password);

            // Utiliza executeUpdate() para updateos

            try
            {
                int rows = ps.executeUpdate();

            }catch (Exception ex){
                ex.printStackTrace();
                error = "Error. No puede haber dos nombres de usuarios iguales.";
                session.setAttribute("error", error);
                response.sendRedirect("crud.jsp");
            }

        }catch (Exception ex) {
            ex.printStackTrace();
            out.print("<h1>Error.</h1>");
        }
        finally {
            try { rs.close(); } catch (Exception e) { /* Ignorado */ }
            try { ps.close(); } catch (Exception e) { /* Ignorado */ }
            try { conn.close(); } catch (Exception e) { /* Ignorado */ }
        }
    }
    else {
        session.setAttribute("erroresValidacion", errores);
        response.sendRedirect("crud.jsp");
    }
%>
</body>
</html>
