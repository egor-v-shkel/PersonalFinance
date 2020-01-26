package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.AccountDAO;
import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.factory.DAOFactory;
import by.javatr.personalfinance.service.AccountService;
import by.javatr.personalfinance.service.AccountType;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.exception.UserDataException;
import by.javatr.personalfinance.service.utill.ServiceValidator;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public String addAccount(String login, String accountName, String type, String initialAmount) throws ServiceException {

        String response = "ADD_ACCOUNT service exception";

        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(accountName))
            throw new UserDataException("Account name can't be null or empty.");
        if (ServiceValidator.isValidUserdata(type))
            throw new UserDataException("Account type can't be null or empty.");
        if (ServiceValidator.isValidAmount(initialAmount))
            throw new UserDataException("Initial amount must be long number.");

        User user = null;
        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();
        try {
            user = fileUserDAO.getUser(login);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to load user from database.", e);
        }

        Account account = new Account();
        account.setUserID(user.getId());
        account.setName(accountName);
        account.setType(AccountType.valueOf(type));
        account.setInitialAmount(Long.parseLong(initialAmount));

        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        try {
            fileAccountDAO.add(account);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        long accountID = 0L;

        try {
            accountID = fileAccountDAO.getId(accountName);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        List<Long> accountIDList = user.getAccountIDList();
        accountIDList.add(accountID);
        try {
            fileUserDAO.updateData(user);
            response = "ADD_ACCOUNT success";
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String calculateBalance(String login, String accountName) throws ServiceException {
        String response = "ADD_ACCOUNT service exception";

        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(accountName))
            throw new UserDataException("Account name can't be null or empty.");

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO fileUserDAO = daoFactory.getFileUserDAO();

        User user = null;
        try {
            user = fileUserDAO.getUser(login);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to calculate balance. Reason: " + e.getMessage());
        }

        long userId = user.getId();

        AccountDAO fileAccountDAO = daoFactory.getFileAccountDAO();
        Account account = null;
        try {
            account = fileAccountDAO.getAccount(userId, accountName);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to calculate balance. Reason: " + e.getMessage());
        }

        long initialAmount = account.getInitialAmount();
        List<Long> transactionsIDList = account.getTransactionsIDList();

        TransactionDAO fileTransactionDAO = daoFactory.getFileTransactionDAO();

        List<Transaction> transactionList = null;
        try {
            transactionList = fileTransactionDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to calculate balance. Reason: " + e.getMessage());
        }

        long sum = calculate(initialAmount, transactionList);

        return String.format("GET_BALANCE account:%s balance:%d", accountName, sum);
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

    private long calculate(long initialAmount, List<Transaction> transactionList) {
        long sum = initialAmount;
        for (Transaction transaction : transactionList) {
            sum += transaction.getAmount();
        }
        return sum;
    }
}

