package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.LessonManager;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/lessons/add")
public class AddLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lessonName = req.getParameter("lessonName");
        if (lessonManager.getLessonByName(lessonName.toUpperCase()) == null) {
        int duration = Integer.parseInt(req.getParameter("duration"));
        String lecturerName = req.getParameter("lecturerName");
        double price = Double.parseDouble(req.getParameter("price"));

        lessonManager.add(Lesson.builder()
                .name(lessonName.toUpperCase())
                .duration(duration)
                .lecturerName(lecturerName)
                .price(price)
                .user((User) req.getSession().getAttribute("user"))
                .build());

        resp.sendRedirect("/lessons");
        } else {
            req.setAttribute("msg", "Lesson with " + lessonName + " have already added !!!");
            req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
        }
    }
}
