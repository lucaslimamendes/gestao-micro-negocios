package gestao.micro.negocios.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import gestao.micro.negocios.MainApp;

/**
 *
 * @author upper
 */
public class LoginController {
    
    @FXML
    private TextField user;
    @FXML
    private TextField password;

    private MainApp mainApp;
    
    @FXML
    private void initialize() {
    }

    @FXML
    private void handleEntrar() {
        mainApp.showProductOverview();
    }

    @FXML
    private void handleLimpar() {
        user.setText("");
        password.setText("");
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
