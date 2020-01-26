package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.AccountDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.utill.FileDatabase;
import by.javatr.personalfinance.service.AccountType;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileAccountDAO implements AccountDAO {
    @Override
    public String add(Account account) throws DAOException {
        String response = null;
        Map<Long, Account> accountDB = readDb();

        long nextID = FileDatabase.getInstance().getNextID(accountDB);
        account.setId(nextID);
        accountDB.put(nextID, account);

        try {
            writeDB(accountDB);
            response = "ADD_ACCOUNT success";
        } catch (DAOException e) {
            response = "ADD_ACCOUNT fail";
            e.printStackTrace();
            throw new DAOException("Exception while trying to write account data to database.", e);
        }

        return response;
    }

    @Override
    public String getInfo(String accountID) throws DAOException {
        return null;
    }

    @Override
    public Account getAccount(long userID, String accountName) throws DAOException {
        HashMap<Long, Account> accountDB = readDb();
        Collection<Account> values = accountDB.values();
        Iterator<Account> iterator = values.iterator();

        while(iterator.hasNext()) {
            Account nextAccount = iterator.next();
            if (nextAccount.getName().equals(accountName)){
                return nextAccount;
            }
        }

        throw new DAOException("Account not found.");
    }

    @Override
    public String update(String accountID, String name, int initialAmount) throws DAOException {
        return null;
    }

    @Override
    public String deleteAccount(String accountID) throws DAOException {
        return null;
    }

    @Override
    public long getId(String accountName) throws DAOException {
        return 0;
    }

    public HashMap<Long, Account> readDb() throws DAOException {

        HashMap<Long, Account> currentDB;
        String dbName = Account.class.getSimpleName();
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String pathToDB;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", dbName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException(String.format("%s database read exception: ", dbName)
                    + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToDB))) {
            currentDB = ((HashMap<Long, Account>) ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(String.format("Error during mapping %s info to database.", dbName.toLowerCase()), e);
        }

        return currentDB;
    }

    public <T, E> boolean writeDB(Map<Long, Account> currentDB) throws DAOException {
        boolean response = false;

        FileDatabase fileDatabase = FileDatabase.getInstance();
        String accountDBName = Account.class.getSimpleName();
        String pathToDB;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", accountDBName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException("Can't get DB directory from config file.", e);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToDB))) {
            oos.writeObject(currentDB);
            response = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(String.format("Error during write to %s DB.", accountDBName.toLowerCase()), e);
        }

        return response;
    }
}
