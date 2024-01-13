package am.itspace.studentlessonservlet.manager;

import am.itspace.studentlessonservlet.db.DBConnectionProvider;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    Connection connection = DBConnectionProvider.getProvider().getConnection();

    LessonManager lessonManager = new LessonManager();

    public void add(Student student) {
        String sql = "INSERT INTO student(name,surname,email,age,lesson_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                student.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                int lessonId = resultSet.getInt("lesson_id");

                Lesson lessonByID = lessonManager.getLessonByID(lessonId);
                students.add(Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonByID)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsByLesson(int lessonId) {
        String sql = "SELECT * FROM student WHERE lesson_id =" + lessonId;
        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    int age = resultSet.getInt("age");

                    Lesson lessonByID = lessonManager.getLessonByID(lessonId);
                    students.add(Student.builder()
                            .id(id)
                            .name(name)
                            .surname(surname)
                            .email(email)
                            .age(age)
                            .lesson(lessonByID)
                            .build());
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
