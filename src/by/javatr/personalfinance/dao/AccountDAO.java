package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.service.AccountType;

public interface AccountDAO {
    String add(Account account) throws DAOException;

    String getInfo(String accountID) throws DAOException;

    Account getAccount(long userId, String accountName) throws DAOException;

    String update(String accountID, String name, int initialAmount) throws DAOException;

    String deleteAccount(String accountID) throws DAOException;

    long getId(String accountName) throws DAOException;
}
