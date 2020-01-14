package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.exception.DAOException;

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
}
