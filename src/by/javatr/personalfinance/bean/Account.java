package by.javatr.personalfinance.bean;

import by.javatr.personalfinance.service.AccountType;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private static final long serialVersionUID = 5526389381480318016L;

    private long id;
    private long userID;
    private String name;
    private AccountType type;
    private String note;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return id == account.id &&
                userID == account.userID &&
                Objects.equals(name, account.name) &&
                type == account.type &&
                Objects.equals(note, account.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, name, type, note);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", userID=" + userID +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", note='" + note + '\'' +
                '}';
    }
}
