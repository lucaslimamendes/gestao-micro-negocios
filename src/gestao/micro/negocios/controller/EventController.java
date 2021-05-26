/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Escola
 */
public class EventController implements Initializable {
     private MainApp mainApp;
    private Integer id;
    
    @FXML
    private TextField clientField;
    @FXML
    private TextField peopleField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextArea productField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainApp(MainApp mainApp) throws Exception{
        this.mainApp = mainApp;
        this.id = mainApp.getIdUser();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
    }

    @FXML
    private void handleCreate(ActionEvent event) {
    }

    @FXML
    private void handleNext(ActionEvent event) {
    }
    
}
