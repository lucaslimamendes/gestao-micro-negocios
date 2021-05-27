/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.CostDAO;
import gestao.micro.negocios.model.Cost;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Escola
 */
public class CostDialogController implements Initializable {
    private MainApp mainApp;
    private Integer id;
    
    private Cost cost;
    
    private Stage dialogStage;
    
    private String action;
    
    private boolean okClicked = false;

    
    @FXML
    private TextField nameField;
    @FXML
    private TextField valueField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

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
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setAction (String action){
        this.action = action;
    }
    
    public void setCost(Cost cost) {
        this.cost = cost;

        nameField.setText(cost.getDesc());
        valueField.setText(cost.getPrice().toString());
        dateField.setValue(cost.getDate());
    }

    @FXML
    private void dateField(ActionEvent event) {
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void handleOk(ActionEvent event) throws Exception{
        if (isInputValid()) {
            cost.setDesc(nameField.getText());
            cost.setPrice(Float.parseFloat(valueField.getText().replace(",", ".")));
            cost.setDate(dateField.getValue());
            
            switch(action){
                case "edit":
                    CostDAO.getInstance(id).editCost(cost);
                    break;
                case "create":
                    CostDAO.getInstance(id).createCost(cost);
                    break;
            }
            okClicked = true;
            dialogStage.close();
        }
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Nome inválido!\n"; 
        }
        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "Valor inválido!\n"; 
        }
        if (dateField.getValue() == null) {
            errorMessage += "Data inválida!\n"; 
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            	      alert.setTitle("Campos Inválidos");
            	      alert.setHeaderText("Por favor, corrija os campos inválidos");
            	      alert.setContentText(errorMessage);
                alert.showAndWait();
                
            return false;
        }
    }
    
}
