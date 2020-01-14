package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.dao.exception.DAOException;

public interface TransactionDAO {
        String add(long accountID, long amount) throws DAOException;
        String get(long transactionID) throws DAOException;
        String update(long transactionID, long amount) throws DAOException;
        String delete(long transactionID) throws DAOException;

}