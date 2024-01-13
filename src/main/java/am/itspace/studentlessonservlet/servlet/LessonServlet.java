package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.LessonManager;
import am.itspace.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonServlet extends HttpServlet {
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getLessons();

        req.setAttribute("lessons", lessons);

        req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);

    }
}
