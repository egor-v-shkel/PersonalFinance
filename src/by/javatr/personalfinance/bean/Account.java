package by.javatr.personalfinance.bean;

import by.javatr.personalfinance.domainmodel.AccountType;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1977784604587190888L;

    public final long ID;
    public final String NAME;
    public final AccountType TYPE;
    public final long CURRENCY_ID;
    public final String NOTE;

    public Account(long ID, String NAME, AccountType TYPE, long CURRENCY_ID, String NOTE) {
        this.ID = ID;
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.CURRENCY_ID = CURRENCY_ID;
        this.NOTE = NOTE;
    }


}
