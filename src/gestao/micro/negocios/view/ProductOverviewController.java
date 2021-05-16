package gestao.micro.negocios.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import gestao.micro.negocios.MainApp;
import gestao.micro.negocios.dao.ProductDAO;
import gestao.micro.negocios.model.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author upper
 */
public class ProductOverviewController {
    private ProductDAO dataAccessor ;
    
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;

    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label inventory;
    @FXML
    private Label type;

    private MainApp mainApp;

    public ProductOverviewController() {
    }

    @FXML
    private void initialize() throws SQLException {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        
        showProductDetails(null);
        productTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showProductDetails(newValue));
    }
    
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Pessoa Selecionada");
            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewPerson() {
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
        if (okClicked) {
            mainApp.getProductData().add(tempProduct);
        }
    }
    
    @FXML
    private void handleEditPerson() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = mainApp.showProductEditDialog(selectedProduct);
            if (okClicked) {
                showProductDetails(selectedProduct);
            }

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhum Produto Selecionada");
            alert.setContentText("Por favor, selecione um produto na tabela.");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        productTable.setItems(mainApp.getProductData());
    }
    
    private void showProductDetails(Product product) {
        if (product != null) {
            name.setText(product.getName());
            price.setText(product.getPrice());
            type.setText(product.getType());
            inventory.setText(Integer.toString(product.getInventory()));
        } else {
            name.setText("");
            price.setText("");
            type.setText("");
            inventory.setText("");
        }
    }
}
