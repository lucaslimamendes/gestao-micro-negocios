package gestao.micro.negocios.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import gestao.micro.negocios.model.Product;

/**
 *
 * @author upper
 */

public class ProductEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField invetaryField;
    @FXML
    private TextField typeField;


    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;

    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     * 
     * @param person
     */
    public void setProduct(Product product) {
        this.product = product;

        nameField.setText(product.getName());
        priceField.setText(product.getPrice());
        typeField.setText(product.getType());
        invetaryField.setText(Integer.toString(product.getInventory()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            product.setName(nameField.getText());
            product.setPrice(priceField.getText());
            product.setType(typeField.getText());
            product.setInventory(Integer.parseInt(invetaryField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Nome inválido!\n"; 
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Preço inválido!\n"; 
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "Tipo inválido!\n"; 
        }

        if (invetaryField.getText() == null || invetaryField.getText().length() == 0) {
            errorMessage += "Estoque inválido!\n"; 
        } else {
            try {
                Integer.parseInt(invetaryField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Estoque inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
            	      alert.setTitle("Campos Inválidos");
            	      alert.setHeaderText("Por favor, corrija os campos inválidos");
            	      alert.setContentText(errorMessage);
                alert.showAndWait();
                
            return false;
        }
    }
}