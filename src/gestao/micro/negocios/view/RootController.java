package gestao.micro.negocios.view;

import gestao.micro.negocios.MainApp;
import javafx.fxml.FXML;

/**
 *
 * @author upper
 */
public class RootController {
    

    private MainApp mainApp;

    @FXML
    private void initialize() {
    }
    
    @FXML
    private void handleSair() {
        mainApp.showLogin();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
