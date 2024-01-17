<%@ page import="am.itspace.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/16/2024
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Student student = (Student) request.getAttribute("student");%>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<html>
<head>
    <title>UPDATE</title>
</head>
<body>
<form method="post" action="/student/update">
    <input type="hidden" name="studentId" value="<%=student.getId()%>">
    NEW NAME: <input type="text" name="name" value="<%=student.getName()%>"><br>
    NEW SURNAME: <input type="text" name="surname" value="<%=student.getSurname()%>"><br>
    NEW E-MAIL: <input type=" email" name="email" value="<%=student.getEmail()%>"><br>
    NEW AGE: <input type="number" name="age" min="16" max="40" value="<%=student.getAge()%>"><br>
    NEW LESSON: <br>
    <select name="lessonId">
        <%for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <%}%>
    </select>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>
