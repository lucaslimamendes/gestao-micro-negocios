package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class CalendarController implements Initializable {
    private MainApp mainApp;
    private Integer id;
    
    @FXML 
    public Pane calendarPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setMainApp(MainApp mainApp) throws Exception{
        this.mainApp = mainApp;
        this.id = mainApp.getIdUser();
    }
     
    private void menuShowCalendar(ActionEvent event) {
        mainApp.showCalendar();
    }

    private void menuShowEstoque(ActionEvent event) {
        mainApp.showProduto();
    }

    private void menuShowFornecedores(ActionEvent event) {
        mainApp.showFornecedores();
    }

    private void menuLogout(ActionEvent event) {
        Alert del = new Alert (Alert.AlertType.CONFIRMATION);
        del.setTitle("Confirmar Saída");
        del.setHeaderText("Deseja realizar sair desta conta?");
        del.setContentText("Após a confirmação você será enviado para Login.");

        Optional<ButtonType> result = del.showAndWait();
        if (result.get() == ButtonType.OK){
            mainApp.showLogin();
        }
    }

}
