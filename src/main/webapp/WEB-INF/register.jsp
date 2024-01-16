<%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/16/2024
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REGISTER</title>
</head>
<body>
<form method="post" action="/register">
    NAME: <input type="text" name="name"><br>
    SURNAME: <input type="text" name="surname"><br>
    E-MAIL: <input type="email" name="email"><br>
    PASSWORD: <input type="password" name="password"><br>
    <input type="submit" value="REGISTER">
</form>
</body>
</html>
