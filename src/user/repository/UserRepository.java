package user.repository;

import user.database.DatabaseConnection;
import user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT id, name FROM users";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet result =
                        statement.executeQuery(sql)
        ) {


            while (result.next()) {

                User user = new User(
                        result.getInt("id"),
                        result.getString("name")
                );

                users.add(user);
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return users;
    }

    public User findById(int id) {

        String sql = "SELECT id, name FROM users WHERE id = ?";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();


            if (result.next()) {

                return new User(
                        result.getInt("id"),
                        result.getString("name")
                );
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return null;
    }

    public User save(User user) {

        String sql =
                "INSERT INTO users(name) VALUES (?) RETURNING id";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getName()
            );


            ResultSet result =
                    statement.executeQuery();


            if (result.next()) {

                user.setId(
                        result.getInt("id")
                );

                return user;
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return null;
    }

    public boolean deleteById(int id) {

        String sql = "DELETE FROM users WHERE id = ?";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);


            int rowsDeleted =
                    statement.executeUpdate();


            return rowsDeleted > 0;


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return false;
    }

}