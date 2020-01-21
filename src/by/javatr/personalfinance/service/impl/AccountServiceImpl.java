package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.service.AccountService;
import by.javatr.personalfinance.service.exception.ServiceException;

public class AccountServiceImpl implements AccountService {

    @Override
    public String addAccount(String login, String name, String type_id, String initial_amount) throws ServiceException {
        return null;
    }

    @Override
    public String calculateBalance(String login, String name) throws ServiceException {
        return null;
    }

    @Override
    public String updateAccount(String accountId, String name, String initialAmount) throws ServiceException {
        return null;
    }

    @Override
    public String getAccountList(String login) throws ServiceException {
        return null;
    }

    @Override
    public String deleteAccount(String login, String name) throws ServiceException {
        return null;
    }
}

