package by.epam.ultraair.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users", catalog = "ultraAir")
public class User extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private boolean admin;

    public User(){}

    public User(String login, String password, boolean admin){
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public User(Integer id, String login, String password, boolean admin){
        super.setId(id);
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
