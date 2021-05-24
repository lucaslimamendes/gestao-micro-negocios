/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Escola
 */
public class connectionDAO {
    private static connectionDAO instance;

    public static connectionDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new connectionDAO();
        }
        return instance;
    }
    
    public Connection connect(String dao) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", "8BqaG7Joaq", "KZHhe6stfM");
        LogDAO.getInstance().GenerateLog("Conexão obtida com o banco para " + dao);
        return connect;
    }
    
     public Connection connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", "8BqaG7Joaq", "KZHhe6stfM");
        return connect;
    }
    
}
