import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataAccess {

    //Class.forName("org.postgresql.Driver");
    public static final String URL_PSQL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    public static final String SQL_INSERT_USER = "INSERT INTO users (first_name,last_name,email,\"password\",\"role\",rating) VALUES (?,?,?,?,?,?)";
    public static final String SQL_UPDATE_USER = "UPDATE users SET first_name=?,last_name=?,email=?,\"password\"=?,\"role\"=?,rating=? WHERE id=?";
    public static final String SQL_Find_BY_ID = "SELECT u.id, u.first_name, u.last_name, u.email, u.\"password\", u.\"role\", u.rating FROM users u WHERE u.id=?";
    public static final String SQL_Find_BY_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.email, u.\"password\", u.\"role\", u.rating FROM users u WHERE u.email=?";
    public static final String SQL_SELECT_ALL = "SELECT u.id, u.first_name, u.last_name, u.email, u.\"password\", u.\"role\", u.rating FROM users u ORDER BY u.id";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id=?";
    public static final String SQL_SELECT_ALL_FROM_INFSCHEMA = "SELECT *   FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'users'";

    public void updateUser(User user) {

        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole());
            statement.setBigDecimal(6, user.getRating());
            statement.setLong(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User find(Long id) {
        try {
            Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SQL_Find_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll() {
        List<User> listUsers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUser(resultSet);
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole());
            statement.setBigDecimal(6, user.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void updateUserRS(User user) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = statement.executeQuery(SQL_SELECT_ALL);

            while (result.next()) {
                if (result.getLong("id") == user.getId()) {
                    result.updateString("first_name", user.getFirstName());
                    result.updateString("last_name", user.getLastName());
                    result.updateString("email", user.getEmail());
                    result.updateString("password", user.getPassword());
                    result.updateInt("role", user.getRole());
                    result.updateBigDecimal("rating", user.getRating());
                    result.updateRow();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserRS(User user) {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = statement.executeQuery(SQL_SELECT_ALL);
            result.moveToInsertRow();
            result.updateString("first_name", user.getFirstName());
            result.updateString("last_name", user.getLastName());
            result.updateString("email", user.getEmail());
            result.updateString("password", user.getPassword());
            result.updateInt("role", user.getRole());
            result.updateBigDecimal("rating", user.getRating());
            result.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTableInfo() {
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FROM_INFSCHEMA);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("                  Table \"users\"");
            System.out.println("Column   |       Type       |Collation| Nullable |");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("column_name") + "\t| " +
                        resultSet.getString("data_type") +
                        "(" + resultSet.getString("character_maximum_length") + ")\t| " +
                        resultSet.getString("collation_name") + "\t| " +
                        resultSet.getString("is_nullable") + "\t|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByEmail (String email) throws SQLException{
        try (Connection connection = DriverManager.getConnection(URL_PSQL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SQL_Find_BY_EMAIL);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            return getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getString("first_name"), resultSet.getString("last_name"),
                resultSet.getString("email"), resultSet.getString("password"),
                resultSet.getInt("role"), resultSet.getBigDecimal("rating"));
        user.setId(resultSet.getLong("id"));
        return user;
    }

}
