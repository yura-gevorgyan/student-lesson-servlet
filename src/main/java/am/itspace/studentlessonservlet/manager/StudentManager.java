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
    UserManager userManager = new UserManager();

    public void add(Student student) {
        String sql = "INSERT INTO student(name,surname,email,age,lesson_id,picture_name,user_id) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());
            ps.setString(6, student.getPictureName());
            ps.setInt(7, student.getUser().getId());

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
                String pictureName = resultSet.getString("picture_name");
                int userId = resultSet.getInt("user_id");

                Lesson lessonByID = lessonManager.getLessonByID(lessonId);
                students.add(Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonByID)
                        .pictureName(pictureName)
                        .user(userManager.getById(userId))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getByID(int id) {
        String sql = "SELECT * FROM student WHERE id =" + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                int lessonId = resultSet.getInt("lesson_id");
                String pictureName = resultSet.getString("picture_name");
                int userId = resultSet.getInt("user_id");

                Lesson lessonByID = lessonManager.getLessonByID(lessonId);
                return Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonByID)
                        .pictureName(pictureName)
                        .user(userManager.getById(userId))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                int lessonId = resultSet.getInt("lesson_id");
                String pictureName = resultSet.getString("picture_name");
                int userId = resultSet.getInt("user_id");

                Lesson lessonByID = lessonManager.getLessonByID(lessonId);

                return Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonByID)
                        .pictureName(pictureName)
                        .user(userManager.getById(userId))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                    String pictureName = resultSet.getString("picture_name");
                    int userId = resultSet.getInt("user_id");

                    Lesson lessonByID = lessonManager.getLessonByID(lessonId);
                    students.add(Student.builder()
                            .id(id)
                            .name(name)
                            .surname(surname)
                            .email(email)
                            .age(age)
                            .lesson(lessonByID)
                            .pictureName(pictureName)
                            .user(userManager.getById(userId))
                            .build());
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, surname = ?, email = ?, age = ?, lesson_id = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());
            ps.setInt(6, student.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
