package by.javatr.personalfinance.bean;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private static final long serialVersionUID = -493827331122613572L;

    int amount;
    int account_id;
    long id;
    Date date;
    String notes;

    public Transaction(int amount, long id, String notes, Date date) {
        this.amount = amount;
        this.id = id;
        this.notes = notes;
        this.date = date;
    }

}
