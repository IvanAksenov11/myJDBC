import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    public User Create(String name, String surname, int age, int role) throws SQLException;
    public User Read(int i) throws SQLException;
    public void update(User user) throws SQLException;
    public void delete (User user) throws SQLException;
    public ArrayList<User> getAll() throws SQLException;
}
