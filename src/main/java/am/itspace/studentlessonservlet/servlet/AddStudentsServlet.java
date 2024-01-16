package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.LessonManager;
import am.itspace.studentlessonservlet.manager.StudentManager;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudents")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class AddStudentsServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();

    private StudentManager studentManager = new StudentManager();

    private final String UPLOAD_DIRECTORY = "C:\\Users\\Yura\\IdeaProjects\\student-lesson-servlet\\uploadPicture";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");

        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        Lesson lesson = lessonManager.getLessonByID(lessonId);
        Part picture = req.getPart("picture");
        String pictureName = null;

        if (picture != null && picture.getSize() > 0) {
            pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
        }

        studentManager.add(Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .lesson(lesson)
                .pictureName(pictureName)
                .build());

        resp.sendRedirect("/student");
    }
}
