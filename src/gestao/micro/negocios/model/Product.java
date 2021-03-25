package gestao.micro.negocios.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author upper
 */
public class Product {
    private final StringProperty name;
    private final StringProperty price;
    private final IntegerProperty inventory;
    private final StringProperty type;


    public Product() {
        this(null, null);
    }

    public Product(String name, String price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);

        // Alguns dados de exemplo, apenas para testes.
        this.inventory = new SimpleIntegerProperty(7);
        this.type = new SimpleStringProperty("Utilitário");
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public StringProperty priceProperty() {
        return price;
    }

    public Integer getInventory() {
        return inventory.get();
    }

    public void setInventory(Integer inventory) {
        this.inventory.set(inventory);
    }

    public IntegerProperty streetInventory() {
        return inventory;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

}
