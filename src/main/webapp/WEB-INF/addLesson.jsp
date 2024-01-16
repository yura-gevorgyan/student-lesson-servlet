<%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/13/2024
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD LESSON</title>
</head>
<body>
<form method="post" action="/addLesson">
    <br>
    <br>
    NAME: <input type="text" name="lessonName">
    <br>
    <br>
    DURATION: <input type="number" name="duration" min="1" max="4">
    <br>
    <br>
    LECTURER NAME: <input type="text" name="lecturerName">
    <br>
    <br>
    PRICE: <input type="number" name="price">
    <br>
    <br>
    <input type="submit" VALUE="ADD">
</form>
</body>
</html>
