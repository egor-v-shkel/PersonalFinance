package by.javatr.personalfinance.service.factory;

import by.javatr.personalfinance.service.AccountService;
import by.javatr.personalfinance.service.TransactionService;
import by.javatr.personalfinance.service.UserService;
import by.javatr.personalfinance.service.impl.AccountServiceImpl;
import by.javatr.personalfinance.service.impl.TransactionServiceImpl;
import by.javatr.personalfinance.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final TransactionService transactionService = new TransactionServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
