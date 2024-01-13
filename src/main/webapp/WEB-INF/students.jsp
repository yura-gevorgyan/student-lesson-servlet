<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/13/2024
  Time: 2:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        width: 30%;
    }
</style>
<head>
    <title>Students</title>
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<h2>All Students</h2>
<div>
    <table>
        <tr>
            <th>NAME</th>
            <th>SURNAME</th>
            <th>EMAIL</th>
            <th>AGE</th>
            <th>LESSON</th>
            <th>DELETE</th>
        </tr>
        <%for (Student student : students) { %>
        <tr>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getSurname()%>
            </td>
            <td><%=student.getEmail()%>
            </td>
            <td><%=student.getAge()%>
            </td>
            <td><%=student.getLesson().getName()%>
            </td>
            <td><a href="deleteStudent?id=<%=student.getId()%>">DELETE</a></td>
        </tr>
        <%}%>
    </table>
</div>
<a href="addStudents"><h2>Add Students</h2></a>
</body>
</html>
