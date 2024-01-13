package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.LessonManager;
import am.itspace.studentlessonservlet.manager.StudentManager;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/studentByLesson")
public class StudentByLessonServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Student> students = studentManager.getStudentsByLesson(id);

        Lesson lessonByID = lessonManager.getLessonByID(id);

        req.setAttribute("lessonName", lessonByID);
        req.setAttribute("studentsByLesson", students);
        req.getRequestDispatcher("/WEB-INF/studentByLesson.jsp").forward(req, resp);
    }
}
