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
}