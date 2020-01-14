package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.domainmodel.AccountType;

public interface AccountDAO {
    String add(String name, AccountType type, long initialAmount) throws DAOException;

    String getInfo(String accountID) throws DAOException;

    String update(String accountID, String name, int initialAmount) throws DAOException;

    String deleteAccount(String accountID) throws DAOException;
}
