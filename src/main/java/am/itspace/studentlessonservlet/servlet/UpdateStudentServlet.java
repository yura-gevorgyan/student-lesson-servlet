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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/student/update")
public class UpdateStudentServlet extends HttpServlet {

    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentManager.getByID(id);
        List<Lesson> lessons = lessonManager.getLessons();

        HttpSession session = req.getSession();
        if (student != null && session.getAttribute("user") != null) {
            req.setAttribute("student", student);
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("studentId"));
        Student student = studentManager.getByID(id);
        if (student != null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");

            int age = Integer.parseInt(req.getParameter("age"));
            int lessonId = Integer.parseInt(req.getParameter("lessonId"));
            Lesson lesson = lessonManager.getLessonByID(lessonId);

            studentManager.update(Student.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .lesson(lesson)
                    .build());

            resp.sendRedirect("/student");
        } else {
            req.getSession().setAttribute("msg", "Invalid Student");
            resp.sendRedirect("/student");
        }
    }
}
