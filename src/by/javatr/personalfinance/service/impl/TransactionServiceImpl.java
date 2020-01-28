package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.dao.AccountDAO;
import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.factory.DAOFactory;
import by.javatr.personalfinance.service.TransactionService;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.utill.ServiceValidator;

import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public String addTransaction(String accountId, String amount, String description) throws ServiceException {
        String response;

        if (!ServiceValidator.isValidAmount(amount))
            throw new ServiceException("Amount can't be null or empty.");
        if (ServiceValidator.isValidUserdata(description))
            throw new ServiceException("Description can't be null or empty.");


        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        AccountDAO accountDAO = DAOFactory.getInstance().getFileAccountDAO();
        try {
            Transaction transaction = new Transaction();
            long amountL = Long.parseLong(amount);
            transaction.setAmount(amountL);
            transaction.setNotes(description);
            transaction.setDate(new Date());
            fileTransactionDAO.add(transaction);
            long transactionId = transaction.getId();

            long accountIdL = Long.parseLong(accountId);
            Account account = accountDAO.getAccount(accountIdL);
            List<Long> transactionsIDList = account.getTransactionsIDList();
            transactionsIDList.add(transactionId);
            account.setTransactionsIDList(transactionsIDList);
            accountDAO.update(account);

            response = "ADD_TRANSACTION success";
        } catch (DAOException e) {
            response = "ADD_TRANSACTION service exception";
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to load user from database.", e);
        }

        return response;
    }

    @Override
    public String update(String transactionId, String amount, String description) throws ServiceException {
        String response;

        if (ServiceValidator.isValidID(transactionId))
            throw new ServiceException("Transaction id can't be null or empty.");
        if (!ServiceValidator.isValidAmount(amount))
            throw new ServiceException("Amount can't be null or empty.");
        if (ServiceValidator.isValidUserdata(description))
            throw new ServiceException("Description can't be null or empty.");


        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        try {
            long transactionID = Long.parseLong(transactionId);
            Transaction transaction = fileTransactionDAO.get(transactionID);
            long amount1 = Long.parseLong(amount);
            transaction.setAmount(amount1);
            transaction.setNotes(description);

            fileTransactionDAO.update(transaction);

            response = "UPDATE_TRANSACTION success";
        } catch (DAOException e) {
            response = "UPDATE_TRANSACTION service exception";
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to load user from database.", e);
        }

        return response;
    }

    @Override
    public String delete(String transactionId) throws ServiceException {
        String response;

        if (ServiceValidator.isValidID(transactionId))
            throw new ServiceException("Transaction id can't be null or empty.");


        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        try {
            long transactionID = Long.parseLong(transactionId);
            fileTransactionDAO.delete(transactionID);

            response = "DELETE_TRANSACTION success";
        } catch (DAOException e) {
            response = "DELETE_TRANSACTION service exception";
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to load user from database.", e);
        }

        return response;
    }

    @Override
    public String get(String transactionId) throws ServiceException {
        String response;

        if (ServiceValidator.isValidID(transactionId))
            throw new ServiceException("Transaction id can't be null or empty.");

        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        try {
            long transactionID = Long.parseLong(transactionId);
            Transaction transaction = fileTransactionDAO.get(transactionID);
            long amount = transaction.getAmount();
            String description = transaction.getNotes();
            Date date = transaction.getDate();

            response = String
                    .format("GET_TRANSACTION amount:%d, description:%s, date:%tc", amount, description, date);
        } catch (DAOException e) {
            response = "DELETE_TRANSACTION service exception";
            e.printStackTrace();
            throw new ServiceException("Unsuccessful attempt to load user from database.", e);
        }

        return response;
    }

    @Override
    public String getList(String accountId) throws ServiceException {
        String response = "GET_TRANSACTION_LIST no transactions for current account";

        if (ServiceValidator.isValidID(accountId))
            throw new ServiceException("Account id can't be null or empty.");

        TransactionDAO fileTransactionDAO = DAOFactory.getInstance().getFileTransactionDAO();
        AccountDAO fileAccountDAO = DAOFactory.getInstance().getFileAccountDAO();
        List<Long> transactionIdList;
        try {
            long accountId1 = Long.parseLong(accountId);
            Account account = fileAccountDAO.getAccount(accountId1);
            transactionIdList = account.getTransactionsIDList();
            List<Transaction> all = fileTransactionDAO.getAll(transactionIdList);
            response = String.format("GET_TRANSACTION_LIST transaction_list:%s", all);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
