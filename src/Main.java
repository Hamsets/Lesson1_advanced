import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDataAccess userDAO = new UserDataAccess();

        User user1 = new User("Roman", "Aminaev", "romanzovut@gmail.com",
                "1234", 1, new BigDecimal(0.0));

        User user2 = new User("Roman", "Korneev", "romankoren@gmail.com",
                "12", 2, new BigDecimal(5.6));

        User user3 = new User("Kirill", "Astralov", "kirya@mail.ru",
                "123", 1, new BigDecimal(0.0));

        User user4 = new User("Artiom", "Aminaev", "artist@mail.ru",
                "12345", 1, new BigDecimal(0.0));

        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        userDAO.createUser(user4);
        List<User> listUser = userDAO.findAll();
        for (User user : listUser) {
            System.out.println(user);
        }
        System.out.println(userDAO.find(1L));



    }
}