

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDataAccess {

    public static final String URL_PSQL = "jdbc://localhost/postgres/users:5432";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    protected Connection connection;

    public void updateUser (User user) {

        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET first_name = ?, last_name = ?, email = ?, password = ?, role = null, rating = null");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public User find (Long id) {
        return user;
    }

    public List<User> findAll () {
        return listUser;
    }

    public void create (User user) {

    }
    boolean deleteById (Long id) {
        return true;
    }
}
