package by.javatr.personalfinance.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 303044282978682950L;

    private long id;
    private int amount;
    private long account_id;
    private Date date;
    private String notes;

    public Transaction() {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Transaction that = (Transaction) object;
        return amount == that.amount &&
                account_id == that.account_id &&
                id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, account_id, id, date, notes);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "amount=" + amount +
                ", account_id=" + account_id +
                ", id=" + id +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}
