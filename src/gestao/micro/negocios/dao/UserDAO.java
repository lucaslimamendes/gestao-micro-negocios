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
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<User> createPerson(String usrName, String usrEmail, String usrPass) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet useRs = stmnt.executeQuery("use 8BqaG7Joaq;");
        ){
            int create = stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`empresa` (`email`, `senha`, `nome`) VALUES ('"+usrEmail+"', '"+usrPass+"', '"+usrName+"');");
            ResultSet rs = stmnt.executeQuery("select * from empresa;");
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("nome");
                String email = rs.getString("email");
                String pass = rs.getString("senha");
                User person = new User(name, email, pass);
                userList.add(person);
            }
            return userList;
        } 
    }

    public List<User> getPersonList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from empresa");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("nome");
                String email = rs.getString("email");
                String pass = rs.getString("senha");
                User person = new User(name, email, pass);
                userList.add(person);
            }
            return userList ;
        } 
    }
}
