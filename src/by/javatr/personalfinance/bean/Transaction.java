package by.javatr.personalfinance.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {

    private static final long serialVersionUID = -8635883031814978974L;

    private long id;
    private long amount;
    private long accountId;
    private Date date;
    private String notes;

    public Transaction() {}

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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
                accountId == that.accountId &&
                id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, accountId, id, date, notes);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "amount=" + amount +
                ", account_id=" + accountId +
                ", id=" + id +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}
