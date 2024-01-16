<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<%if (session.getAttribute("msg") != null) {%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<br>
<%}%>
LOGIN
<form method="post" action="/login">
    E-MAIL: <input type="email" name="email"><br>
    PASSWORD: <input type="password" name="password"><br>
    <input type="submit" value="LOGIN">
</form>
<a href="/register">REGISTER</a>
</body>
</html>