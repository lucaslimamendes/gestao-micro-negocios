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
    private static Connection connection;
    private static Statement stmnt;
    private MainApp mainApp;
    private static ProductDAO instance;
    private static Integer idUser;


    public ProductDAO() throws Exception {
        connection = connectionDAO.getInstance().connect("PRODUTO");
        stmnt = connection.createStatement();
    }
    
    public static ProductDAO getInstance(Integer id) throws Exception {
        if (instance == null) {
            instance = new ProductDAO();
        }
        
        if(stmnt.isClosed()){
            stmnt = connection.createStatement();
        }
        
        idUser = id;
        return instance;
    }
    
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
     public void createProduct (Product prod) throws Exception {
         try {
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`produto` (`quantidade`, `descricao`, `categoria`, "
                    + "`valorUnitario`, `valorVenda`, `usuario`) VALUES ('"+prod.getInventory()+"', '"+prod.getName()+"', '"+prod.getType()+"', "
                            + "'"+prod.getUnitPrice()+"', '"+prod.getPrice()+"', '"+idUser+"');");
            LogDAO.getInstance().GenerateLog("Inserir na tabela PRODUTO para CADASTRO PRODUTO");
        } finally   {
            stmnt.close();
        }
    }
     
    public void deleteProduct (Product prod) throws Exception {
        try {           
            stmnt.executeUpdate("DELETE FROM `8BqaG7Joaq`.`produto` WHERE `id_produto`='"+prod.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Excluir da tabela PRODUTO");
        } finally   {
            stmnt.close();
        }
    }
    
    public void editProduct (Product prod) throws Exception {
        try {       
            stmnt.executeUpdate("UPDATE `8BqaG7Joaq`.`produto` SET `quantidade`='"+prod.getInventory()+"',"
                    + " `descricao`='"+prod.getName()+"', `categoria`='"+prod.getType()+"', `valorUnitario`='"+prod.getUnitPrice().toString()+"',"
                            + " `valorVenda`='"+prod.getPrice().toString()+"' WHERE `id_produto`='"+prod.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Editar na tabela PRODUTO");
        } finally   {
            stmnt.close();
        }
    }
        
    public List<Product> getProductList() throws Exception {
        List<Product> prdList = new ArrayList<>();
        try {
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`produto` WHERE `usuario` = '"+idUser+"'");
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
        }  finally   {
            stmnt.close();
        }
        return prdList;
    }
}
