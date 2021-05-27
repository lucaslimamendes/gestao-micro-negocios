/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author upper
 */
public class OrderDAO {
    private final Connection connection;
    private Statement stmnt;
    private static OrderDAO instance;
    private static Integer idUser;


    public OrderDAO() throws Exception {
        connection = connectionDAO.getInstance().connect("PEDIDO");
        stmnt = connection.createStatement();
    }
    
    public static OrderDAO getInstance(Integer id) throws Exception {
        if (instance == null) {
            instance = new OrderDAO();
        }
        idUser = id;
        return instance;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void createOrder (Order order) throws Exception {
        if(stmnt.isClosed()){
            stmnt = connection.createStatement();
        }
        try{
            Date dateActualy = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`pedido` (`usuario`, `cliente`, `tipo`, `detalhe`, `valor`, `data`, `dataRealizada`) VALUES ('"+idUser+"', '"+order.getClient()+"', "
                    + "'"+order.getType()+"', '"+order.getDetail()+"', '"+order.getValue()+"', '"+order.getData()+"', '"+formatter.format(dateActualy)+"');");
            LogDAO.getInstance().GenerateLog("Inserir pedido na tabela PEDIDO");
        }  finally   {
            stmnt.close();
        }
    }
}
