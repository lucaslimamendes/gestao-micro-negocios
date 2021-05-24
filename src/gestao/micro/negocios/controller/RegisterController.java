package gestao.micro.negocios.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.UserDAO;

/**
 *
 * @author upper
 */
public class RegisterController {  
    private Integer id;
    private MainApp mainApp;
    
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    
    @FXML
    private void initialize() {
    }
    
    public void setMainApp(MainApp mainApp) throws Exception{
        this.mainApp = mainApp;
        this.id = mainApp.getIdUser();
    }

    @FXML
    private void handleVoltar() {
        mainApp.showLogin();
    }

    @FXML
    private void handleLimpar() {
        name.setText("");
        email.setText("");
        password.setText("");
    }

    @FXML
    public void handleCriar() throws Exception {
        UserDAO.getInstance().createPerson(name.getText(), email.getText(), password.getText());
        mainApp.showLogin();
    }
}
