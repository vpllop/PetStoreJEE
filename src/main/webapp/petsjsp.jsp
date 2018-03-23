<%-- 
    Document   : petsjsp
    Created on : Mar 17, 2018, 12:52:01 PM
    Author     : vpllop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>SQL Query</h1>
        <form action="PetsServlet" method="POST">
            <textarea name="sentencia" rows="8" cols="60">${param.sentencia}</textarea>
            <input type="submit" value="Enviar"/>
        </form>
        ${requestScope.resultado}
    </body>
</html>
