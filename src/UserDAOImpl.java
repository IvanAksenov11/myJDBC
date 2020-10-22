import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private final Connection connection;
    public UserDAOImpl(Connection con){
        this.connection = con;
    }

    @Override
    public User Create(String name, String surname, int age, int role) throws SQLException {
        User user;
        int key;
        String sql = "INSERT INTO users (name, surname, age, role) VALUES ('"+ name + "', '" + surname + "', " + age + ", " + role + ");";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            int count = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            key = rs.getInt(1);
        }
        sql = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            user = new User(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getInt(4),rs.getInt(5));
        }
        return user;
    }

    @Override
    public User Read(int i) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?;";
        User user = new User(i);

        try (PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setInt(1, i);

            ResultSet resultSet = stm.executeQuery();
            resultSet.next();

            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setRole(resultSet.getInt("role"));
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET name = '" + user.getName() +
                "', surname = '" + user.getSurname() +
                "', age = " + user.getAge() +
                ", role = " + user.getRole() +
                " WHERE id = "+ user.getId() + ";";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            int count = statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?;";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, user.getId());

            int count = statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<User> getAll() throws SQLException{
        String sql = "SELECT * FROM users";
        ArrayList<User> res;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            res = getUsers(resultSet);
        }
        return res;
    }

    private ArrayList<User> getUsers (ResultSet resultSet) throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            int role = resultSet.getInt("role");
            User user = new User(id, name,surname,age,role);
            res.add(user);
        }
        return res;
    }
}
