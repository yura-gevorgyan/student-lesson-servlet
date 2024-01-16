<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %>
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
    <title>Lessons</title>
</head>
<body>
<div>
    <h2>All Lessons</h2>
    <% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
    <table>
        <tr>
            <th>NAME</th>
            <th>DURATION</th>
            <th>LECTURER NAME</th>
            <th>PRICE</th>
            <th>DELETE</th>
        </tr>
        <% for (Lesson lesson : lessons) {%>
        <tr>
            <td><a href="/studentByLesson?id=<%=lesson.getId()%>"><%=lesson.getName()%>
            </a>
            </td>
            <td><%=lesson.getDuration()%>
            </td>
            <td><%=lesson.getLecturerName()%>
            </td>
            <td><%=lesson.getPrice()%>
            </td>
            <td><a href="/deleteLesson?id=<%=lesson.getId()%>">DELETE</a></td>
        </tr>
        <%}%>
    </table>
</div>
<a href="/addLesson"><h2>ADD LESSON</h2></a>
<a href="/home"><h3>BACK</h3></a>
</body>
</html>
