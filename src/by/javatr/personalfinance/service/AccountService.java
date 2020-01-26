package by.javatr.personalfinance.service;

import by.javatr.personalfinance.service.exception.ServiceException;

public interface AccountService {

    String addAccount(String login, String name, String type, String initial_amount) throws ServiceException;

    String calculateBalance(String login, String name) throws ServiceException;

    String updateAccount(String accountId, String name, String initialAmount) throws ServiceException;

    String getAccountList(String login) throws ServiceException;

    String deleteAccount(String accountId) throws ServiceException;

    String getAccount(String accountId) throws ServiceException;
}

