package gestao.micro.negocios.model;

import javafx.beans.property.*;

/**
 *
 * @author upper
 */
public class Provider {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty detail;
    private final StringProperty email;
    private final StringProperty telefone;
    private final StringProperty pedidos;

    public Provider() {
        this(null, null, "0", null, null, "0");
    }

    public Provider(String name, String detail, String id, String email, String telefone, String pedidos) {
        this.name = new SimpleStringProperty(name);
        this.detail = new SimpleStringProperty(detail);
        this.id = new SimpleIntegerProperty(Integer.parseInt(id));
        this.email = new SimpleStringProperty(email);
        this.telefone = new SimpleStringProperty(telefone);
        this.pedidos = new SimpleStringProperty(pedidos);
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

    public String getDetail() {
        return detail.get();
    }

    public void setDetail(String email) {
        this.detail.set(email);
    }

    public StringProperty detailProperty() {
        return detail;
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
    
    public String getPedidos() {
        return pedidos.get();
    }

    public void setPedidos(String pedidos) {
        this.pedidos.set(pedidos);
    }

    public StringProperty pedidosProperty() {
        return pedidos;
    } 
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String tel) {
        this.telefone.set(tel);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }  
}
