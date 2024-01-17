package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student/delete")
public class DeleteStudentServlet extends HttpServlet {

    private StudentManager studentManager = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        studentManager.delete(id);
        resp.sendRedirect("/student");
    }
}
