<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Lesson lesson = (Lesson) request.getAttribute("lessonName");%>
<%List<Student> students = (List<Student>) request.getAttribute("studentsByLesson"); %>
<html>
<style>table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    width: 30%;
}
</style>
<head>
    <title><%=lesson.getName()%>
    </title>
</head>
<body>
<table>
    <tr>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>EMAIL</th>
        <th>AGE</th>
        <th>LESSON</th>
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
    </tr>
    <%}%>
</table>
<a href="/lessons"><h3>BACK</h3></a>

</body>
</html>
