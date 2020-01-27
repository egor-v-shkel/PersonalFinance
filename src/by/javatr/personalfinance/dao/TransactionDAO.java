package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.dao.exception.DAOException;

import java.util.List;

public interface TransactionDAO {
    String add(Transaction transaction) throws DAOException;

    Transaction get(long transactionID) throws DAOException;

    String update(Transaction transaction) throws DAOException;

    String delete(long transactionID) throws DAOException;

    List<Transaction> getAll(List<Long> transactionIdList) throws DAOException;
}
