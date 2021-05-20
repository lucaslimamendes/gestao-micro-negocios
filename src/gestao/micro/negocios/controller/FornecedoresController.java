/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.ProductDAO;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author Escola
 */
public class FornecedoresController implements Initializable {
    private MainApp mainApp;

    @FXML
    private TableView<?> productTable;
    @FXML
    private TableColumn<?, ?> tabProvider;
    @FXML
    private TableColumn<?, ?> tabDetail;
    @FXML
    private TableColumn<?, ?> tabOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainApp(MainApp mainApp) throws Exception{
        this.mainApp = mainApp;
       // productTable.setItems(mainApp.getProductData());
    } 

    @FXML
    private void handleProvider(ActionEvent event) {
    }

    @FXML
    private void handleOrder(ActionEvent event) {
    }

    @FXML
    private void menuShowEventos(ActionEvent event) {
        //mainApp.showEventos();
    }

    @FXML
    private void menuShowEstoque(ActionEvent event) {
        mainApp.showProduto();
    }

    @FXML
    private void menuShowFornecedores(ActionEvent event) {
        mainApp.showFornecedores();
    }

    @FXML
    private void menuShowClientes(ActionEvent event) {
       // mainApp.showClientes();
    }

    @FXML
    private void menuShowFinanceiro(ActionEvent event) {
       // mainApp.showFinanceiro();
    }

    @FXML
    private void menuShowAdm(ActionEvent event) {
        //mainApp.showAdm();
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
