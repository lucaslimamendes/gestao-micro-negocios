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
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty price;
    private final StringProperty unitprc;
    private final IntegerProperty inventory;
    private final StringProperty type;


    public Product() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleStringProperty("");
        this.unitprc = new SimpleStringProperty("");
        this.inventory = new SimpleIntegerProperty(0);
        this.type = new SimpleStringProperty("");
    }
 
    public Product(String id, String qtd, String desc, String cat, String unit, String value) {
        this.id = new SimpleIntegerProperty(Integer.parseInt(id));
        this.name = new SimpleStringProperty(desc);
        this.price = new SimpleStringProperty(value);
        this.unitprc = new SimpleStringProperty(unit);
        this.inventory = new SimpleIntegerProperty(Integer.parseInt(qtd));
        this.type = new SimpleStringProperty(cat);
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

    public String getUnitPrice() {
        return unitprc.get();
    }

    public void setUnitPrice(String price) {
        this.unitprc.set(price);
    }
    
    public StringProperty unitProperty() {
        return unitprc;
    }
    
    public StringProperty priceProperty() {
        return price;
    }

    public Integer getInventory() {
        return inventory.get();
    }
    
    public Integer getId() {
        return id.get();
    }

    public void setInventory(Integer inventory) {
        this.inventory.set(inventory);
    }

    public IntegerProperty inventoryProperty() {
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
