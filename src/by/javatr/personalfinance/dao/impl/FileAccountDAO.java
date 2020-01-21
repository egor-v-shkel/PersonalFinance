package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.dao.AccountDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.service.AccountType;

public class FileAccountDAO implements AccountDAO {
    @Override
    public String add(String name, AccountType type, long initialAmount) throws DAOException {
        return null;
    }

    @Override
    public String getInfo(String accountID) throws DAOException {
        return null;
    }

    @Override
    public String update(String accountID, String name, int initialAmount) throws DAOException {
        return null;
    }

    @Override
    public String deleteAccount(String accountID) throws DAOException {
        return null;
    }
}
