package am.itspace.studentlessonservlet.manager;

import am.itspace.studentlessonservlet.db.DBConnectionProvider;
import am.itspace.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    Connection connection = DBConnectionProvider.getProvider().getConnection();
    UserManager userManager = new UserManager();

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,lecturer_name,price,user_id) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, lesson.getName());
            ps.setInt(2, lesson.getDuration());
            ps.setString(3, lesson.getLecturerName());
            ps.setDouble(4, lesson.getPrice());
            ps.setInt(5, lesson.getUser().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getLessons() {
        String sql = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                int userId = resultSet.getInt("user_id");

                Lesson lesson = Lesson.builder()
                        .id(id)
                        .name(name)
                        .duration(duration)
                        .lecturerName(lecturerName)
                        .price(price)
                        .user(userManager.getById(userId))
                        .build();

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public void delete(int id) {
        String sql = "DELETE FROM lesson WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            deleteStudentByLesson(id);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudentByLesson(int id) {
        String sql = "DELETE FROM student WHERE lesson_id =" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lesson getLessonByID(int lessonId) {
        String sql = "SELECT * FROM lesson WHERE id =" + lessonId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                int userId = resultSet.getInt("user_id");
                Lesson lesson = Lesson.builder()
                        .id(id)
                        .name(name)
                        .duration(duration)
                        .lecturerName(lecturerName)
                        .price(price)
                        .user(userManager.getById(userId))
                        .build();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLessonByName(String name) {
        String sql = "SELECT * FROM lesson WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                int userId = resultSet.getInt("user_id");
                Lesson lesson = Lesson.builder()
                        .id(id)
                        .name(name)
                        .duration(duration)
                        .lecturerName(lecturerName)
                        .price(price)
                        .user(userManager.getById(userId))
                        .build();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
