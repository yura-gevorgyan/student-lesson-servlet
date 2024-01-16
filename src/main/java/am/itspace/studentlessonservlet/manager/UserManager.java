package am.itspace.studentlessonservlet.manager;

import am.itspace.studentlessonservlet.db.DBConnectionProvider;
import am.itspace.studentlessonservlet.model.User;

import java.sql.*;

public class UserManager {

    Connection connection = DBConnectionProvider.getProvider().getConnection();

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");

                return User.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(User user) {
        String sql = "INSERT INTO user(name,surname,email,password) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
