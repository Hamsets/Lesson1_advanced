import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        User user1 = new User("Roman", "Aminaev", "romanzovut@gmail.com",
                "1234", 1, new BigDecimal(0.0));

        User user2 = new User("Roman", "Korneev", "romankoren@gmail.com",
                "12", 2, new BigDecimal(5.6));

        User user3 = new User("Kirill", "Astralov", "kirya@mail.ru",
                "123", 1, new BigDecimal(0.0));

        User user4 = new User("Artiom", "Aminaev", "artist@mail.ru",
                "12345", 1, new BigDecimal(0.0));

        User user5 = new User("Ivan", "Grozny", "ivi@mail.com", "16", 1, new BigDecimal(0.0));

        User user6 = new User("Aleks", "Sanders", "ali@mail.com", "1556", 2, new BigDecimal(3.4));

        User user7 = new User("Mickey", "Mouse", "miha@mail.ru", "big", 1, new BigDecimal(5.41));

//    void printTableInfo()
        printTableInfo();

//    void create(User user)
        create(user1);
        create(user2);
        create(user3);
        create(user4);

//    List<User> findAll()
        findAll();

//      void update(User user)
        user5.setId(4L);
        update(user5);
        findAll();

//    User find(Long id)

        System.out.println("\nFind user by id=3");
        System.out.println( find(3L));

//    boolean deleteById(Long id)
        System.out.println("\nDeleted user by id=3 - " + deleteById(3L));
        findAll();

//void updateRS(User user)
        user6.setId(4L);
        updateRS(user6);
        findAll();

//  void createRS(User user)
        createRS(user7);
        findAll();

//  User findUserByEmail(String email)
//        findUserByEmail ("ivi@mail.com");

    }

    private static void update(User user) {
        System.out.println("\nUpdate user whith id=4");
        UserDataAccess userDAO = new UserDataAccess();
        userDAO.updateUser(user);
    }

    private static User find(Long id) {
        UserDataAccess userDAO = new UserDataAccess();
        return userDAO.find(id);
    }

    private static void findAll() {
        System.out.println("\nFind all users");
        UserDataAccess userDAO = new UserDataAccess();
        List<User> listUser = userDAO.findAll();
        for (User user : listUser) {
            System.out.println(user);
        }
    }

    private static void create(User user) {
        UserDataAccess userDAO = new UserDataAccess();
        userDAO.createUser(user);
    }

    private static boolean deleteById(Long id) {
        UserDataAccess userDAO = new UserDataAccess();
        return userDAO.deleteById(id);
    }

    private static void updateRS(User user) {
        System.out.println("\nUpdate user by ResultSet by id=4");
        UserDataAccess userDAO = new UserDataAccess();
        userDAO.updateUserRS(user);
    }

    private static void createRS(User user) {
        System.out.println("\nCreate user by ResultSet");
        UserDataAccess userDAO = new UserDataAccess();
        userDAO.createUserRS(user);
    }

    private static void printTableInfo() {
        System.out.println("\nPrint Table info");
        UserDataAccess userDAO = new UserDataAccess();
        userDAO.printTableInfo();
    }

//    private static
}