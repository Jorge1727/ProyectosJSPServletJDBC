<%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 27/11/2023
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int altura = Integer.parseInt(request.getParameter("altura"));

    for (int i = 1; i <= altura; i++) {
        for (int j = 1; j <= altura - i; j++) {
            out.print("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;");//espacios en blanco
        }
        for (int k = 1; k <= 2 * i - 1; k++) {
            out.print("<img src=\"gatito.png\" width=\"50\" height=\"50\">");
        }
        out.println("<br>");
    }
%>
</body>
</html>

