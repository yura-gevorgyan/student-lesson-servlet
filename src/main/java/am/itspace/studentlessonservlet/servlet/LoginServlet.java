package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.manager.UserManager;
import am.itspace.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.getByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            req.getSession().invalidate();
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        } else {
            req.getSession().setAttribute("msg", "Invalid E-MAIL or PASSWORD");
            resp.sendRedirect("/");
        }
    }
}
