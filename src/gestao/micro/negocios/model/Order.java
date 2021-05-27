/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao.micro.negocios.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author upper
 */
public class Order {
    private final IntegerProperty id;
    private final IntegerProperty client;
    private final StringProperty type;
    private final StringProperty detail;
    private final FloatProperty value;
    private final StringProperty data;
    private final StringProperty dataRealizada;

    public Order() {
        this("0", "0", null, null, "0", null, null);
    }

    public Order(String id, String client, String type, String detail, String value, String data, String dataRealizada) {
        this.id = new SimpleIntegerProperty(Integer.parseInt(id));
        this.client = new SimpleIntegerProperty(Integer.parseInt(client));
        this.type = new SimpleStringProperty(type);
        this.detail = new SimpleStringProperty(detail);
        this.value = new SimpleFloatProperty(Float.parseFloat(value));
        this.data = new SimpleStringProperty(data);
        this.dataRealizada = new SimpleStringProperty(dataRealizada);
    }
    
    public Integer getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(Integer.parseInt(id));
    }

    public IntegerProperty idProperty() {
        return id;
    } 
    
    public Integer getClient() {
        return client.get();
    }

    public void setClient(String client) {
        this.client.set(Integer.parseInt(client));
    }

    public IntegerProperty clientProperty() {
        return client;
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

    public String getDetail() {
        return detail.get();
    }

    public void setDetail(String det) {
        this.detail.set(det);
    }

    public StringProperty detailProperty() {
        return detail;
    }   
    
    public Float getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(Float.parseFloat(value));
    }

    public FloatProperty valueProperty() {
        return value;
    } 
    
    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public StringProperty dataProperty() {
        return data;
    }

    public String getDataRealizada() {
        return dataRealizada.get();
    }

    public void setDataRealizada(String tel) {
        this.dataRealizada.set(tel);
    }

    public StringProperty dataRealizadaProperty() {
        return dataRealizada;
    }  
}
