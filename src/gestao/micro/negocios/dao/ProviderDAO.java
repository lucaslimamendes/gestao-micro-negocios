package gestao.micro.negocios.dao;

import gestao.micro.negocios.model.Provider;
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
public class ProviderDAO {
    private final Connection connection;
    private static ProviderDAO instance;


    public ProviderDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false", "8BqaG7Joaq", "KZHhe6stfM");
        LogDAO.getInstance().GenerateLog("Conexão obtida com o banco para USUARIO");

    }
    
    public static ProviderDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new ProviderDAO();
        }
        return instance;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void createProvider (String provName, String provDetail) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){
            stmnt.executeUpdate("INSERT INTO `8BqaG7Joaq`.`empresa` (`nome`, `tipo`, `detalhe`) VALUES ('"+provName+"', 'fornecedor', '"+provDetail+"');");
            LogDAO.getInstance().GenerateLog("Inserir fornecedor na tabela EMPRESA para CADASTRO FORNECEDOR");
        } 
    }
    
    public void editProvider (Provider prov) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){       
            stmnt.executeUpdate("UPDATE `8BqaG7Joaq`.`empresa` SET `nome`='"+prov.getName()+"',"
                    + " `detalhe`='"+prov.getDetail()+"', `tipo`='fornecedor' WHERE `id`='"+prov.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Editar fornecedor na tabela PRODUTO");
        }
    }
    
    public void deleteProvider (Provider prov) throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){           
            stmnt.executeUpdate("DELETE FROM `8BqaG7Joaq`.`empresa` WHERE `id`='"+prov.getId().toString()+"';");

            LogDAO.getInstance().GenerateLog("Excluir fornedor da tabela EMPRESA");
        }
    }

    public List<Provider> getProviderList () throws Exception {
        try (
            Statement stmnt = connection.createStatement();
        ){
            ResultSet rs = stmnt.executeQuery("SELECT * FROM `8BqaG7Joaq`.`empresa`");
            LogDAO.getInstance().GenerateLog("Consulta fornecedor na tabela EMPRESA");
            List<Provider> providerList = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("nome");
                String detail  = rs.getString("detalhe");
                Provider person = new Provider(name, detail, id);
                providerList.add(person);
            }
            return providerList ;
        } 
    }
}
