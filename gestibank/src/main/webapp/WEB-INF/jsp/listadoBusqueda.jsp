<%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 20/12/2023
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.iesvdm.gestibank.model.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesvdm.gestibank.model.Cliente"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Socios</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="estilos.css" />
    <link rel="stylesheet" type="text/css" href="css/backtop.css" />
</head>
<body class="bg-light">

<!-- BackToTop Button -->
<a href="javascript:void(0);" id="backToTop" class="back-to-top">
    <i class="arrow"></i><i class="arrow"></i>
</a>

<div class="container bg-white sticky-top">

    <div class="row">
        <div class="col-md-4 h3">Nombre</div>
    </div>
</div>

<div class="container bg-light">
    <%
        //                                                          v----RECOGER listado DE SOCIO DEL request
        List<Cliente> listaBusqueda = (List<Cliente>) request.getAttribute("listaBusqueda");
        // FOR-EACH SOBRE LA COLECCIÓN DE listado DE SOCIO
        for (Cliente cliente : listaBusqueda) {
    %>
    <div id="<%=cliente.getClienteId()%>" class="row mt-2 body">
        <div class="col-md-4 align-self-center"><%=cliente.getNombre() %>
        </div>


        <div class="col-md-2 align-self-center text-center">
            <form class="d-inline" method="get" action="EditarSociosServlet">
                <input type="hidden" name="codigo" value="<%=cliente.getClienteId() %>"/>
                <input class="btn btn-primary"  type="submit" value="Editar">
            </form>
        </div>    </div>
    <%
            //v--- FIN DEL BUCLE FOR CON HTML INCRUSTADO
        } // for
    %>

    <div class="row mt-2">
        <form class="d-inline" method="get" action="ListarSociosServlet"><!--Fataba una r al Servlet de BorrarSociosServlet y EditarSociosServlet-->
            <input class="btn btn-primary"  type="submit" value="Volver">
        </form>

    </div>

</div>


<script type="text/javascript" src="js/bootstrap.bundle.js" ></script>
</body>
</html>
