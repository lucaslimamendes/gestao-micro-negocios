package gestao.micro.negocios.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author upper
 */
public class User {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty pass;


    public User() {
        this(null, null, null);
    }

    public User(String name, String email, String pass) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.pass = new SimpleStringProperty(pass);
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

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public StringProperty passProperty() {
        return pass;
    }
    
}
