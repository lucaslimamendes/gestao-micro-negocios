/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escola
 */
public class ProductDAO {
    private final Connection connection;

    public ProductDAO(String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    /*public void createProduct (String prdQtd, String prdDesc, String prdCat, String prdUnit, String prdValue) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
        ){
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`produto` (`quantidade`, `descricao`, `categoria`, "
                    + "`valorUnitario`, `valorVenda`) VALUES ('"+prdQtd+"', '"+prdDesc+"', '"+prdCat+"', '"+prdUnit+"', '"+prdValue+"');");
        }
    }*/
    
     public void createProduct (Product prod) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
        ){
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`produto` (`quantidade`, `descricao`, `categoria`, "
                    + "`valorUnitario`, `valorVenda`) VALUES ('"+prod.getInventory()+"', '"+prod.getName()+"', '"+prod.getType()+"', '"+prod.getUnitPrice()+"', '"+prod.getPrice()+"');");
        }
    }
        
    public List<Product> getProductList() throws SQLException {
        List<Product> prdList = new ArrayList<>();
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`produto`");
        ){    
            while (rs.next()) {
                String qtd = rs.getString("quantidade");
                String desc = rs.getString("descricao");
                String cat = rs.getString("categoria");
                String unit = rs.getString("valorUnitario");
                String value = rs.getString("valorVenda");
                Product product = new Product(qtd, desc, cat, unit, value);
                prdList.add(product);
            }
            rs.close();
        } 
        return prdList;
    }
}
