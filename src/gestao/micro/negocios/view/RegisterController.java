package gestao.micro.negocios.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.UserDAO;

/**
 *
 * @author upper
 */
public class RegisterController {
    private UserDAO dataAccessor ;
    
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    private MainApp mainApp;
    
    @FXML
    private void initialize() {
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
        dataAccessor = new UserDAO("remotemysql.com", "8BqaG7Joaq", "KZHhe6stfM");
        dataAccessor.createPerson(name.getText(), email.getText(), password.getText());
        mainApp.showLogin();
    }

    public void stop() throws Exception {
        if (dataAccessor != null) {
            dataAccessor.shutdown();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
