/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.OrderDAO;
import gestao.micro.negocios.dao.ProviderDAO;
import gestao.micro.negocios.model.Order;
import gestao.micro.negocios.model.Provider;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Escola
 */
public class OrderDialogController implements Initializable {
    private MainApp mainApp;
    private Integer id;
    
    private Stage dialogStage;
    
    private Provider provider;    
    
    private Order order;
    
    private boolean okClicked = false;

    @FXML
    private TextField typeField;
    @FXML
    private TextField detalheField;
    @FXML
    private TextField valueField;
    @FXML
    private DatePicker dataField;



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
    private void handleOk(ActionEvent event) throws Exception{
        if (isInputValid()) {
            LocalDate localDate = dataField.getValue();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("uuuu/MM/d");
            order.setType(typeField.getText());
            order.setDetail(detalheField.getText());
            order.setValue(valueField.getText());
            order.setData(localDate.format(formatters));
            order.setClient(Integer.toString(provider.getId()));
            
            OrderDAO.getInstance(id).createOrder(order);
            okClicked = true;
            dialogStage.close();
        }
        
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public boolean isOkClicked() { 
        return okClicked;
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "Tipo inválido!\n"; 
        }
        
        if (detalheField.getText() == null || detalheField.getText().length() == 0) {
            errorMessage += "Detalhe inválido!\n";
        }
        
        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "Valor inválido!\n";
        }
        
        try{
            parseFloat(valueField.getText());
        }catch(Exception e) {
            errorMessage += "Valor inválido!\n";
        }
         
        if (dataField.getValue() == null) {
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
