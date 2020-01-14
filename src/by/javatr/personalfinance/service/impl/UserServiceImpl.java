package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.factory.DAOFactory;
import by.javatr.personalfinance.service.utill.FileDatabase;
import by.javatr.personalfinance.service.utill.ServiceValidator;
import by.javatr.personalfinance.service.UserService;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.exception.UserDataException;

import java.util.LinkedList;

public class UserServiceImpl implements UserService {


    @Override
    public String register(String login, String password) throws ServiceException {
        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");

        User newUser = new User();
        newUser.setId(FileDatabase.getInstance().getFreeID("user.db"));
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setTransactions(new LinkedList<>());

        return DAOFactory
                .getInstance()
                .getFileUserDAO()
                .register(newUser);
    }

    @Override
    public String singIn(String login, String password) throws ServiceException {
        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");

        return DAOFactory
                .getInstance()
                .getFileUserDAO()
                .singIn(login, password);
    }

    @Override
    public String updateData(String userID, String login, String password) throws ServiceException {
        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(userID))
            throw new UserDataException("UserID must be natural long number.");

        long id = Long.parseLong(userID);

        return DAOFactory
                .getInstance()
                .getFileUserDAO()
                .updateData(id, login, password);
    }

    @Override
    public String delete(String userID, String password) throws ServiceException {
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(userID))
            throw new UserDataException("UserID must be natural long number.");

        long id = Long.parseLong(userID);

        return DAOFactory
                .getInstance()
                .getFileUserDAO()
                .delete(id, password);

    }

    @Override
    public String singOut(String userID, String password) throws ServiceException {

        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(userID))
            throw new UserDataException("UserID must be natural long number.");

        long id = Long.parseLong(userID);

        return DAOFactory
                .getInstance()
                .getFileUserDAO()
                .singOut(id, password);
    }
}
