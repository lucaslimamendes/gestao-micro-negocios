/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.controller;

import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.CostDAO;
import gestao.micro.negocios.dao.ProductDAO;
import gestao.micro.negocios.model.Cost;
import gestao.micro.negocios.model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Escola
 */
public class CostController implements Initializable {
    private MainApp mainApp;
    private Integer id;
    
    @FXML
    public Pane calendarPane;
    @FXML
    private TableView<Cost> costTable;
    @FXML
    private TableColumn<Cost, String> tabCusto;
    @FXML
    private TableColumn<Cost, Number> tabValor;
    @FXML
    private TableColumn<Cost, String> tabData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabCusto.setCellValueFactory(cellData -> cellData.getValue().descProperty());
        tabValor.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        tabData.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
    }    
    
    public void setMainApp(MainApp mainApp) throws Exception{
        this.mainApp = mainApp;
        this.id = mainApp.getIdUser();
        
        costTable.setItems(mainApp.getCostData());
    }

    @FXML
     private void handleDelete() throws Exception {
        Cost tempCost = costTable.getSelectionModel().getSelectedItem();
        int selectedIndex = costTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
           Alert del = new Alert (Alert.AlertType.CONFIRMATION);
           del.setTitle("Confirmar Exclusão");
           del.setHeaderText("Deseja realmente excluir o custo?");
           del.setContentText("Após a confirmação não será possível reverter a ação.");

           Optional<ButtonType> result = del.showAndWait();
           if (result.get() == ButtonType.OK){
            costTable.getItems().remove(selectedIndex);
            CostDAO.getInstance(id).deleteCost(tempCost);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Custo Excluído");
            alert.setHeaderText(null);
            alert.setContentText("O custo " + tempCost.getDesc()+ " foi excluído com sucesso!");

            alert.showAndWait();
           }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhum Custo Selecionado");
            alert.setContentText("Por favor, selecione um custo da tabela.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEdit() {
        Cost selectedcost = costTable.getSelectionModel().getSelectedItem();
        if (selectedcost != null) {
           mainApp.showCostDialog(selectedcost, "edit");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhum Custo Selecionado");
            alert.setContentText("Por favor, selecione um custo na tabela.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCreate() throws Exception{
        Cost tempCost = new Cost();
        boolean okClicked = mainApp.showCostDialog(tempCost, "create");
        if (okClicked) {
            mainApp.getCostData().add(tempCost);
        }
    }
}
