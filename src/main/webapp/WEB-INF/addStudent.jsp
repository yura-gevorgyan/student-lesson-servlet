<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/13/2024
  Time: 2:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
</head>
<body>
<%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<form method="post" action="/addStudents" enctype="multipart/form-data">
    NAME: <input type="text" name="name">
    <br>
    <br>
    SURNAME: <input type="text" name="surname">
    <br>
    <br>
    EMAIL: <input type="email" name="email">
    <br>
    <br>
    AGE: <input type="number" name="age" min="16" max="60">
    <br>
    <br>
    <select name="lessonId">
        <%for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <% }%>
    </select>
    <br>
    <br>
    <input type="file" name="picture">
    <br>
    <br>
    <input type="submit" value="ADD">
</form>
</body>
</html>
