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
            rs.close();
            return userList;
        } 
    }

    public User Login(String usrEmail, String usrPass) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
        ){
            String name = null;
            String email = null;
            String pass = null;
            ResultSet rs = stmnt.executeQuery("select * from `8BqaG7Joaq`.`empresa` where `email` = '"+usrEmail+"' and `senha` = '"+usrPass+"' limit 1;");
            while (rs.next()) {
                name = rs.getString("nome");
                email = rs.getString("email");
                pass = rs.getString("senha");
            }
            if( name == null || email == null ){
                return null;
            }else {
                User person = new User(name, email, pass);
                rs.close();
                return person;
            }
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
