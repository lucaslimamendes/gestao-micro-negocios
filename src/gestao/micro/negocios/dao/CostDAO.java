package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.Cost;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author fael_
 */
public class CostDAO {
    private static Connection connection;
    private static Statement stmnt;
    private static CostDAO instance;
    private static Integer idUser;


    public CostDAO() throws Exception {
        connection = connectionDAO.getInstance().connect("CUSTO");
        stmnt = connection.createStatement();
    }
    
    public static CostDAO getInstance(Integer id) throws Exception {
        if (instance == null) {
            instance = new CostDAO();
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

    public void createCost (Cost cust) throws Exception {
        try{
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`custo` (`descricao`, `data`, `valor`, `usuario`) "
                    + "VALUES ('"+cust.getDesc()+"', '"+cust.getData()+"', '"+cust.getPrice()+"', '"+idUser+"');");
            LogDAO.getInstance().GenerateLog("Inserir cliente na tabela CUSTO");
        }  finally   {
            stmnt.close();
        }
    }
    
    public void editCost (Cost cost) throws Exception {
        try {       
            stmnt.executeUpdate("UPDATE `8BqaG7Joaq`.`custo` SET `descricao`='"+cost.getDesc()+"', `data`='"+cost.getData()+"', `valor`='"+cost.getPrice()+"'"
                            + " WHERE `id`='"+cost.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Editar custo na tabela CUSTO");
        } finally   {
            stmnt.close();
        }
    }
    
    public void deleteCost (Cost cost) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){           
            stmnt.executeUpdate("DELETE FROM `8BqaG7Joaq`.`custo` WHERE `id`='"+cost.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Excluir custo da tabela CUSTO");
        } finally   {
            stmnt.close();
        }
    }

    public List<Cost> getCostList () throws Exception {
        try {
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`custo` WHERE `usuario` = '"+idUser+"';");
            LogDAO.getInstance().GenerateLog("Consulta custo na tabela CUSTO");
            List<Cost> costList = new ArrayList<>();
            while (rs.next()) {
                String desc = rs.getString("descricao");
                String data = rs.getString("data");
                String price  = rs.getString("valor");
                String id = rs.getString("id");
                Cost cost = new Cost(id, data, price, desc);
                costList.add(cost);
            }
            return costList;
        }  finally   {
            stmnt.close();
        }
    }
}
