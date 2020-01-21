package by.javatr.personalfinance.service;

public interface TransactionService {


    String addTransaction(String accountId, String amount, String description);

    String update(String accountId, String amount, String description);

    String delete(String transactionId);

    String get(String transactionId);
}
