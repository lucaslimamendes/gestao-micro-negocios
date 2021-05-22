/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Escola
 */
public class MainController implements Initializable {

    @FXML
    private Pane viewPane;
    @FXML
    private AnchorPane menuPane;
    
    private MainApp mainApp;
    @FXML
    private Pane paneName;
    @FXML
    private Pane logoPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void menuShowCalendar(ActionEvent event) {
    }

    @FXML
    private void menuShowEvent(ActionEvent event) {
    }

    @FXML
    private void menuShowFinance(ActionEvent event) {
    }

    @FXML
    private void menuShowStock(ActionEvent event) {
        mainApp.showProduto();
    }

    @FXML
    private void menuShowProvider(ActionEvent event) {
        mainApp.showFornecedores();
    }

    @FXML
    private void menuShowClient(ActionEvent event) {
    }

    @FXML
    private void menuShowAdm(ActionEvent event) {
    }

    @FXML
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
