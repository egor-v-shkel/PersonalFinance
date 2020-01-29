package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.utill.FileDatabase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTransactionDAO implements TransactionDAO {
    @Override
    public String add(Transaction transaction) throws DAOException {
        String response = "DAOTransaction add transaction filed";
        HashMap<Long, Transaction> database = readDb();
        long nextID = FileDatabase.getInstance().getNextID(database);
        transaction.setId(nextID);
        database.put(nextID, transaction);
        writeDB(database);

        response = "DAOTransaction add transaction success";

        return response;
    }

    @Override
    public Transaction get(long transactionID) throws DAOException {
        HashMap<Long, Transaction> database = readDb();
        Transaction transaction = database.get(transactionID);
        return transaction;
    }

    @Override
    public String update(Transaction transaction) throws DAOException {
        String response = "DAOTransaction update transaction failed.";

        HashMap<Long, Transaction> database = readDb();
        database.replace(transaction.getId(), transaction);

        writeDB(database);

        return response;
    }

    @Override
    public String delete(long transactionID) throws DAOException {
        String response = "DAOTransaction delete transaction failed.";

        HashMap<Long, Transaction> database = readDb();
        database.remove(transactionID);
        writeDB(database);

        response = "DAOTransaction delete transaction success.";

        return response;
    }

    @Override
    public List<Transaction> getAll(List<Long> transactionIdList) throws DAOException {
        HashMap<Long, Transaction> database = readDb();
        List<Transaction> transactions = new ArrayList<>();
        for (long transactionID :
                transactionIdList) {
            Transaction transaction = database.get(transactionID);
            transactions.add(transaction);
        }

        return transactions;
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
