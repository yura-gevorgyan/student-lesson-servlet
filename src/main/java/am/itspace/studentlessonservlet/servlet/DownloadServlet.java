package am.itspace.studentlessonservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/downloadImage")
public class DownloadServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "C:\\Users\\Yura\\IdeaProjects\\student-lesson-servlet\\uploadPicture";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String image = req.getParameter("image");

        File imgFile = new File(UPLOAD_DIRECTORY, image);

        try (FileInputStream inputStream = new FileInputStream(imgFile)) {
            resp.setContentType("image/jpeg");
            resp.setContentLength((int) imgFile.length());

            OutputStream outputStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
