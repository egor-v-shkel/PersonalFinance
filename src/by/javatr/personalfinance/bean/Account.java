package by.javatr.personalfinance.bean;

import by.javatr.personalfinance.service.AccountType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Account implements Serializable {

    private static final long serialVersionUID = 54876122555889745L;

    private long id;
    private long userID;
    private String name;
    private AccountType type;
    private long initialAmount;
    private List<Long> transactionsIDList;

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

    public List<Long> getTransactionsIDList() {
        return transactionsIDList;
    }

    public void setTransactionsIDList(List<Long> transactionsIDList) {
        this.transactionsIDList = transactionsIDList;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(long initialAmount) {
        this.initialAmount = initialAmount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return id == account.id &&
                userID == account.userID &&
                initialAmount == account.initialAmount &&
                Objects.equals(name, account.name) &&
                type == account.type &&
                Objects.equals(transactionsIDList, account.transactionsIDList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, name, type, initialAmount, transactionsIDList);
    }

    @Override
    public String toString() {
        return Account.class.getSimpleName()+"{" +
                "id=" + id +
                ", userID=" + userID +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", initialAmount=" + initialAmount +
                ", transactionsIDList=" + transactionsIDList +
                '}';
    }
}
