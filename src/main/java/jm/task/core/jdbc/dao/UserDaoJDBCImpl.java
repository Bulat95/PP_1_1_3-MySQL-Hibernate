package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS User (Id INT (11) PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        util.executeSQL(sqlCommand);
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS User";
        util.executeSQL(sqlCommand);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = String.format("INSERT INTO USER (name, lastname, age) VALUES ('%s', '%s', %d)", name, lastName, age);
        util.executeSQL(sqlCommand);
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM USER WHERE Id = " + id + "";
        util.executeSQL(sqlCommand);
    }

    public List<User> getAllUsers() {
        List<User> lu = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/USERS", "root", "1234");) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USER");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = new User(res.getString("name"), res.getString("lastName"), (byte) res.getInt("age"));
                System.out.println(user);
                lu.add(user);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return lu;
    }

    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE USER";
        util.executeSQL(sqlCommand);
    }
}
