package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author upper
 */
public class UserDAO {
    private final Connection connection;

    public UserDAO(String dbURL, String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<User> createPerson(String usrName, String usrEmail, String usrPass) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet create = stmnt.executeQuery("INSERT INTO `8BqaG7Joaq`.`empresa` (`email`, `senha`, `nome`) VALUES ('"+usrName+"', '"+usrEmail+"', '"+usrPass+"')");
            ResultSet rs = stmnt.executeQuery("select * from users");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                User person = new User(name, email, pass);
                userList.add(person);
            }
            return userList;
        } 
    }

    public List<User> getPersonList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from users");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                User person = new User(name, email, pass);
                userList.add(person);
            }
            return userList ;
        } 
    }
}
