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
    private Statement stmnt;
    private static UserDAO instance;


    public UserDAO() throws Exception {
        connection = connectionDAO.getInstance().connect("USUARIO");
        stmnt = connection.createStatement();
    }
    
    public static UserDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void createPerson(String usrName, String usrEmail, String usrPass) throws Exception {
        if(stmnt.isClosed()){
            stmnt = connection.createStatement();
        }
        try {
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`usuario` (`email`, `senha`, `nome`) VALUES ('"+usrEmail+"', '"+usrPass+"', '"+usrName+"');");
            LogDAO.getInstance().GenerateLog("Inserir na tabela USUARIO para CADASTRO EMPRESA");
        }  finally   {
            stmnt.close();
        }
    }

    public User Login(String usrEmail, String usrPass) throws Exception {
        if(stmnt.isClosed()){
            stmnt = connection.createStatement();
        }
        try {
            String id = null;
            String name = null;
            String email = null;
            String pass = null;
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`usuario` WHERE `email` = '"+usrEmail+"' and `senha` = '"+usrPass+"' limit 1;");
            LogDAO.getInstance().GenerateLog("Consulta na tabela USUARIO para LOGIN");
            while (rs.next()) {
                id = rs.getString("id");
                name = rs.getString("nome");
                email = rs.getString("email");
                pass = rs.getString("senha");
            }
            if( name == null || email == null ){
                return null;
            }else {
                User person = new User(id, name, email, pass);
                rs.close();
                return person;
            }
        } finally   {
            stmnt.close();
        }
    }

    public List<User> getPersonList() throws Exception {
        if(stmnt.isClosed()){
            stmnt = connection.createStatement();
        }
        
        try {
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`usuario`");
            LogDAO.getInstance().GenerateLog("Consulta na tabela USUARIO");
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");;
                String name = rs.getString("nome");
                String email = rs.getString("email");
                String pass = rs.getString("senha");
                User person = new User(id, name, email, pass);
                userList.add(person);
            }
            return userList ;
        }  finally   {
            stmnt.close();
        }
    }
}
