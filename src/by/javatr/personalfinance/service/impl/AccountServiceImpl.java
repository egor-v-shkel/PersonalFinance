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

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public String addAccount(String login, String accountName, String type, String initialAmount) throws ServiceException {

        String response = "ADD_ACCOUNT service exception";

        if (ServiceValidator.checkUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.checkUserdata(accountName))
            throw new UserDataException("Account name can't be null or empty.");
        if (ServiceValidator.checkUserdata(type))
            throw new UserDataException("Account type can't be null or empty.");
        if (ServiceValidator.checkAmount(initialAmount))
            throw new UserDataException("Initial amount must be long number.");
        if (ServiceValidator.checkAccountType(type))
            throw new UserDataException("Unknown type");

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
        account.setType(AccountType.valueOf(type.toUpperCase()));
        account.setInitialAmount(Long.parseLong(initialAmount));
        account.setTransactionsIDList(new ArrayList<>());

        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        try {
            fileAccountDAO.add(account);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Can't add account", e);
        }

        long accountID = account.getId();
        List<Long> accountIDList = user.getAccountIDList();
        accountIDList.add(accountID);

        try {
            fileUserDAO.updateData(user);
            response = String.format("ADD_ACCOUNT account_id:%d", accountID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Can't update account", e);
        }

        return response;
    }

    @Override
    public String calculateBalance(String login, String accountName) throws ServiceException {
        String response = "ADD_ACCOUNT service exception";

        if (ServiceValidator.checkUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.checkUserdata(accountName))
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
            transactionList = fileTransactionDAO.getAll(transactionsIDList);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to calculate balance. Reason: " + e.getMessage());
        }

        long sum = calculate(initialAmount, transactionList);

        return String.format("GET_BALANCE account:%s balance:%d", accountName, sum);
    }

    @Override
    public String calculateCommonBalance(String userId) throws ServiceException {
        String response = "ADD_ACCOUNT service exception";

        if (ServiceValidator.checkId(userId))
            throw new UserDataException("Login can't be null or empty.");

        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();

        long sum = 0L;

        try {
            long userId1 = Long.parseLong(userId);
            List<Account> accounts = fileAccountDAO.getAccountList(userId1);

            for (Account a :
                    accounts) {
                sum += calculate(a.getInitialAmount(), a.getId());
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to calculate balance. Reason: " + e.getMessage());
        }

        return String.format("GET_COMMON_BALANCE common_balance:%d", sum);
    }

    @Override
    public String updateAccount(String accountId, String name, String initialAmount) throws ServiceException {
        if (ServiceValidator.checkUserdata(name))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.checkAmount(initialAmount))
            throw new UserDataException("Amount can't be null or empty.");
        if (ServiceValidator.checkId(accountId))
            throw new UserDataException("AccountId must be natural long number.");

        String response = "UPDATE_ACCOUNT success";
        long id = Long.parseLong(accountId);
        AccountDAO accountDAO = DAOFactory.getInstance().getFileAccountDAO();

        try {
            Account account = accountDAO.getAccount(id);
            account.setName(name);
            account.setInitialAmount(Long.parseLong(initialAmount));
            accountDAO.update(account);
        } catch (DAOException e) {
            e.printStackTrace();
            response = "UPDATE_ACCOUNT failed.";
            throw new ServiceException("Can't update account");
        }

        return response;
    }

    @Override
    public String getAccountList(String userId) throws ServiceException {
        if (ServiceValidator.checkId(userId))
            throw new UserDataException("User id can't be null or empty.");

        String response;

        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        try {
            long userId1 = Long.parseLong(userId);
            List<Account> accountList = fileAccountDAO.getAccountList(userId1);
            response = String.format("GET_ACCOUNT_LIST accounts:%s", accountList.toString());
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("GET_ACCOUNT_LIST user has no accounts.", e);
        }

        return response;
    }

    @Override
    public String getAccount(String accountId) throws ServiceException {
        if (ServiceValidator.checkId(accountId))
            throw new UserDataException("AccountID can't be null or empty.");

        String response;

        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        TransactionDAO transactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        try {
            long id = Long.parseLong(accountId);
            Account account = fileAccountDAO.getAccount(id);
            String name = account.getName();
            AccountType type = account.getType();
            long initialAmount = account.getInitialAmount();
            List<Long> transactionsIDList = account.getTransactionsIDList();
            List<Transaction> transactions = transactionDAO.getAll(transactionsIDList);
            long balance = calculate(initialAmount, transactions);

            response = String.format("GET_ACCOUNT account_name:%s, account_type:%s, initial_amount:%s, balance:%d",
                    name, type, initialAmount, balance);
        } catch (DAOException e) {
            e.printStackTrace();
            response = "GET_ACCOUNT can't get account.";
            throw new ServiceException("Exception while getting user data from DB");
        }

        return response;
    }

    @Override
    public String deleteAccount(String accountId) throws ServiceException {
        if (ServiceValidator.checkId(accountId))
            throw new UserDataException("AccountID can't be null or empty.");

        String response;
        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();
        AccountDAO accountDAO = DAOFactory.getInstance().getFileAccountDAO();
        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();

        try {
            long accountIdL = Long.parseLong(accountId);
            Account account = accountDAO.getAccount(accountIdL);
            long userID = account.getUserID();

            User user = fileUserDAO.getUser(userID);
            List<Long> accountIDList = user.getAccountIDList();
            accountIDList.remove(accountIdL);
            user.setAccountIDList(accountIDList);
            fileUserDAO.updateData(user);

            List<Long> transactionsIDList = account.getTransactionsIDList();
            for (Long t :
                    transactionsIDList) {
                fileTransactionDAO.delete(t);
            }

            accountDAO.deleteAccount(accountIdL);
            response = "DELETE_ACCOUNT success";
        } catch (DAOException e) {
            response = "UPDATE_DATA problem occur while accessing DB";
            e.printStackTrace();
            throw new ServiceException("Exception while trying to delete account");
        }

        return response;
    }

    private long calculate(long initialAmount, List<Transaction> transactionList) {
        long sum = initialAmount;
        if (transactionList == null) return sum;
        for (Transaction transaction : transactionList) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    private long calculate(long initialAmount, long accountId) throws DAOException {
        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        Account account = fileAccountDAO.getAccount(accountId);
        List<Long> transactionsIDList = account.getTransactionsIDList();
        List<Transaction> transactions = DAOFactory.getInstance().getFileTransactionDAO().getAll(transactionsIDList);
        return calculate(initialAmount, transactions);
    }
}

