package gestao.micro.negocios;


import gestao.micro.negocios.model.Product;
import gestao.micro.negocios.view.DashboardController;
import gestao.micro.negocios.view.LoginController;
import gestao.micro.negocios.view.RegisterController;
import gestao.micro.negocios.view.ProductEditDialogController;
import gestao.micro.negocios.view.ProductOverviewController;
import gestao.micro.negocios.view.RootController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Product> productData = FXCollections.observableArrayList();
    
    public MainApp() {
        /*
        productData.add(new Product("Bexiga Gigante", "18.68"));
        productData.add(new Product("Bexiga Pequena", "2.89"));
        productData.add(new Product("Bexiga Média", "9.32"));
        productData.add(new Product("Confete", "7.87"));
        productData.add(new Product("Apito", "1.68"));
        productData.add(new Product("Biribinha", "3.34"));
        */
    }
    
    public ObservableList<Product> getProductData() {
        return productData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestão Micro Negócio");

        initRootLayout();

        showLogin();
    }
    
    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegister() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Register.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            RegisterController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Dashboard.fxml"));
            AnchorPane dash = (AnchorPane) loader.load();

            rootLayout.setCenter(dash);

            DashboardController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProductOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(productOverview);

            ProductOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showProductEditDialog(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Stage getPrimaryStage() {
            return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
