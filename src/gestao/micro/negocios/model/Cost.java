package gestao.micro.negocios.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author upper
 */
public class Cost {
    private Integer id;
    private final StringProperty data;
    private final FloatProperty price;
    private final StringProperty desc;


    public Cost() {
        this("0", null, "0", null);
    }

    public Cost(String id, String data, String value, String desc) {
        this.id = Integer.parseInt(id);
        this.data = new SimpleStringProperty(data);
        this.price = new SimpleFloatProperty(Float.parseFloat(value));
        this.desc = new SimpleStringProperty(desc);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    
    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public StringProperty descProperty() {
        return desc;
    }
    
    public StringProperty dataProperty() {
        return data;
    }

    public String getData() {
        return data.get();
    }
    
    public LocalDate getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy");
        String date = this.data.get() == null ? LocalDate.now().format(dtf) : this.data.get();
        return LocalDate.parse(date, dtf);
      
    }
    
    public void setData(String data) {
        this.data.set(data);
    }

    public void setDate(LocalDate data) {
        this.data.set(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public StringProperty DataProperty() {
        return data;
    }

    public Float getPrice() {
        return price.get();
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }
    
    public FloatProperty priceProperty() {
        return price;
    }
}
