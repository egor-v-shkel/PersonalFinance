package by.javatr.personalfinance.dao.factory;

import by.javatr.personalfinance.dao.AccountDAO;
import by.javatr.personalfinance.dao.TransactionDAO;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.impl.FileAccountDAO;
import by.javatr.personalfinance.dao.impl.FileTransactionDAO;
import by.javatr.personalfinance.dao.impl.FileUserDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO fileUserDAO = new FileUserDAO();
    private final AccountDAO fileAccountDAO = new FileAccountDAO();
    private final TransactionDAO fileTransactionDAO = new FileTransactionDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getFileUserDAO() {
        return fileUserDAO;
    }
    public AccountDAO getFileAccountDAO() {
        return fileAccountDAO;
    }
    public TransactionDAO getFileTransactionDAO() {
        return fileTransactionDAO;
    }

}
