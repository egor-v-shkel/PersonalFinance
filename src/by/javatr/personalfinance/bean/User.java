package by.javatr.personalfinance.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 5268050154127387822L;
    private long id;
    private String login;
    private String password;
    private List<Transaction> transactions;

    public User() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id == user.id &&
                password.equals(user.password) &&
                transactions.equals(user.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, transactions);
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
