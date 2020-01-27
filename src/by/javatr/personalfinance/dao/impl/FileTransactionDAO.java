package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.utill.FileDatabase;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTransactionDAO implements TransactionDAO {
    @Override
    public String add(long accountID, long amount) throws DAOException {
        return null;
    }

    @Override
    public String get(long transactionID) throws DAOException {
        return null;
    }

    @Override
    public String update(long transactionID, long amount) throws DAOException {
        return null;
    }

    @Override
    public String delete(long transactionID) throws DAOException {
        return null;
    }

    @Override
    public List<Transaction> getAll(long accountId) throws DAOException {
        return null;
    }

    public HashMap<Long, Transaction> readDb() throws DAOException {

        HashMap<Long, Transaction> currentDB;
        String dbName = Transaction.class.getSimpleName();
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
            currentDB = ((HashMap<Long, Transaction>) ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(String.format("Error during mapping %s info to database.", dbName.toLowerCase()), e);
        }

        return currentDB;
    }

    public boolean writeDB(Map<Long, Transaction> currentDB) throws DAOException {
        boolean response = false;

        FileDatabase fileDatabase = FileDatabase.getInstance();
        String accountDBName = Transaction.class.getSimpleName();
        String pathToDB;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", accountDBName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException("Can't get transaction DB directory from config file.", e);
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
