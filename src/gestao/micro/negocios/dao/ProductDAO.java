/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.Product;
import gestao.micro.negocios.MainApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Escola
 */
public class ProductDAO {
    private final Connection connection;
    private MainApp mainApp;
    private static ProductDAO instance;


    public ProductDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", "8BqaG7Joaq", "KZHhe6stfM");
        LogDAO.getInstance().GenerateLog("Conexão obtida com o banco para PRODUTO");
    }
    
    public static ProductDAO getInstance() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
     public void createProduct (Product prod) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`produto` (`quantidade`, `descricao`, `categoria`, "
                    + "`valorUnitario`, `valorVenda`) VALUES ('"+prod.getInventory()+"', '"+prod.getName()+"', '"+prod.getType()+"', '"+prod.getUnitPrice()+"', '"+prod.getPrice()+"');");
            LogDAO.getInstance().GenerateLog("Inserir na tabela PRODUTO para CADASTRO PRODUTO");
        }
    }
     
    public void deleteProduct (Product prod) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){           
            stmnt.executeUpdate("DELETE FROM `8BqaG7Joaq`.`produto` WHERE `id_produto`='"+prod.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Excluir da tabela PRODUTO");
        }
    }
    
    public void editProduct (Product prod) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){       
            stmnt.executeUpdate("UPDATE `8BqaG7Joaq`.`produto` SET `quantidade`='"+prod.getInventory()+"',"
                    + " `descricao`='"+prod.getName()+"', `categoria`='"+prod.getType()+"', `valorUnitario`='"+prod.getUnitPrice().toString()+"',"
                            + " `valorVenda`='"+prod.getPrice().toString()+"' WHERE `id_produto`='"+prod.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Editar na tabela PRODUTO");
        }
    }
        
    public List<Product> getProductList() throws Exception {
        List<Product> prdList = new ArrayList<>();
        try (
            Statement stmnt = connection.createStatement();
        ){
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`produto`");
            LogDAO.getInstance().GenerateLog("Consulta na tabela PRODUTO");
            while (rs.next()) {
                String qtd = rs.getString("quantidade");
                String desc = rs.getString("descricao");
                String cat = rs.getString("categoria");
                String unit = rs.getString("valorUnitario");
                String value = rs.getString("valorVenda");
                String id = rs.getString("id_produto");
                Product product = new Product(id, qtd, desc, cat, unit, value);
                prdList.add(product);
            }
            rs.close();
        } 
        return prdList;
    }
}
