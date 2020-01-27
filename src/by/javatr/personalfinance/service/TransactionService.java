package by.javatr.personalfinance.service;

import by.javatr.personalfinance.service.exception.ServiceException;

public interface TransactionService {

    String addTransaction(String accountId, String amount, String description) throws ServiceException;

    String update(String transactionId, String amount, String description) throws ServiceException;

    String delete(String transactionId) throws ServiceException;

    String get(String transactionId) throws ServiceException;

    String getList(String accountId) throws ServiceException;
}
