import java.sql.*;
import java.util.ArrayList;


import static java.lang.System.out;

public class Main {



    public static void main(String[] args){
        final String user = "postgres";
        final String password = "---";
        final String url = "jdbc:postgresql://localhost:5432/users";

        try (Connection connection = DriverManager.getConnection(url, user, password)){

            UserDAOImpl dao = new UserDAOImpl(connection);
            User messi = dao.Create("Lionel", "Messi", 28, 2);
            out.println("user created");

            messi.setAge(29);

            dao.update(messi);
            out.println("user updated");
            User messi2 = dao.Read(messi.getId());
            out.println(messi2.getAge());

            out.println("got new user");
            dao.delete(messi);
            out.println("user deleted");

            ArrayList<User> users = dao.getAll();
            out.println(users.size());
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
