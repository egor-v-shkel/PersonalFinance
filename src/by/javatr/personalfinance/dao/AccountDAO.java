package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.service.AccountType;

import java.util.List;

public interface AccountDAO {
    String add(Account account) throws DAOException;

    Account getAccount(long userId, String accountName) throws DAOException;
    Account getAccount(long accountId) throws DAOException;
    List<Account> getAccountList(String login) throws DAOException;

    String update(Account account) throws DAOException;

    String deleteAccount(long accountId) throws DAOException;

    long getId(long userId, String accountName) throws DAOException;
}
