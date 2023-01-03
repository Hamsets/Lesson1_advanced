import java.sql.*;

import java.util.List;

public class UserDataAccess {

 //   Class.("org.postgresql.Driver");
    public static final String URL_PSQL = "jdbc:postgresql://localhost:5432/users";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    public static final String SQL_INSERT_USER = "INSERT INTO users " +
            "(first_name, last_name, email, password, role, rating) VALUES " +
            "('?','?','?','?',?,?)";
    public static final String SQL_UPDATE_USER = "UPDATE users " +
            "SET first_name = '?', last_name = '?', email = '?', password = '?', role = ?, rating = ?";
    public static final String SQL_Find_BY_ID = "SELECT * FROM public.users WHERE id=?";
    public static final String SQL_SELECT_ALL = "SELECT * FROM users";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id=?";

    public void updateUser (User user) {

        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setInt(5,user.getRole());
            statement.setBigDecimal(6,user.getRating());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User find (Long id) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)){
            PreparedStatement statement = connection.prepareStatement(SQL_Find_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = getUser(resultSet);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> findAll () {
        List<User> listUsers = null;
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)){
            Statement statement = connection.createStatement ();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (!resultSet.next()) {
                User user = getUser(resultSet);
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setInt(5,user.getRole());
            statement.setBigDecimal(6,user.getRating());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean deleteById (Long id) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
//            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    void printTamleInfo () {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeQuery("");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User (
                resultSet.getString("first_name"), resultSet.getString("last_name"),
                resultSet.getString("email"), resultSet.getString("password"),
                resultSet.getInt("role"), resultSet.getBigDecimal("rating"));
        return user;
    }

}
